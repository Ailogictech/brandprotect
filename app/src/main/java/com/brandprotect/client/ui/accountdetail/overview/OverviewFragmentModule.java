package com.brandprotect.client.ui.accountdetail.overview;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.rxjava.RxJavaSchedulers;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class OverviewFragmentModule {

    @Binds
    public abstract OverviewView view(OverviewFragment fragment);

    @Provides
    static OverviewPresenter provideOverviewPresenter(OverviewView view, TronNetwork tronNetwork,
            RxJavaSchedulers rxJavaSchedulers) {
        return new OverviewPresenter(view, tronNetwork, rxJavaSchedulers);
    }
}