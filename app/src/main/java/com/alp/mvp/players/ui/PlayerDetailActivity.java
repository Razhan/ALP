package com.alp.mvp.players.ui;

import android.os.Bundle;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;

import butterknife.OnClick;

public class PlayerDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTranslucentStatusBar(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_player_detail;
    }

    @OnClick(R.id.player_back)
    public void onClick() {
        onBackPressed();
    }
}
