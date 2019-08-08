package com.brandprotect.client.ui.blockdetail.fragment;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.rxjava.RxJavaSchedulers;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BlockInfoFragmentModule {

    @Binds
    public abstract BlockInfoView view(BlockInfoFragment fragment);

    @Provides
    static BlockInfoPresenter provideBlockInfoPresenter(BlockInfoView view, TronNetwork tronNetwork,
            RxJavaSchedulers rxJavaSchedulers) {
        return new BlockInfoPresenter(view, tronNetwork, rxJavaSchedulers);
    }
}