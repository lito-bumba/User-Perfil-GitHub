package com.exemple.android.perfilgithub.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exemple.android.perfilgithub.model.GitHubReponse
import com.exemple.android.perfilgithub.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val dadosGitHub = MutableLiveData<GitHubReponse>()
    val MensagemErro = MutableLiveData<String>()

    fun BuscarDados(nome: String) {
        val pedido = repository.BuscarDados(nome)

        pedido.enqueue(object: Callback<GitHubReponse> {
            override fun onResponse(
                call: Call<GitHubReponse>,
                response: Response<GitHubReponse>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    dadosGitHub.postValue(response.body())
                } else {
                    MensagemErro.postValue("Não foi possível pegar os dados")
                }
            }

            override fun onFailure(call: Call<GitHubReponse>, t: Throwable) {
                MensagemErro.postValue(t.message)
                Log.i("Mensagem do Erro", t.message.toString())
            }
        })
    }

}