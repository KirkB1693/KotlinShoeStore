package com.example.shoestore.shoeList

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry

data class Shoe(var shoeName: String,
           var shoeCompany: String,
           var shoeSize: String,
           var shoeDescription: String) : Observable {
    constructor() : this("", "", "", "")

    private val propertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }


}