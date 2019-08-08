package com.brandprotect.client.ui.backupaccount;

import android.support.annotation.NonNull;

import com.brandprotect.client.ui.mvp.IView;

public interface BackupAccountView extends IView {

    void displayAccountInfo(@NonNull String address, @NonNull String privateKey);

    void startMainActivity();
}
