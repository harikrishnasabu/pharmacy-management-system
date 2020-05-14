package com.pms.drugzsm.repo

import com.pms.drugzsm.datamodels.Product
import com.pms.drugzsm.dao.ProductDao

class ProductsRepo constructor( val productsDao: ProductDao) {


    fun setProducts(products:List<Product>){
        productsDao.setproductList(products)
    }
    //    fun getProducts()=productDao.getProductList()
    fun getProducts()=productsDao.getFakeProductList()

    companion object {
        // Singleton instantiation
        @Volatile private var instance: ProductsRepo? = null

        fun getInstance(productsDao: ProductDao) =
            instance ?: synchronized(this) {
                instance
                    ?: ProductsRepo(productsDao).also { instance = it }
            }
    }
}