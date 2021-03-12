package e.ardya.foodorderapp.data.net.service

import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.TempatModel
import e.ardya.foodorderapp.data.net.RetrofitClient
import e.ardya.foodorderapp.data.net.inf.IMenu
import e.ardya.foodorderapp.data.net.inf.ITempat
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

object TempatService {

    fun getTempat(
        onSuccess: (ArrayList<TempatModel.Data>?) -> Unit,
        onError: (String?) -> Unit
    ){
        RetrofitClient.getInstance()
            ?.create(ITempat::class.java)
            ?.getTempat()
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

    fun getTempatAvailable(
        onSuccess: (Boolean?) -> Unit,
        onError: (String?) -> Unit
    ){
        RetrofitClient.getInstance()
            ?.create(ITempat::class.java)
            ?.getEmptyPlace()
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