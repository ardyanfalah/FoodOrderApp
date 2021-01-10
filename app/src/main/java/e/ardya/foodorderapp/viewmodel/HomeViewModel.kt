package e.ardya.foodorderapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.ardya.foodorderapp.base.BaseVM
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.net.service.MenuService

class HomeViewModel : BaseVM() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var mMenuModel: ArrayList<MenuModel.Data>? = ArrayList()
    var listMenu = MutableLiveData<ArrayList<MenuModel.Data>>()

    fun getMenu(){
        dataLoading.postValue(true)

        MenuService.getMenu(
            {
                dataLoading.postValue(false)

                mMenuModel = it
                listMenu.postValue(it)
                Log.d("menu success=>",it.toString())
            },
            {
                dataLoading.postValue(false)

                Log.d("menu fail=>",it.toString())
            }
        )
    }
}