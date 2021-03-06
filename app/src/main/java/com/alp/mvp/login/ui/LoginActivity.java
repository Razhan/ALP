package com.alp.mvp.login.ui;

import android.content.Intent;
import android.os.Bundle;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;
import com.alp.mvp.main.ui.MainActivity;
import com.alp.mvp.register.RegisterActivity;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullScreen(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.to_tegister_wrapper)
    public void onRegisterClick() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    @OnClick(R.id.button_login)
    public void onLoginClick() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
