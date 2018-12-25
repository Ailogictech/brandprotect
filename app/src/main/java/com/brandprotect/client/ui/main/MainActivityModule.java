package com.brandprotect.client.ui.main;

import com.brandprotect.tronlib.TronNetwork;
import com.brandprotect.client.common.CustomPreference;
import com.brandprotect.client.database.AppDatabase;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.WalletAppManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Binds
    public abstract MainView view(MainActivity mainActivity);

    @Provides
    static MainPresenter provideMainPresenter(MainView mainView, Tron tron, WalletAppManager walletAppManager,
            TronNetwork tronNetwork, RxJavaSchedulers rxJavaSchedulers, CustomPreference customPreference,
            AppDatabase appDatabase) {
        return new MainPresenter(mainView, tron, walletAppManager, tronNetwork, rxJavaSchedulers,
                customPreference, appDatabase);
    }
}
