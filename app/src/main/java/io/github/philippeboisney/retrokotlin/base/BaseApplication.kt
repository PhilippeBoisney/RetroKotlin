package io.github.philippeboisney.retrokotlin.base

import android.app.Application
import io.github.philippeboisney.retrokotlin.BASE_URL
import io.github.philippeboisney.retrokotlin.di.*

open class BaseApplication : Application() {

    // FOR DATA
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        this.appComponent = this.initDagger()
    }

    // --- Dependencies injection ---

    protected open fun initDagger(): AppComponent
            = DaggerAppComponent.builder()
            .netModule(NetModule(BASE_URL))
            .repositoryModule(RepositoryModule())
            .rxJavaModule(RxJavaModule())
            .build()
}