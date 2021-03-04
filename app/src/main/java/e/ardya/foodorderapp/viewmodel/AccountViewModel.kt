package e.ardya.foodorderapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.ardya.foodorderapp.base.ActionLiveData
import e.ardya.foodorderapp.utils.helper.SessionHelper

class AccountViewModel : ViewModel() {

    var name: MutableLiveData<String> = MutableLiveData()
    var address: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var goToRegister: ActionLiveData<Boolean> =ActionLiveData()
    fun logout(){
        SessionHelper.clearAll()

    }
}