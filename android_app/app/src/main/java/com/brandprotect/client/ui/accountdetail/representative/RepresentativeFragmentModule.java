package com.brandprotect.client.ui.accountdetail.representative;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.rxjava.RxJavaSchedulers;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RepresentativeFragmentModule {

    @Binds
    public abstract RepresentativeView view(RepresentativeFragment fragment);

    @Provides
    static RepresentativePresenter provideRepresentativePresenter(RepresentativeView view, TronNetwork tronNetwork,
            RxJavaSchedulers rxJavaSchedulers) {
        return new RepresentativePresenter(view, tronNetwork, rxJavaSchedulers);
    }
}