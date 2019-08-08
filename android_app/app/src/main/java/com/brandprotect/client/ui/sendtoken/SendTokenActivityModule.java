package com.brandprotect.client.ui.sendtoken;

import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SendTokenActivityModule {

    @Binds
    public abstract SendTokenView view(SendTokenActivity sendTokenActivity);

    @Provides
    static SendTokenPresenter provideRepresentativePresenter(SendTokenView view, Tron tron,
            RxJavaSchedulers rxJavaSchedulers) {
        return new SendTokenPresenter(view, tron, rxJavaSchedulers);
    }
}
