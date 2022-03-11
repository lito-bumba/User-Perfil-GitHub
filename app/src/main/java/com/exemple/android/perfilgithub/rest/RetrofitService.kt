package com.exemple.android.perfilgithub.rest

import com.exemple.android.perfilgithub.model.GitHubReponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val nome = "lazaro-manuel"

interface RetrofitService {

    @GET("users/{nomeUsuario}")

    fun BuscarDados(@Path("nomeUsuario") nomeUsuario: String): Call<GitHubReponse>

    companion object {

        private val retrofitService: RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)

        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }

    }
}