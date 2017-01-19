package com.alp.mvp.splash;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;
import com.alp.mvp.login.ui.LoginActivity;
import com.alp.mvp.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    private final static int ANIMATION_DURATION = 1300;

    @BindView(R.id.background)
    ImageView background;
    @BindView(R.id.blender)
    ImageView blender;
    @BindView(R.id.button_login)
    Button buttonLogin;
    @BindView(R.id.button_register)
    Button buttonRegister;
    @BindView(R.id.button_wrapper)
    LinearLayout buttonWrapper;
    @BindView(R.id.activity_splash)
    RelativeLayout splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullScreen(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        splash.post(this::animateBackground);
    }

    private void animateBackground() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(background, View.SCALE_X, 1.1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(background, View.SCALE_Y, 1.1f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(blender, View.ALPHA, 0.5f);
        ObjectAnimator transY = ObjectAnimator.ofFloat(buttonWrapper, View.TRANSLATION_Y, buttonWrapper.getHeight(), 0);

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new DecelerateInterpolator());
        set.playTogether(scaleX, scaleY, alpha, transY);
        set.setDuration(ANIMATION_DURATION);
        set.start();
    }

    @OnClick({R.id.button_login, R.id.button_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.button_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }

        finish();
    }

}
