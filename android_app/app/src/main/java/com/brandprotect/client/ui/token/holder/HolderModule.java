package com.brandprotect.client.ui.token.holder;

import com.brandprotect.client.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HolderModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {HolderFragmentModule.class})
    public abstract HolderFragment contributeHolderFragment();
}