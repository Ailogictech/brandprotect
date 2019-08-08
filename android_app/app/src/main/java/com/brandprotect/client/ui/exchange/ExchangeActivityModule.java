package com.brandprotect.client.ui.exchange;

import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ExchangeActivityModule {

    @Binds
    public abstract ExchangeView view(ExchangeActivity exchangeActivity);

    @Provides
    static ExchangePresenter provideExchangePresenter(ExchangeView view, Tron tron, RxJavaSchedulers rxJavaSchedulers) {
        return new ExchangePresenter(view, tron, rxJavaSchedulers);
    }
}
