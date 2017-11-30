package com.training.nicklos.vmcolorlist.di

import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditFragment
import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module to help Dagger know about the fragments to inject with AndroidInjector
 * Place all fragments here
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeColorListFragment(): ColorListFragment

    @ContributesAndroidInjector
    abstract fun contributeColorEditFragment(): ColorEditFragment
}