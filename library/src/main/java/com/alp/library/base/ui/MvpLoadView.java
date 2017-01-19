package com.alp.library.base.ui;

import android.support.annotation.UiThread;

public interface MVPLoadView<T> extends MVPView {

    @UiThread
    public void showLoading(boolean pullToRefresh);

    @UiThread
    public void showContent(T data);

    @UiThread
    public void showError(String errorMessage, boolean pullToRefresh);

    @UiThread
    public void loadData(boolean pullToRefresh);

}
