package com.brandprotect.client.ui.mytransfer;

import com.brandprotect.client.ui.mvp.IView;

/**
 * Created by user on 2018. 5. 17..
 */

public interface TransferView extends IView {

    void transferDataLoadSuccess(long total);

    void showLoadingDialog();

    void showServerError();
}
