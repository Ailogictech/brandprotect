package com.brandprotect.client.ui.accountdetail.transaction;

import com.brandprotect.client.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TransactionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {TransactionFragmentModule.class})
    public abstract TransactionFragment contributeTransactionFragment();
}