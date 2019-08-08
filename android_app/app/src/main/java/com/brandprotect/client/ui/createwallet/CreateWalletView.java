package com.brandprotect.client.ui.createwallet;

import android.support.annotation.NonNull;

import com.brandprotect.client.ui.mvp.IView;

public interface CreateWalletView extends IView {

    void createdWallet(@NonNull byte[] aesKey);

    void passwordError();

    void registerWalletError();
}
