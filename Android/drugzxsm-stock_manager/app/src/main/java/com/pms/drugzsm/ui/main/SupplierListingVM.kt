package com.pms.drugzsm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pms.drugzsm.datamodels.api.Supplier
import com.pms.drugzsm.datamodels.api.Suppliers
import com.pms.drugzsm.repo.MainRepository

class SupplierListingVM: ViewModel() {
     var _suppliers:LiveData<List<Suppliers>> = MutableLiveData()
    var _supplier:MutableLiveData<Suppliers> = MutableLiveData()
    var _searchSupplier:LiveData<List<Suppliers>> = MutableLiveData()
fun setSelectedSupplier(supplier: Suppliers){
    _supplier.value=supplier
    MainRepository._selectedSupplier=supplier
    println("SELECTED"+supplier)
}

    fun getSuppliersList(): LiveData<List<Suppliers>> {
        _suppliers= MainRepository.getSuppliers()
        return _suppliers   }

    fun searchSupplier(search: String) {

        _searchSupplier= MainRepository.searchSuppliers(search)
    }
}