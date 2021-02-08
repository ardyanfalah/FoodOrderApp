package e.ardya.foodorderapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import e.ardya.foodorderapp.base.BaseVM
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.data.net.service.MenuService
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : BaseVM() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var mMenuModel: ArrayList<MenuModel.Data>? = ArrayList()
    var listMenu = MutableLiveData<ArrayList<MenuModel.Data>>()
    var listOrder = MutableLiveData<ArrayList<MenuModel.Data>>()
    var totalPrice = MutableLiveData<String>()
    init {
        listOrder.value = ArrayList()
    }
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

    fun sendOrder(){
        val jsonTemp: String? = Gson().toJson(listOrder.value)
        Log.d("test json=>",jsonTemp.toString())

    }

    fun addOrder(menu:MenuModel.Data){
        val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
        var currentMenu = listOrder.value
        var transaksiTemp:TransaksiModel.ItemTransaksi = TransaksiModel.ItemTransaksi()
        transaksiTemp.Id_Trx = 0
        transaksiTemp.Id_Admin = 0
        transaksiTemp.Id_Pelanggan = 2
        transaksiTemp.Id_Menu = menu.Id_Menu
        transaksiTemp.Harga_Menu = menu.Harga_Menu
        transaksiTemp.Jumlah_Makanan = 1
        transaksiTemp.Tanggal_Trx = currentDate
        listOrder.value?.add(menu)
        listOrder.postValue(listOrder.value)
//        listOrder.value = ArrayList()
//        val array : ArrayList<MenuModel.Data> = listOrder.value
//        array.add(menu)
//        listOrder.postValue(array)
    }

    fun removeOrder(menu:MenuModel.Data){

    }

    fun addTotalPrice(price:String){
        totalPrice.value = "Rp. $price"
    }
}