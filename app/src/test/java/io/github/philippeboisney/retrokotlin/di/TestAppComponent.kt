package io.github.philippeboisney.retrokotlin.di

import dagger.Component
import io.github.philippeboisney.retrokotlin.base.BaseActivity
import io.github.philippeboisney.retrokotlin.base.BaseTest
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, RepositoryModule::class, ViewModelModule::class, TestRxJavaModule::class])
interface TestAppComponent {
    fun inject(baseTest: BaseTest)
}