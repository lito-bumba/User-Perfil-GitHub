package com.exemple.android.perfilgithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.exemple.android.perfilgithub.databinding.ActivityMainBinding
import com.exemple.android.perfilgithub.repository.MainRepository
import com.exemple.android.perfilgithub.rest.RetrofitService
import com.exemple.android.perfilgithub.viewmodel.MainViewModel
import com.exemple.android.perfilgithub.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private var user: String = "lito-bumba"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        viewModel.dadosGitHub.observe(this, Observer { dados ->
            binding.txtNome.text = dados.name
            binding.txtNomeUsuario.text = dados.login
            binding.txtStatus.text = dados.bio

            Glide.with(this)
                .load(dados.avatar_url)
                .override(150, 150)
                .into(binding.imageView)

        })

        viewModel.MensagemErro.observe(this, Observer { mensagem ->
            Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
        })

        binding.btnPesquisar.setOnClickListener {
            Toast.makeText(this, binding.txtPesquisar.text, Toast.LENGTH_SHORT).show()
            user = binding.txtPesquisar.text.toString()
            onResume()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.BuscarDados(user)
    }
}