package com.brandprotect.client.ui.blockexplorer.account;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.rxjava.RxJavaSchedulers;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AccountFragmentModule {

    @Binds
    public abstract AccountView view(AccountFragment fragment);

    @Provides
    static AccountPresenter provideAccountPresenter(AccountView view, TronNetwork tronNetwork,
            RxJavaSchedulers rxJavaSchedulers) {
        return new AccountPresenter(view, tronNetwork, rxJavaSchedulers);
    }
}