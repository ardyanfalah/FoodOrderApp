package e.ardya.foodorderapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseVM:ViewModel() {

    val dataLoading = MutableLiveData<Boolean>().apply { value = false }
    val toastMessage = MutableLiveData<String>()
}