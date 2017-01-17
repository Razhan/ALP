package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alp.library.base.ui.BaseFragment;
import com.alp.mvp.R;
import com.alp.mvp.adapter.SelectPlayerAdapter;
import com.alp.mvp.widget.SectionView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddScoreFragment extends BaseFragment {

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

    private List<String> list1;
    private SelectPlayerAdapter selectPlayerAdapter;

    public static Fragment newInstance() {
        return new AddScoreFragment();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_add_score;
    }

    @Override
    public void initData() {
        list1 = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            list1.add("");
        }
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        selectPlayerAdapter = new SelectPlayerAdapter(activity, list1);
        selectPlayerAdapter.setClickListener((view, pos, item) -> selectPlayerAdapter.updatePlayer(view));
        selectPlayerList.setLayoutManager(new LinearLayoutManager(activity));
        selectPlayerList.setAdapter(selectPlayerAdapter);

        periodView.setSectionCount(3);
    }

    @OnClick(R.id.time_wrapper)
    public void onClick() {
        PickerDialogFragment dialog = new PickerDialogFragment();
        dialog.setListener(time -> timeView.setText(time))
                .show(getChildFragmentManager(), "dialog");
    }

    public interface OnTimeSetListener {

        void onTimeSet(String time);

    }
}