package e.ardya.foodorderapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import e.ardya.foodorderapp.base.ActionLiveData
import e.ardya.foodorderapp.base.BaseVM
import e.ardya.foodorderapp.data.model.UserModel
import e.ardya.foodorderapp.data.net.service.UserService
import e.ardya.foodorderapp.utils.helper.SessionHelper

class LoginViewModel: BaseVM() {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var actionGoToHome: ActionLiveData<Boolean> = ActionLiveData()

    fun login(){
        if(email.value != null && password.value != null){
            dataLoading.postValue(true)
            val data = UserModel.Login(email.value!!,password.value!!)
            UserService.login(data,{
                Log.d("hasil login=>",it.toString())
                SessionHelper.save("name", it?.nama_plgn ?: "-")
                SessionHelper.save("email", it?.email ?: "-")
                SessionHelper.save("phone", it?.no_hp ?: "-")
                SessionHelper.save("address", it?.alamat ?: "-")
                SessionHelper.save("id", it?.id_plgn ?: "-")
                dataLoading.postValue(false)
                actionGoToHome.postValue(actionGoToHome.value?.not() ?: true)
            },{
                dataLoading.postValue(false)
                toastMessage.postValue(it ?: "Login Gagal")
            })
        } else {
            dataLoading.postValue(false)
            toastMessage.postValue("Email dan Password tidak Boleh kosong")
            
        }
    }


}