package com.brandprotect.client.ui.token.holder;

import com.brandprotect.client.ui.mvp.IView;

public interface HolderView extends IView {

    void finishLoading(long total);
    void showLoadingDialog();
    void showServerError();
}
