package com.android.codingtask


import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*



interface RestService {

    @GET("repositories?q=created:>2017-10-22&sort=stars&order=desc")
    fun getGitRepos(): Call<JsonObject>

    @GET("repositories?q=created:>2017-10-22&sort=stars&order=desc")
    fun getGitReposByPage(@Query("page") page:Int): Call<JsonObject>
}