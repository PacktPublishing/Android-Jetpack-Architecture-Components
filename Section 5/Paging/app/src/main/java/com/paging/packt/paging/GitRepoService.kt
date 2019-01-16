package com.paging.packt.paging

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoService {

    @GET("search/repositories?sort=stars")
    fun getRepositories(
        @Query("page") page: Int,
        @Query("per_page") size: Int,
        @Query("q") topic: String
    ): Call<GitRepoResponse>
}