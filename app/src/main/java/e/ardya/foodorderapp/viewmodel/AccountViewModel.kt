package e.ardya.foodorderapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.ardya.foodorderapp.base.ActionLiveData
import e.ardya.foodorderapp.base.BaseVM
import e.ardya.foodorderapp.utils.helper.SessionHelper

class AccountViewModel : BaseVM() {

    var name: MutableLiveData<String> = MutableLiveData()
    var address: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var logoutObserve : ActionLiveData<Boolean> =ActionLiveData()

    fun logout(){
        logoutObserve.postValue(true)
    }

    fun showAccount(){
        name.postValue(SessionHelper["nama",""])
        address.postValue(SessionHelper["address",""])
        email.postValue(SessionHelper["email",""])
    }
}