package com.brandprotect.client.ui.vote;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.WalletAppManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class VoteActivityModule {

    @Binds
    public abstract VoteView view(VoteActivity voteActivity);

    @Provides
    static VotePresenter provideVotePresenter(VoteView view, Tron tron, TronNetwork tronNetwork,
            WalletAppManager walletAppManager, RxJavaSchedulers rxJavaSchedulers) {
        return new VotePresenter(view, tron, tronNetwork, walletAppManager, rxJavaSchedulers);
    }
}
