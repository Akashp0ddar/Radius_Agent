package com.example.radiusagent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.radiusagent.fragments.HomeViewModel
import com.example.radiusagent.repository.Repository

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("viewModel")
    }
}