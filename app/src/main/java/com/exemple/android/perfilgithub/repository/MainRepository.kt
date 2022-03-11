package com.exemple.android.perfilgithub.repository

import com.exemple.android.perfilgithub.rest.RetrofitService
import retrofit2.http.GET

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun BuscarDados(nomeUsuario: String) = retrofitService.BuscarDados(nomeUsuario)

}