package com.brandprotect.client.ui.blockdetail.fragment;

import com.brandprotect.client.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BlockInfoModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {BlockInfoFragmentModule.class})
    public abstract BlockInfoFragment contributeBlockInfoFragment();
}