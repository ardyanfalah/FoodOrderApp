package e.ardya.foodorderapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.net.service.MenuService

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var mMenuModel: ArrayList<MenuModel.Data>? = ArrayList()

    fun getMenu(){
        MenuService.getMenu(
            {
                mMenuModel = it
                Log.d("menu success=>",it.toString())
            },
            {
                Log.d("menu success=>",it.toString())
            }
        )
    }
}