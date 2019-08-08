package com.brandprotect.client.ui.main;

import android.support.annotation.NonNull;

import com.brandprotect.tronlib.dto.CoinMarketCap;
import com.brandprotect.client.ui.main.dto.TronAccount;
import com.brandprotect.client.ui.mvp.IView;

public interface MainView extends IView {

    void displayAccountInfo(@NonNull TronAccount account);

    void setTronMarketInfo(CoinMarketCap coinMarketCap);

    void showInvalidPasswordMsg();

    void successCreateAccount();

    void successImportAccount();

    void failCreateAccount();

    void duplicatedAccount();

    void connectionError();

    void changePasswordResult(boolean result);

    void showChangePasswordDialog();
}
