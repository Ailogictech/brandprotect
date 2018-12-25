package com.brandprotect.client.ui.blockexplorer.overview;

import com.brandprotect.tronlib.dto.Stat;
import com.brandprotect.tronlib.dto.SystemStatus;
import com.brandprotect.tronlib.dto.TopAddressAccounts;
import com.brandprotect.client.ui.mvp.IView;

import java.util.List;

/**
 * Created by user on 2018. 5. 28..
 */

public interface OverviewView extends IView {

    void overviewBlockStatus(SystemStatus systemStatus);
    void overviewDataLoadSuccess(TopAddressAccounts topAddressAccounts);
    void overviewTransferPastHour(List<Stat> stats);
    void overviewTransactionPastHour(List<Stat> stats);
    void overviewAvgBlockSize(List<Stat> stats);
    void richListLoadSuccess(List<RichItemViewModel> viewModels);
    void showLoadingDialog();
    void showServerError();

}
