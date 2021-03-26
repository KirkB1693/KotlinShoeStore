package com.example.shoestore.shoeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel() : ViewModel() {

    private lateinit var shoeList: MutableList<Shoe>

    private val _shoeList = MutableLiveData(mutableListOf<Shoe>())
    val shoeListForDisplay: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        shoeList = mutableListOf()
    }

    fun addShoeToList(shoe: Shoe) {
        shoeList.add(shoe)
        _shoeList.value = shoeList
    }

}