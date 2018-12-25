package com.brandprotect.client.ui.blockexplorer.block;


import com.brandprotect.tronlib.dto.Blocks;
import com.brandprotect.client.ui.mvp.IView;

/**
 * Created by user on 2018. 5. 25..
 */

public interface BlockView extends IView {
    void blockDataLoadSuccess(Blocks blocks, boolean added);
    void showLoadingDialog();
    void showServerError();
}
