package com.training.nicklos.vmcolorlist.di

import android.app.Application
import com.training.nicklos.vmcolorlist.ColorListApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        AndroidInjectionModule::class,
        ActivityBuilderModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: ColorListApplication)
}