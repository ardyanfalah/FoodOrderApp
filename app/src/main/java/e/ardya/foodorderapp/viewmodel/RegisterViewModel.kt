package e.ardya.foodorderapp.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.ardya.foodorderapp.activity.MainActivity
import e.ardya.foodorderapp.base.BaseVM

class RegisterViewModel:BaseVM() {
    var mName: MutableLiveData<String> = MutableLiveData()
    var mPassword:  MutableLiveData<String> = MutableLiveData()
    var mAddress:  MutableLiveData<String> = MutableLiveData()
    var mEmail:  MutableLiveData<String> = MutableLiveData()

}