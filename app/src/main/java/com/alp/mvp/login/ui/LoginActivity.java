package com.alp.mvp.login.ui;

import android.os.Bundle;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullScreen(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_register;
    }

}
