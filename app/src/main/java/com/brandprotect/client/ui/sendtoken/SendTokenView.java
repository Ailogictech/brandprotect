package com.brandprotect.client.ui.sendtoken;

import com.brandprotect.client.ui.mvp.IView;

import org.tron.protos.Protocol;

public interface SendTokenView extends IView {

    void sendTokenResult(boolean result);

    void invalidPassword();

    void displayAccountInfo(Protocol.Account account);

    void invalidAddress();

    void connectionError();
}
