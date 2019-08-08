package com.brandprotect.client.ui.mytransfer;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MyTransferActivityModule {

    @Binds
    public abstract TransferView view(TransferActivity transferActivity);

    @Provides
    static TransferPresenter provideTransferPresenter(TransferView transferView, Tron tron,
            TronNetwork tronNetwork, RxJavaSchedulers rxJavaSchedulers) {
        return new TransferPresenter(transferView, tron, tronNetwork, rxJavaSchedulers);
    }
}
