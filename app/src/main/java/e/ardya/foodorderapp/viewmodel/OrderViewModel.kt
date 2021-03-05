package e.ardya.foodorderapp.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import e.ardya.foodorderapp.base.BaseVM
import e.ardya.foodorderapp.data.model.RatingModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.data.net.service.PemesananService
import e.ardya.foodorderapp.data.net.service.RatingService
import e.ardya.foodorderapp.utils.helper.SessionHelper
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class OrderViewModel : BaseVM() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    var mOrderModel: ArrayList<TransaksiModel.PemesananWithDetail>? = ArrayList()

    var listOrder = MutableLiveData<ArrayList<TransaksiModel.PemesananWithDetail>>()
    var listMenu = MutableLiveData<ArrayList<TransaksiModel.DetailMenuPemesanan>>()

    init {
    }

    fun getOrder() {
        dataLoading.postValue(true)
        Log.d("Session id=>",SessionHelper["id",0].toString())
        PemesananService.getPemesanan(
            SessionHelper["id",0],
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendRating(callbackSuccess: () -> Unit, callbackFailed: () -> Unit) {
        var rating= ArrayList<RatingModel.Rating>()
        val currentTime = Calendar.getInstance().time
        val timeStamp: String = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneOffset.UTC)
            .format(Instant.now())
        listMenu.value!!.forEach { menu->
            var temp=RatingModel.Rating(
                id_rating = 0,
                id_plgn = 1,
                id_menu = menu.id_menu!!,
                catatan = (if(menu.catatan != null) menu.catatan else "").toString(),
                nilai = if(menu.rating != null) menu.rating!!.toLong() else 0,
                waktu_rekam= timeStamp
            )
            rating.add(temp)
        }


        val json = Gson().toJson(rating)
        Log.d("text=>",json)
        dataLoading.postValue(true)

        RatingService.sendRating(
            json,
            {
                dataLoading.postValue(false)

                callbackSuccess.invoke()
                Log.d("menu success=>",it.toString())
            },
            {
                dataLoading.postValue(false)
                callbackFailed.invoke()
                Log.d("menu fail=>",it.toString())
            }
        )
    }

//    fun getListRating(menus: ArrayList<TransaksiModel.DetailMenuPemesanan>,callbackSuccess: () -> Unit, callbackFailed: () -> Unit){
//        if(menus.size > 0){
//
//        } else {
//            callbackFailed.invoke()
//            listMenu.postValue(listMenu.value)
//        }
//    }

}
