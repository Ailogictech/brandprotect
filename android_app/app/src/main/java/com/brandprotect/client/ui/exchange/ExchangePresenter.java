package com.brandprotect.client.ui.exchange;

import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.ui.mvp.BasePresenter;

public class ExchangePresenter extends BasePresenter<ExchangeView> {

    private Tron mTron;
    private RxJavaSchedulers mRxJavaSchedulers;

    public ExchangePresenter(ExchangeView view, Tron tron, RxJavaSchedulers rxJavaSchedulers) {
        super(view);
        this.mTron = tron;
        this.mRxJavaSchedulers = rxJavaSchedulers;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
