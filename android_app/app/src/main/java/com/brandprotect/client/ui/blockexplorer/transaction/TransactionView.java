package com.brandprotect.client.ui.blockexplorer.transaction;

import com.brandprotect.client.ui.mvp.IView;

public interface TransactionView extends IView {

    void finishLoading(long total);
    void showLoadingDialog();
    void showServerError();
}
