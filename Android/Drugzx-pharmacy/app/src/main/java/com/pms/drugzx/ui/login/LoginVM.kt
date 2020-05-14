package com.pms.drugzx.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pms.drugzx.datamodels.Login
import com.pms.drugzx.datamodels.api.User
import com.pms.drugzx.repo.MainRepository
import com.pms.drugzx.utils.InjectorUtils

class LoginVM: ViewModel() {
    private val _login: MutableLiveData<Login> = MutableLiveData()

//var loginRepository= InjectorUtils.provideLoginRepository()
    //fun getLoginInfo() = loginRepository.getLoginInfo()

  //  fun setLoginInfo(login: Login) = loginRepository.setLoginInfo(login)
    val user: LiveData<User> = Transformations
        .switchMap(_login){
            MainRepository.getUser(it)
        }

    fun setLoginInfo(login: Login){
        val update = login
        if (_login.value == update) {
            return
        }
        _login.value = update
    }


    fun cancelJobs(){
        MainRepository.cancelJobs()
    }
}