package com.brandprotect.client.ui.importkey;

import com.brandprotect.client.ui.mvp.IView;

public interface ImportPrivateKeyView extends IView {

    void createdWallet();

    void passwordError();

    void registerWalletError();
}
