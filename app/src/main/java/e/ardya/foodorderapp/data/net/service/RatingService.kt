package e.ardya.foodorderapp.data.net.service

import com.google.gson.Gson
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.net.RetrofitClient
import e.ardya.foodorderapp.data.net.inf.IMenu
import e.ardya.foodorderapp.data.net.inf.IPemesanan
import e.ardya.foodorderapp.data.net.inf.IRating
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

object RatingService {

    fun getRatingRekomendasi(
        id:Int,
        onSuccess: (ArrayList<MenuModel.Data>?) -> Unit,
        onError: (String?) -> Unit
    ){
        RetrofitClient.getInstance()
            ?.create(IRating::class.java)
            ?.getRatingRekomendasi(id)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({
                if (it.success)
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

    fun sendRating(
        ratings: String,
        onSuccess: (String?) -> Unit,
        onError: (String?) -> Unit
    ) {
        var body = ""
//        body = Gson().toJson(ratings)
        body = ratings
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body)
        RetrofitClient.getInstance()
            ?.create(IRating::class.java)
            ?.postRating(requestBody)
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

}