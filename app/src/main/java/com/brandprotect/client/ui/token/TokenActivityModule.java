package com.brandprotect.client.ui.token;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.WalletAppManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class TokenActivityModule {

    @Binds
    public abstract TokenView view(TokenActivity tokenActivity);

    @Provides
    static TokenPresenter provideTokenPresenter(TokenView view, Tron tron, TronNetwork tronNetwork,
            WalletAppManager walletAppManager, RxJavaSchedulers rxJavaSchedulers) {
        return new TokenPresenter(view, tron, tronNetwork, walletAppManager, rxJavaSchedulers);
    }
}
