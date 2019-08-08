package com.brandprotect.client.ui.accountdetail.representative;

import com.brandprotect.client.ui.accountdetail.representative.model.BaseModel;
import com.brandprotect.client.ui.mvp.IView;

import java.util.List;

public interface RepresentativeView extends IView {
    void dataLoadSuccess(List<BaseModel> viewModels);
    void showLoadingDialog();
    void showServerError();
}
