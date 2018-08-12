package io.github.philippeboisney.retrokotlin.di

import dagger.Module
import dagger.Provides
import io.github.philippeboisney.retrokotlin.api.GithubApiService
import io.github.philippeboisney.retrokotlin.repository.UserRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(githubApiService: GithubApiService) = UserRepository(githubApiService)

}