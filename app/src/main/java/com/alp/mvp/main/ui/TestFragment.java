package com.alp.mvp.main.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.TextView;

import com.alp.library.base.ui.BaseFragment;
import com.alp.mvp.R;

import butterknife.BindView;

public class TestFragment extends BaseFragment {

    private static final String KEY_TEXT = "test";

    @BindView(R.id.test_text)
    TextView testText;

    public static Fragment newInstance(String text) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_test;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        String str = getArguments().getString(KEY_TEXT);
        testText.setText(str);
    }

}
