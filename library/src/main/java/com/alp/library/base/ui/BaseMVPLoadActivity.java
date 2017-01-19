package com.alp.library.base.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alp.library.R;
import com.alp.library.presenter.BasePresenter;
import com.alp.library.utils.LoadAnimationUtil;

public abstract class BaseMVPLoadActivity<M, P extends BasePresenter> extends BaseMVPActivity<P>
        implements MVPLoadView<M> {

    protected View loadingView;
    protected View contentView;
    protected TextView errorView;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        loadingView = findViewById(R.id.loadingView);
        contentView = findViewById(R.id.contentView);
        errorView = (TextView)findViewById(R.id.errorView);

        if (loadingView == null) {
            throw new NullPointerException("Loading view is null!");
        }

        if (contentView == null) {
            throw new NullPointerException("Content view is null!");
        }

        if (errorView == null) {
            throw new NullPointerException("Error view is null!");
        }

        errorView.setOnClickListener(v -> onErrorViewClicked());
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        if (!pullToRefresh) {
            LoadAnimationUtil.showLoading(loadingView, contentView, errorView);
        }
    }

    @Override
    public void showContent() {
        LoadAnimationUtil.showContent(loadingView, contentView, errorView);
    }

    @Override
    public void showError(String errorMessage, boolean pullToRefresh) {
        if (pullToRefresh) {
            showToast(errorMessage);
        } else {
            errorView.setText(errorMessage);
            LoadAnimationUtil.showErrorView(loadingView, contentView, errorView);
        }
    }

    protected void onErrorViewClicked() {
        loadData(false);
    }

}