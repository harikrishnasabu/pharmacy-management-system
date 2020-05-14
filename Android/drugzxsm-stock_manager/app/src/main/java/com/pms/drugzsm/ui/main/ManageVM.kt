package com.pms.drugzsm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pms.drugzsm.datamodels.api.Product
import com.pms.drugzsm.datamodels.api.Products
import com.pms.drugzsm.datamodels.api.Suppliers
import com.pms.drugzsm.datamodels.api.Supplier
import com.pms.drugzsm.repo.MainRepository

class ManageVM: ViewModel() {
    var _updateProduct: MutableLiveData<Products> = MutableLiveData()
    var _updateSupplier:MutableLiveData<Suppliers> = MutableLiveData()
    var _productList: LiveData<List<Products>> = MutableLiveData()
    var _supplierList:LiveData<List<Suppliers>> = MutableLiveData()
    fun addProduct(product: Product) {
        MainRepository.addProduct(product)

    }

    fun refreshProducts(){
        _productList= MainRepository.getProducts()
    }
    fun refreshSuppliers(){
        _supplierList=MainRepository.getSuppliers()
    }
    fun addSupplier(supplier: Supplier) {
MainRepository.addSupplier(supplier)
    }

    fun updateProduct(product: Products, pId: Int) {
MainRepository.updateProduct(product,pId)
    }

    fun updateSupplier(supplierId: Int, supplier: Suppliers) {
MainRepository.updateSupplier(supplierId,supplier)
    }






    fun updateThisProduct(product: Products) {
        val update=product
MainRepository._updateProduct.value=product
        if (_updateProduct.value == update) {
            return
        }
        _updateProduct.value = update
println("updateThisProduct : MANAGEVM")
    }

    fun updateThisSupplier(supplier: Suppliers) {
        val update=supplier
        MainRepository._updateSupplier.value=supplier
        if (_updateSupplier.value == update) {
            return
        }
        _updateSupplier.value = update

    }
fun getProductForUpdate():Products?{
    return MainRepository._updateProduct.value
}
    fun getSupplierForUpdate():Suppliers?{
        return MainRepository._updateSupplier.value
    }

    fun deleteThisProduct(pId: Int) {
        MainRepository.deleteProduct(pId)
    }

    fun deleteThisSupplier(supplierId: Int) {
        MainRepository.deleteSupplier(supplierId)
    }


}