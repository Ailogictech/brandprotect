package com.brandprotect.client.ui.blockexplorer.block;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.rxjava.RxJavaSchedulers;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BlockFragmentModule {

    @Binds
    public abstract BlockView view(BlockFragment fragment);

    @Provides
    static BlockPresenter provideBlockPresenter(BlockView view, TronNetwork tronNetwork,
            RxJavaSchedulers rxJavaSchedulers) {
        return new BlockPresenter(view, tronNetwork, rxJavaSchedulers);
    }
}