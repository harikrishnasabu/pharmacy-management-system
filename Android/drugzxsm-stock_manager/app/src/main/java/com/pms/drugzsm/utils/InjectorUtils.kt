package com.pms.drugzsm.utils

import com.pms.drugzsm.repo.LoginRepo
import com.pms.drugzsm.data.FakeDatabase
import com.pms.drugzsm.repo.ProductsRepo

object InjectorUtils {

    // This will be called from QuotesActivity
    fun provideLoginRepository(): LoginRepo {
        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val loginRepository =
            LoginRepo.getInstance(FakeDatabase.getInstance().userDao)
        return loginRepository
    }

    fun provideProductsRepository(): ProductsRepo {
        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val productsRepo =
            ProductsRepo.getInstance(FakeDatabase.getInstance().productDao)
        return productsRepo
    }
}