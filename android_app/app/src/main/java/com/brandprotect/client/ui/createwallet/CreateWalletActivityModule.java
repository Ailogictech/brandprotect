package com.brandprotect.client.ui.createwallet;

import com.brandprotect.client.common.CustomPreference;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CreateWalletActivityModule {

    @Binds
    public abstract CreateWalletView view(CreateWalletActivity createWalletActivity);

    @Provides
    static CreateWalletPresenter provideCreateWalletPresenter(CreateWalletView createWalletView,
            Tron tron, RxJavaSchedulers rxJavaSchedulers, CustomPreference customPreference) {
        return new CreateWalletPresenter(createWalletView, tron, rxJavaSchedulers, customPreference);
    }
}
