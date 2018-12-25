package com.brandprotect.client.ui.accountdetail.overview;

import com.brandprotect.client.ui.mvp.IView;

public interface AccountVoteView extends IView {

    void finishLoading(long totalVotes, long total);
    void showLoadingDialog();
    void showServerError();
}
