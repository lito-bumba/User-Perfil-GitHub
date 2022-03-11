package com.exemple.android.perfilgithub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exemple.android.perfilgithub.repository.MainRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(this.repository) as T
        }else{
            throw IllegalArgumentException("View não encontrada")
        }
    }
}