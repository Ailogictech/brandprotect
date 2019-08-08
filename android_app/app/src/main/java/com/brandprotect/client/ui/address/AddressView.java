package com.brandprotect.client.ui.address;

import android.support.annotation.Nullable;

import com.brandprotect.client.ui.mvp.IView;

public interface AddressView extends IView {

    void addressResult(@Nullable AddressPresenter.AddressInfo addressInfo);

}
