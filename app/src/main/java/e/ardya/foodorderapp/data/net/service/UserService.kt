package e.ardya.foodorderapp.data.net.service

import com.google.gson.Gson
import e.ardya.foodorderapp.data.model.UserModel
import e.ardya.foodorderapp.data.net.RetrofitClient
import e.ardya.foodorderapp.data.net.inf.IUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody

object UserService {

    fun login(
        data: UserModel.Login,
        onSuccess: (UserModel.User?) -> Unit,
        onError: (String?) -> Unit
    ) {

        val body = Gson().toJson(data)
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body)

        RetrofitClient.getInstance()
            ?.create(IUser::class.java)
            ?.loginPhone(requestBody)
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

    fun register(
        data: UserModel.User,
        onSuccess: (UserModel.ResponseRegister?) -> Unit,
        onError: (String?) -> Unit
    ) {

        val body = Gson().toJson(data)
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body)

        RetrofitClient.getInstance()
            ?.create(IUser::class.java)
            ?.register(requestBody)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({
                if (it.success)
                    onSuccess(it)
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