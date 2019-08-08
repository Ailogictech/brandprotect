package com.brandprotect.client.ui.token.overview;

import android.support.annotation.NonNull;

import com.brandprotect.tronlib.dto.Token;
import com.brandprotect.client.ui.mvp.IView;

public interface OverviewView extends IView {

    void tokenInfoLoadSuccess(@NonNull Token token);
    void showLoadingDialog();
    void showServerError();
}
