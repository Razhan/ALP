package com.alp.library.presenter;

import android.content.Context;

import com.alp.library.base.ui.MVPView;
import com.alp.library.exception.IErrorHandler;

import javax.inject.Inject;

public abstract class BasePresenter<V extends MVPView> implements IPresenter {

    protected V view;
    protected Context context;

    @Inject
    IErrorHandler errorHandler;

    public BasePresenter(Context context) {
        this.context = context;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void attachView(MVPView view) {
        this.view = (V) view;
    }

    @Override
    public void detachView() {
        context = null;
        view = null;
        unSubscribe();
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public V getView() {
        return view;
    }

    @Override
    public Context getContext() {
        return context;
    }

    protected void showErrorMessage(Throwable throwable) {
        String errorMsg = errorHandler.getErrorMessage(throwable);
        getView().showMessage(errorMsg);
    }

}
