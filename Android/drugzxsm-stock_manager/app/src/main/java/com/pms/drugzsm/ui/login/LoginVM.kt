package com.pms.drugzsm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pms.drugzsm.datamodels.Login
import com.pms.drugzsm.datamodels.api.User
import com.pms.drugzsm.repo.MainRepository

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
    fun getUser():User{
        return MainRepository._user
    }
    fun cancelJobs(){
        MainRepository.cancelJobs()
    }
}