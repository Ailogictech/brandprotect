package com.brandprotect.client.ui.address;

import com.brandprotect.client.rxjava.RxJavaSchedulers;
import com.brandprotect.client.tron.Tron;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AddressActivityModule {

    @Binds
    public abstract AddressView view(AddressActivity addressActivity);

    @Provides
    static AddressPresenter provideAddressPresenter(AddressView addressView, Tron tron,
            RxJavaSchedulers rxJavaSchedulers) {
        return new AddressPresenter(addressView, tron, rxJavaSchedulers);
    }
}
