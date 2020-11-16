package com.udacity.shoestore.ui.shoes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoesViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoesViewModel::class.java)) {
            return ShoesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}