package com.training.nicklos.vmcolorlist.di

import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditActivity
import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module to help Dagger know about the activities to inject with AndroidInjector
 * Place all activities here, add the FragmentBuilderModule if the activity will be creating fragments
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilderModule::class))
    abstract fun colorListActivity(): ColorListActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilderModule::class))
    abstract fun colorEditActivity(): ColorEditActivity
}