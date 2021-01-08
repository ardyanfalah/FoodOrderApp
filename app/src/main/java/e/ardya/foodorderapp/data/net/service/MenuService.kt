package e.ardya.foodorderapp.data.net.service

import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.net.RetrofitClient
import e.ardya.foodorderapp.data.net.inf.IMenu
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

object MenuService {
    fun getMenu(
        onSuccess: (ArrayList<MenuModel.Data>?) -> Unit,
        onError: (String?) -> Unit
    ){
        RetrofitClient.getInstance()
            ?.create(IMenu::class.java)
            ?.getData()
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
}