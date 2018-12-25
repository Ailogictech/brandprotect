package com.brandprotect.client.ui.representative;

import com.brandprotect.client.ui.mvp.IView;

public interface RepresentativeView extends IView {

    void showLoadingDialog();

    void displayRepresentativeInfo(int witnessCount, long highestVotes);

    void showServerError();
}
