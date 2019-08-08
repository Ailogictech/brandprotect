package com.brandprotect.client.ui.blockexplorer.account;

import com.brandprotect.client.ui.mvp.IView;

public interface AccountView extends IView {

    void finishLoading(long total);
    void showLoadingDialog();
    void showServerError();
}
