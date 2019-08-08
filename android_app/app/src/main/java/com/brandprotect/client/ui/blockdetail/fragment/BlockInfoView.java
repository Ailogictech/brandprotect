package com.brandprotect.client.ui.blockdetail.fragment;

import android.support.annotation.NonNull;

import com.brandprotect.tronlib.dto.Block;
import com.brandprotect.client.ui.mvp.IView;

public interface BlockInfoView extends IView {
    void showLoadingDialog();
    void showServerError();
    void finishLoading(@NonNull Block block);
}
