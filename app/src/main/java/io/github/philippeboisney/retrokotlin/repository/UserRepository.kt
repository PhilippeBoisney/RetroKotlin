package io.github.philippeboisney.retrokotlin.repository

import io.github.philippeboisney.retrokotlin.api.GithubApiService

class UserRepository(private val githubApiService: GithubApiService) {

    fun getUser(userId: String) = this.githubApiService.getUser(userId)

}