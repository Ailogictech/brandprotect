package com.brandprotect.client.ui.blockexplorer.account;

import com.brandprotect.client.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AccountModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {AccountFragmentModule.class})
    public abstract AccountFragment contributeAccountFragment();
}