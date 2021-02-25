package e.ardya.foodorderapp.viewmodel

import android.os.Build
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
import e.ardya.foodorderapp.data.net.service.PemesananService
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class OrderViewModel : BaseVM() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    var mOrderModel: ArrayList<TransaksiModel.HeaderPemesanan>? = ArrayList()

    var listOrder = MutableLiveData<ArrayList<TransaksiModel.HeaderPemesanan>>()

    init {
    }

    fun getOrder() {
        dataLoading.postValue(true)

        PemesananService.getPemesanan(
            {
                dataLoading.postValue(false)
                mOrderModel = it
                listOrder.postValue(it)
                Log.d("menu success=>", it.toString())
            },
            {
                dataLoading.postValue(false)
                Log.d("menu fail=>", it.toString())
            }
        )
    }

}
