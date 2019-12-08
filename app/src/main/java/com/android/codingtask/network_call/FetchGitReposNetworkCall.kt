package com.android.codingtask.network_call

import android.app.Application
import com.android.codingtask.model.Repository
import com.android.codingtask.network.model.NetworkModelWithNoSession
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class FetchGitReposNetworkCall(application: Application) :
    NetworkModelWithNoSession<ArrayList<Repository>, Int, JsonObject>(application),
    Callback<JsonObject> {
    override fun execute(req: Int) {
        onDestroy()
        restServices.getGitReposByPage(req).let {
            call = it
            it.enqueue(this)
        }
    }

    override fun reExecute(): Boolean {
        getClone()?.let {
            it.enqueue(this)
            return true
        }
        return false
    }

    override fun onCleared() {
        onDestroy()
    }

    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
        if (response.isSuccessful) {
            val respBody = response.body()
            if(respBody != null && respBody.has("items")){
                val type = object : TypeToken<List<Repository>>() {}.type
                val repositoryList = Gson().fromJson<ArrayList<Repository>>(respBody.getAsJsonArray("items"), type)
                setValue(repositoryList)
            }else {
                setValue(null)
            }
        } else {
            setError(parseError(response))
        }
    }

    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
        if (!retry()) {
            setException(t)
        }
    }

    override fun retry(): Boolean {
        return false
    }
}