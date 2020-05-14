package com.pms.drugzx.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pms.drugzx.datamodels.Product

class ProductDao {
    // A fake database table
    private lateinit var  productsList:List<Product>
    // MutableLiveData is from the Architecture Components Library
    // LiveData can be observed for changes
    private val productListLiveData = MutableLiveData<List<Product>>()

    init {
        // Immediately connect the now empty quoteList
        // to the MutableLiveData which can be observed
        productListLiveData.value = getFakeProductList()
    }

    fun setproductList(products: List<Product>) {
        productsList=getFakeProductList()
        // After adding a quote to the "database",
        // update the value of MutableLiveData
        // which will notify its active observers
        productListLiveData.value = productsList
    }

    // Casting MutableLiveData to LiveData because its value
    // shouldn't be changed from other classes
    fun getProductList() = productListLiveData as LiveData<List<Product>>
    fun getFakeProductList(): List<Product> {
        val listmedicine = arrayListOf<Product>()
        listmedicine.add(
            Product(
                "Paracetamol",
                "1",
                false
            )

        )
        listmedicine.add(
            Product(
                "Amlodipine",
                "2",
                false
            )

        )
        listmedicine.add(
            Product(
                "Doxycycline",
                "3",
                false
            )

        )
        listmedicine.add(
            Product(
                "Paracetamol",
                "4",
                false
            )

        )
        listmedicine.add(
            Product(
                "Ibuprofen",
                "5",
                false
            )

        )
        listmedicine.add(
            Product(
                "Trazodone",
                "6",
                false
            )

        )
        listmedicine.add(
            Product(
                "Oxycodone",
                "7",
                false
            )

        )
        listmedicine.add(
            Product(
                "IbuprofenSDF",
                "8",
                false
            )

        )
        listmedicine.add(
            Product(
                "Trazodone",
                "9",
                false
            )

        )
        listmedicine.add(
            Product(
                "Oxycodone",
                "10",
                false
            )

        )
        return listmedicine
    }

}

