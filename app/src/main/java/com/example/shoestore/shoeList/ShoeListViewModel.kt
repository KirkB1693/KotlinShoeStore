package com.example.shoestore.shoeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel() : ViewModel() {

    private val _shoeList = MutableLiveData(mutableListOf<Shoe>())
    val shoeListForDisplay: LiveData<MutableList<Shoe>>
        get() = _shoeList

    fun addShoeToList(shoe: Shoe) {
        _shoeList.value?.add(shoe)
    }

}