package com.udacity.shoestore.ui.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel : ViewModel(){
    private val shoelist = mutableListOf<Shoe>()
    private val _shoes = MutableLiveData<List<Shoe>>()
     val shoes : LiveData<List<Shoe>>
        get() = _shoes

    fun addShoe(shoe:Shoe){
        shoelist.add(shoe)
        notifyObserver()
    }

    fun resetData() {
        shoelist.clear()
        notifyObserver()
    }

    private fun notifyObserver(){
        _shoes.value = shoelist
    }
}