package e.ardya.foodorderapp.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.ardya.foodorderapp.activity.MainActivity
import e.ardya.foodorderapp.base.ActionLiveData
import e.ardya.foodorderapp.base.BaseVM
import e.ardya.foodorderapp.data.model.UserModel
import e.ardya.foodorderapp.data.net.service.UserService
import e.ardya.foodorderapp.utils.helper.SessionHelper

class RegisterViewModel:BaseVM() {
    var mName: MutableLiveData<String> = MutableLiveData()
    var mPassword:  MutableLiveData<String> = MutableLiveData()
    var mAddress:  MutableLiveData<String> = MutableLiveData()
    var mEmail:  MutableLiveData<String> = MutableLiveData()
    var actionGotoLogin: ActionLiveData<Boolean> = ActionLiveData()

    fun register(){
        dataLoading.postValue(true)
        if(!mName.value.isNullOrEmpty() && !mPassword.value.isNullOrEmpty() && !mAddress.value.isNullOrEmpty() && !mEmail.value.isNullOrEmpty()){
            val data = UserModel.User(
                mAddress.value,
                mEmail.value,
                0,
                mName.value,
                "",
                mPassword.value,
                "Active"
            )
            UserService.register(data,{
                dataLoading.postValue(false)
                actionGotoLogin.postValue(actionGotoLogin.value?.not() ?: true)
            },{
                dataLoading.postValue(false)
                toastMessage.postValue(it ?: "Register Gagal")
            })
        } else {
            dataLoading.postValue(false)
            toastMessage.postValue("Semua form harus diisi")
        }
    }

    fun goToLogin(){
        actionGotoLogin.postValue(actionGotoLogin.value?.not() ?: true)

    }

}