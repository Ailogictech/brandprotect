package com.brandprotect.client.ui.myaccount;

import com.brandprotect.client.database.AppDatabase;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.WalletAppManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MyAccountActivityModule {

    @Binds
    public abstract MyAccountView view(MyAccountActivity myAccountActivity);

    @Provides
    static MyAccountPresenter provideMyAccountPresenter(MyAccountView myAccountView, Tron tron,
            WalletAppManager walletAppManager, RxJavaSchedulers rxJavaSchedulers, AppDatabase appDatabase) {
        return new MyAccountPresenter(myAccountView, tron, walletAppManager, rxJavaSchedulers,
                appDatabase);
    }
}
