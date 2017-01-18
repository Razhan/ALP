package com.alp.mvp.games.ui;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alp.library.base.ui.BaseFragment;
import com.alp.mvp.R;
import com.alp.mvp.adapter.GoalPlayerAdapter;
import com.alp.mvp.games.data.model.Penalty;
import com.alp.mvp.games.data.model.Score;
import com.alp.mvp.widget.SectionView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.alp.mvp.games.ui.LiveGameActivity.TYPE_ADD_PENALTY;
import static com.alp.mvp.games.ui.LiveGameActivity.TYPE_ADD_SCORE;

public class AddOperationFragment extends BaseFragment {

    private static final int FIRST_STEP = 0;
    private static final int SECOND_STEP = 1;
    private final static int ANIMATION_DURATION = 300;

    private final static String KEY_TYPE = "startStep";

    @BindView(R.id.back_arrow)
    ImageView backArrow;
    @BindView(R.id.next_arrow)
    ImageView nextArrow;
    @BindView(R.id.select_player_list)
    RecyclerView selectPlayerList;
    @BindView(R.id.time)
    TextView timeView;
    @BindView(R.id.add_tip)
    TextView tip;
    @BindView(R.id.add_time_wrapper)
    RelativeLayout addTimeWrapper;
    @BindView(R.id.period_view)
    SectionView periodView;
    @BindView(R.id.comment)
    EditText comment;

    private List<String> list1, periodList;
    private GoalPlayerAdapter goalPlayerAdapter;
    private int step = 0;
    private int type = 0;
    private boolean isGoalSelected;
    private int margin;

    private IAddOperationCallBack callBack;

    public static AddOperationFragment newInstance(int i) {
        Bundle args = new Bundle();
        AddOperationFragment fragment = new AddOperationFragment();
        args.putInt(KEY_TYPE, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_add_operation;
    }

    @Override
    public void initData() {
        list1 = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            list1.add("11");
        }

        periodList = new ArrayList<>();
        periodList.add("1st period");
        periodList.add("2nd period");
        periodList.add("3rd period");

        type = getArguments().getInt(KEY_TYPE, 0);

        setCallBack();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        margin = ((ViewGroup.MarginLayoutParams) addTimeWrapper.getLayoutParams()).leftMargin * 2;

        goalPlayerAdapter = new GoalPlayerAdapter(activity, list1);
        goalPlayerAdapter.setClickListener((view, pos, item) -> goalPlayerAdapter.updatePlayer(view, item));
        goalPlayerAdapter.setListener(res -> {
            isGoalSelected = res;

            if (isGoalSelected) {
                nextArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
            } else {
                nextArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            }
        });

        selectPlayerList.setLayoutManager(new LinearLayoutManager(activity));
        selectPlayerList.setAdapter(goalPlayerAdapter);

        periodView.setSectionCount(periodList);

        initType();
    }

    public void setCallBack() {
        this.callBack = (IAddOperationCallBack) activity;
    }

    private void initType() {
        if (type == TYPE_ADD_SCORE) {
            addTimeWrapper.post(() -> addTimeWrapper.setTranslationX(addTimeWrapper.getHeight() + margin));
            selectPlayerList.setVisibility(View.VISIBLE);
            initPlayerSelect();
        } else {
            selectPlayerList.setVisibility(View.INVISIBLE);
            initAddTime();
            nextArrow.setVisibility(View.INVISIBLE);
        }
    }

    private void initPlayerSelect() {
        tip.setText("First click the player who scored, than choose up by 2 assists and go next.");
        nextArrow.setVisibility(View.VISIBLE);

        if (isGoalSelected) {
            nextArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
        } else {
            nextArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
        }
    }

    private void initAddTime() {
        tip.setText("Enter period");
        nextArrow.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.time_wrapper)
    public void onTimeClick() {
        PickerDialogFragment dialog = new PickerDialogFragment();
        dialog.setListener(time -> timeView.setText(time))
                .show(getChildFragmentManager(), "dialog");
    }

    @OnClick(R.id.back_arrow)
    public void onBackArrowClick() {
        if (step == FIRST_STEP) {
            callBack.backToDetailPage();
        } else {
            initPlayerSelect();
            animateStep(addTimeWrapper.getHeight() + margin);
            step--;
        }
    }

    @OnClick(R.id.next_arrow)
    public void onNextArrowClick() {
        if (step == FIRST_STEP && isGoalSelected) {
            initAddTime();
            animateStep(-addTimeWrapper.getHeight() - margin);
            step++;
        }
    }

    @OnClick(R.id.confirm_button)
    public void onButtonClick() {
        if (callBack != null && type == TYPE_ADD_SCORE && checkInfo()) {
            Score score = new Score();
            score.setPlayer(goalPlayerAdapter.getScorePlayer());
            score.setPeriod(periodView.getSelectedPeriod());
            score.setTime(timeView.getText().toString());
            score.setComment(comment.getText().toString());

            callBack.addScore(score);
            callBack.backToDetailPage();
        }

        if (callBack != null && type == TYPE_ADD_PENALTY && checkInfo()) {
            Penalty penalty = new Penalty();
            penalty.setPeriod(periodView.getSelectedPeriod());
            penalty.setTime(timeView.getText().toString());
            penalty.setComment(comment.getText().toString());

            callBack.addPenalty(penalty);
            callBack.backToDetailPage();
        }
    }

    public void onBack() {
        onBackArrowClick();
    }

    private boolean checkInfo() {
        if (TextUtils.isEmpty(periodView.getSelectedPeriod())) {
            showToast("please select the period");
            return false;
        }

        if (TextUtils.isEmpty(timeView.getText())) {
            showToast("please input the time");
            return false;
        }

        if (TextUtils.isEmpty(comment.getText())) {
            showToast("please input the comment");
            return false;
        }

        if (type == TYPE_ADD_SCORE && TextUtils.isEmpty(goalPlayerAdapter.getScorePlayer().getGoalPlayer())) {
            showToast("please select the goal player");
            return false;
        }

        return true;
    }

    private void animateStep(float value) {
        addTimeWrapper.animate()
                .translationXBy(value)
                .setInterpolator(new LinearInterpolator())
                .setDuration(ANIMATION_DURATION)
                .start();

        selectPlayerList.animate()
                .translationXBy(value)
                .setInterpolator(new LinearInterpolator())
                .setDuration(ANIMATION_DURATION)
                .start();
    }

    public interface OnTimeSetListener {

        void onTimeSet(String time);
    }

    public interface OnGoalSelectListener {

        void onGoalSelect(boolean isSelected);
    }

}
