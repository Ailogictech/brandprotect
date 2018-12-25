package com.brandprotect.client.ui.blockexplorer.block;

import com.brandprotect.client.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BlockModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {BlockFragmentModule.class})
    public abstract BlockFragment contributeBlockFragment();
}