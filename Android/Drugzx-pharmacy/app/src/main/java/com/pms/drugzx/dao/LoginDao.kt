package com.pms.drugzx.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pms.drugzx.datamodels.Login

class LoginDao {
    private var loginUser = Login("", "")
    // MutableLiveData is from the Architecture Components Library
    // LiveData can be observed for changes
    private val loginLiveData = MutableLiveData<Login>()

    init {
        // Immediately connect the now empty quoteList
        // to the MutableLiveData which can be observed
        loginLiveData.value = loginUser
    }

    fun setLoginInfo(login: Login) {
        loginUser=login
        // After adding a quote to the "database",
        // update the value of MutableLiveData
        // which will notify its active observers
        loginLiveData.value = loginUser
    }

    // Casting MutableLiveData to LiveData because its value
    // shouldn't be changed from other classes
    fun getLoginInfo() = loginLiveData as LiveData<Login>
}