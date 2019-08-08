package com.brandprotect.client.ui.token.transfer;

import com.brandprotect.client.ui.mvp.IView;

public interface TransferView extends IView {

    void finishLoading(long total);
    void showLoadingDialog();
    void showServerError();
}
