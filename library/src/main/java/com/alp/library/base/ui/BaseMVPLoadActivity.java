package com.alp.library.base.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alp.library.R;
import com.alp.library.presenter.BasePresenter;
import com.alp.library.utils.LoadAnimationUtil;

public abstract class BaseMVPLoadActivity<T, P extends BasePresenter> extends BaseMVPActivity<P>
        implements MVPLoadView<T> {

    protected View loadingView;
    protected View contentView;
    protected TextView errorView;

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        loadingView = findViewById(R.id.loading_view);
        contentView = findViewById(R.id.content_view);
        errorView = (TextView)findViewById(R.id.error_view);

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
    public void showContent(T data) {
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