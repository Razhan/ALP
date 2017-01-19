package com.alp.mvp.register;

import android.content.Intent;
import android.os.Bundle;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;
import com.alp.mvp.main.ui.MainActivity;

import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullScreen(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.button_login)
    public void onLoginClick() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
