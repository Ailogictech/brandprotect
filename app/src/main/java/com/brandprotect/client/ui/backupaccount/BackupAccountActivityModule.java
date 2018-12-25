package com.brandprotect.client.ui.backupaccount;

import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;
import com.brandprotect.client.tron.WalletAppManager;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BackupAccountActivityModule {

    @Binds
    public abstract BackupAccountView view(BackupAccountActivity backupAccountActivity);

    @Provides
    static BackupAccountPresenter provideBackupAccountPresenter(BackupAccountView backupAccountView,
            Tron tron, WalletAppManager walletAppManager, RxJavaSchedulers rxJavaSchedulers) {
        return new BackupAccountPresenter(backupAccountView, tron, walletAppManager,
                rxJavaSchedulers);
    }
}
