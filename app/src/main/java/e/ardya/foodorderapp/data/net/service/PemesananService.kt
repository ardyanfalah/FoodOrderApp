package e.ardya.foodorderapp.data.net.service

import com.google.gson.Gson
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.data.net.RetrofitClient
import e.ardya.foodorderapp.data.net.inf.IMenu
import e.ardya.foodorderapp.data.net.inf.IPemesanan
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

object PemesananService {
    fun sendOrder(
        orders: String,
        onSuccess: (String?) -> Unit,
        onError: (String?) -> Unit
    ) {
        var body = ""
        body = Gson().toJson(orders)
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
                    onError(it.messages)
            }, {
                onError(it.message)
            })?.let {
                CompositeDisposable().add(
                    it
                )
            }
    }

    fun getPemesanan(
        onSuccess: (ArrayList<TransaksiModel.HeaderPemesanan>?) -> Unit,
        onError: (String?) -> Unit
    ){
        RetrofitClient.getInstance()
            ?.create(IPemesanan::class.java)
            ?.getPemesanan()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({
                if (it.success)
                    onSuccess(it?.data)
                else
                    onError(it.messages)
            }, {
                onError(it.message)
            })?.let {
                CompositeDisposable().add(
                    it
                )
            }
    }

    fun getPemesananDetail(
        onSuccess: (ArrayList<TransaksiModel.DetailMenuPemesanan>?) -> Unit,
        onError: (String?) -> Unit
    ){
        RetrofitClient.getInstance()
            ?.create(IPemesanan::class.java)
            ?.getDetailPemesanan()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({
                if (it.success)
                    onSuccess(it?.data)
                else
                    onError(it.messages)
            }, {
                onError(it.message)
            })?.let {
                CompositeDisposable().add(
                    it
                )
            }
    }

}