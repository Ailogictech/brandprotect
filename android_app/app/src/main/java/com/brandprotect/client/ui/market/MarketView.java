package com.brandprotect.client.ui.market;

import com.brandprotect.tronlib.dto.Market;
import com.brandprotect.client.ui.mvp.IView;

import java.util.List;

/**
 * Created by user on 2018. 5. 24..
 */

public interface MarketView extends IView {
    void marketDataLoadSuccess(List<Market> markets);
    void showLoadingDialog();
    void showServerError();
}
