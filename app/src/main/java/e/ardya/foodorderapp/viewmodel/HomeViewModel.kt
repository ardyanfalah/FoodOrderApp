package e.ardya.foodorderapp.viewmodel

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
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
import javax.security.auth.callback.Callback
import kotlin.collections.ArrayList

class HomeViewModel : BaseVM() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var mMenuModel: ArrayList<MenuModel.Data>? = ArrayList()
    var listMenu = MutableLiveData<ArrayList<MenuModel.Data>>()
    var listOrder = MutableLiveData<ArrayList<TransaksiModel.ItemTransaksi>>()
    var totalPrice = MutableLiveData<String>()
    var totalItem = MutableLiveData<String>()
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

    fun addOrder(menu:MenuModel.Data,position:Int){
        val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
        var currentMenu = listOrder.value
        val transaksiTemp:TransaksiModel.ItemTransaksi = TransaksiModel.ItemTransaksi()
        var isExist = false
        transaksiTemp.Id_Trx = 0
        transaksiTemp.Id_Admin = 0
        transaksiTemp.Id_Pelanggan = 2
        transaksiTemp.Id_Menu = menu.Id_Menu
        transaksiTemp.Harga_Menu = menu.Harga_Menu
        transaksiTemp.Jumlah_Makanan = 1
        transaksiTemp.Tanggal_Trx = currentDate
        transaksiTemp.Nama_Menu = menu.Nama_Menu
        if(!listOrder.value.isNullOrEmpty()){
            for(it in listOrder.value!!){
                if(it.Id_Menu==transaksiTemp.Id_Menu){
                    it.Jumlah_Makanan = it.Jumlah_Makanan?.plus(1)
                    isExist = true
                    break
                }
            }
            if(!isExist){
                listOrder.value?.add(transaksiTemp)
            }
            listMenu.value?.get(position)?.Jumlah_Menu = listMenu.value?.get(position)?.Jumlah_Menu?.plus(
                1
            )!!
        } else {
            listMenu.value?.get(position)?.Jumlah_Menu = listMenu.value?.get(position)?.Jumlah_Menu?.plus(
                1
            )!!
            listOrder.value?.add(transaksiTemp)
        }
        listOrder.postValue(listOrder.value)
        listMenu.postValue(listMenu.value)

//        if(listOrder.value?.isEmpty()!! || !listOrder.value?.contains(transaksiTemp)!!){
//            listOrder.value?.add(transaksiTemp)
//            listOrder.postValue(listOrder.value)
//        } else if(listOrder.value?.contains(transaksiTemp)!!){
//            listOrder.value?.find { it.Id_Menu == transaksiTemp.Id_Menu }?.Jumlah_Makanan=+3
//            listOrder.postValue(listOrder.value)
//        }


//        listOrder.value = ArrayList()
//        val array : ArrayList<MenuModel.Data> = listOrder.value
//        array.add(menu)
//        listOrder.postValue(array)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun removeOrder(menu:MenuModel.Data, position: Int, callback: () -> Unit){
        if(!listOrder.value.isNullOrEmpty()){
            for(it in listOrder.value!!){
                if(it.Id_Menu==menu.Id_Menu && it.Jumlah_Makanan!! > 1){
                    it.Jumlah_Makanan = it.Jumlah_Makanan?.minus(1)
                    listMenu.value?.get(position)?.Jumlah_Menu = listMenu.value?.get(position)?.Jumlah_Menu?.minus(
                        1
                    )!!
                    break
                } else {
                    listMenu.value?.get(position)?.Jumlah_Menu = 0
                    listOrder.value?.removeIf { itemTransaksi -> itemTransaksi.Id_Menu == menu.Id_Menu  }
                    break
                }
            }
            if(listOrder.value?.size == 0){
                callback.invoke()
            }
        }

        listOrder.postValue(listOrder.value)
        listMenu.postValue(listMenu.value)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun addOrderDetail(order:TransaksiModel.ItemTransaksi, position: Int) {
        listOrder.value?.get(position)?.Jumlah_Makanan = listOrder.value?.get(position)?.Jumlah_Makanan?.plus(1)
        listMenu.value?.get(position)?.Jumlah_Menu = listMenu.value?.find { it.Id_Menu == order.Id_Menu }?.Jumlah_Menu?.plus(1)!!

        listOrder.postValue(listOrder.value)
        listMenu.postValue(listMenu.value)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun removeOrderDetail(order: TransaksiModel.ItemTransaksi, position: Int,callback: () -> Unit){
        if(listOrder.value?.get(position)?.Jumlah_Makanan!! >  1){
            listOrder.value?.get(position)?.Jumlah_Makanan = listOrder.value!![position].Jumlah_Makanan?.minus(1)
            listMenu.value?.get(position)?.Jumlah_Menu = listMenu.value?.find { it.Id_Menu == order.Id_Menu }?.Jumlah_Menu?.minus(1)!!
        } else {
            listOrder.value?.remove(order )
            listMenu.value?.find { it.Id_Menu == order.Id_Menu }?.Jumlah_Menu = 0
        }
        listOrder.postValue(listOrder.value)
        listMenu.postValue(listMenu.value)
        if( listOrder.value != null && listOrder.value?.size!! <1){
            callback.invoke()
        }
    }

    fun addTotalPrice(price:String){
        totalPrice.value = "Rp. $price"
        totalPrice.postValue(totalPrice.value)

    }

    fun addTotalItem(total: String){
        totalItem.value = total
        totalItem.postValue(totalItem.value)
    }

     fun getOrderSize(): Int {
        return if(!listOrder.value.isNullOrEmpty()){
            listOrder.value?.size!!
        } else {
            0
        }
    }
}