package com.pms.drugzx.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pms.drugzx.datamodels.Product
import com.pms.drugzx.datamodels.api.Products
import com.pms.drugzx.repo.MainRepository
import com.pms.drugzx.utils.InjectorUtils


class ProductListingVM : ViewModel() {
    var _products: LiveData<List<Products>> = MutableLiveData()
    private val _selectedProducts: MutableLiveData<List<Products>> = MutableLiveData()
    var _searchProduct:LiveData<List<Products>> = MutableLiveData()


    fun setProducts(selectedProducts: List<Products>) {
        _selectedProducts.value=selectedProducts
        MainRepository._selectedProducts=selectedProducts
        println("_selectedProducts"+selectedProducts)
    }

    fun getProducts() {
        _products=MainRepository.getProducts()
    }
    fun getSelectedProducts(): List<Products>? {
        return  MainRepository._selectedProducts
    }

    fun searchchProduct(search: String) {

        _searchProduct= MainRepository.searchProducts(search)
    }
}