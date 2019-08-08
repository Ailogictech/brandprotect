package com.brandprotect.client.ui.login;

import com.brandprotect.client.common.CustomPreference;
import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.WalletAppManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class LoginActivityModule {

    @Binds
    public abstract LoginView view(LoginActivity loginActivity);

    @Provides
    static LoginPresenter provideLoginPresenter(LoginView loginView, Tron tron,
            WalletAppManager walletAppManager, RxJavaSchedulers rxJavaSchedulers,
            CustomPreference customPreference) {
        return new LoginPresenter(loginView, tron, walletAppManager, rxJavaSchedulers, customPreference);
    }
}
