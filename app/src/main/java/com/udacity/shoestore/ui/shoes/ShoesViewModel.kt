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

    val name = MutableLiveData<String>()
    val size = MutableLiveData<String>()
    val company = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    fun resetValues(){
        name.value = ""
        size.value = ""
        company.value = ""
        description.value = ""
    }

    fun addShoe(){
        shoelist.add(Shoe(name.value!!, size.value!!.toDouble(), company.value!!, description.value!!))
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