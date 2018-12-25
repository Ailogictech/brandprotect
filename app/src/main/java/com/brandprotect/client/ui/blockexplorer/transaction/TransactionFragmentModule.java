package com.brandprotect.client.ui.blockexplorer.transaction;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.rxjava.RxJavaSchedulers;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class TransactionFragmentModule {

    @Binds
    public abstract TransactionView view(TransactionFragment fragment);

    @Provides
    static TransactionPresenter provideTransactionPresenter(TransactionView view, TronNetwork tronNetwork,
            RxJavaSchedulers rxJavaSchedulers) {
        return new TransactionPresenter(view, tronNetwork, rxJavaSchedulers);
    }
}