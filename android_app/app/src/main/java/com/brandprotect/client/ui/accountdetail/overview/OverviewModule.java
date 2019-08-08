package com.brandprotect.client.ui.accountdetail.overview;

import com.brandprotect.client.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class OverviewModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {OverviewFragmentModule.class})
    public abstract OverviewFragment contributeOverviewFragment();
}