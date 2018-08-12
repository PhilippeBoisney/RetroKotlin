package io.github.philippeboisney.retrokotlin.di

import dagger.Component
import io.github.philippeboisney.retrokotlin.base.BaseActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class, RepositoryModule::class, ViewModelModule::class, RxJavaModule::class])
interface AppComponent {
    fun inject(baseActivity: BaseActivity)
}