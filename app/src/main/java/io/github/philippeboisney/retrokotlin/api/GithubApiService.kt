package io.github.philippeboisney.retrokotlin.api

import io.github.philippeboisney.retrokotlin.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Observable<User>
}