package com.brandprotect.client.ui.accountdetail.representative;

import com.brandprotect.client.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RepresentativeModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {RepresentativeFragmentModule.class})
    public abstract RepresentativeFragment contributeRepresentativeFragment();
}