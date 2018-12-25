package com.brandprotect.client.ui.token;

import com.brandprotect.client.ui.mvp.IView;

import org.tron.protos.Protocol;

public interface TokenView extends IView {

    void showLoadingDialog();

    void showServerError();

    void finishLoading(int total, Protocol.Account account);

    void participateTokenResult(boolean result);

    void needLogin();
}
