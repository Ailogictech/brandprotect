package com.brandprotect.client.ui.blockexplorer.transfer;

import com.brandprotect.client.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TransferModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {TransferFragmentModule.class})
    public abstract TransferFragment contributeTransferFragment();
}