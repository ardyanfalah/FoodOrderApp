package e.ardya.foodorderapp.data.net.service

import com.google.gson.Gson
import com.google.gson.JsonArray
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.net.RetrofitClient
import e.ardya.foodorderapp.data.net.inf.IMenu
import e.ardya.foodorderapp.data.net.inf.IPemesanan
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

import kotlin.random.Random.Default.Companion

object PemesananService {
    fun sendOrder(
        orders:JsonArray,
        onSuccess: (String?) -> Unit,
        onError: (String?) -> Unit
    ){
        var body = ""
        body=Gson().toJson(orders)
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body)
        RetrofitClient.getInstance()
            ?.create(IPemesanan::class.java)
            ?.savePemesanan(requestBody)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({
                if (it.success == true)
                    onSuccess(it?.data)
                else
                    onError(it.message)
            }, {
                onError(it.message)
            })?.let {
                CompositeDisposable().add(
                    it
                )
            }
    }

}