package com.brandprotect.client.ui.accountdetail.overview;

import android.support.annotation.NonNull;

import com.brandprotect.client.ui.main.dto.TronAccount;
import com.brandprotect.client.ui.mvp.IView;

public interface OverviewView extends IView {

    void showLoadingDialog();
    void showServerError();
    void finishLoading(@NonNull TronAccount account);
}
