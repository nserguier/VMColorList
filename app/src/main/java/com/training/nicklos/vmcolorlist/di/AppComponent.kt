package com.training.nicklos.vmcolorlist.di

import com.training.nicklos.vmcolorlist.ui.coloredit.ColorEditFragment
import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(target: ColorListFragment)
    fun inject(target: ColorEditFragment)
}