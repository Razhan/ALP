package com.alp.mvp.setting;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;

public class SettingActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected String setToolBarText() {
        return "Setting";
    }

}
