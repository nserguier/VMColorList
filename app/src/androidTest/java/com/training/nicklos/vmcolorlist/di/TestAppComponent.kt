package com.training.nicklos.vmcolorlist.di

import android.app.Application
import com.training.nicklos.vmcolorlist.TestColorListApp
import com.training.nicklos.vmcolorlist.ui.ScreenRotatingRobot
import com.training.nicklos.vmcolorlist.ui.colorlist.ColorListTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Version of [AppComponent] for instrumented tests.
 * Uses module specifically for tests.
 */
@Singleton
@Component(modules = arrayOf(
        TestAppModule::class,
        AndroidInjectionModule::class,
        ActivityBuilderModule::class))
interface TestAppComponent: AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(testApp: TestColorListApp)

    fun inject(colorListTest: ColorListTest)

    fun inject(robot: ScreenRotatingRobot)
}