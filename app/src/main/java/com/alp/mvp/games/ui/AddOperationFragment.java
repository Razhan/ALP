package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alp.library.base.ui.BaseFragment;
import com.alp.mvp.R;
import com.alp.mvp.adapter.SelectPlayerAdapter;
import com.alp.mvp.widget.SectionView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.alp.mvp.games.ui.LiveGameActivity.TYPE_ADDSCORE;

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
    @BindView(R.id.period_view)
    SectionView periodView;
    @BindView(R.id.time)
    TextView timeView;
    @BindView(R.id.add_tip)
    TextView tip;
    @BindView(R.id.add_time_wrapper)
    RelativeLayout addTimeWrapper;

    private List<String> list1;
    private SelectPlayerAdapter selectPlayerAdapter;
    private int step = 0;
    private int type = 0;
    private boolean isGoalSelected;
    private int margin;

    public static Fragment newInstance(int i) {
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
            list1.add("");
        }

        type = getArguments().getInt(KEY_TYPE, 0);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        margin = ((ViewGroup.MarginLayoutParams) addTimeWrapper.getLayoutParams()).leftMargin * 2;

        selectPlayerAdapter = new SelectPlayerAdapter(activity, list1);
        selectPlayerAdapter.setClickListener((view, pos, item) -> selectPlayerAdapter.updatePlayer(view));
        selectPlayerAdapter.setListener(res -> {
            isGoalSelected = res;

            if (isGoalSelected) {
                nextArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
            } else {
                nextArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            }
        });

        selectPlayerList.setLayoutManager(new LinearLayoutManager(activity));
        selectPlayerList.setAdapter(selectPlayerAdapter);

        periodView.setSectionCount(3);

        initType();
    }

    private void initType() {
        if (type == TYPE_ADDSCORE) {
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
    public void onClick() {
        PickerDialogFragment dialog = new PickerDialogFragment();
        dialog.setListener(time -> timeView.setText(time))
                .show(getChildFragmentManager(), "dialog");
    }

    @OnClick(R.id.back_arrow)
    public void onBackArrowClick() {
        if (step == FIRST_STEP) {
            ((LiveGameActivity) activity).toDetailPage();
            selectPlayerAdapter.notifyDataSetChanged();
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
