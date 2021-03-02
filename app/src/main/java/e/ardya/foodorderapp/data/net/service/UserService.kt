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
import org.json.JSONObject

object UserService {

    fun login(
        username: String,
        email: String,
        onSuccess: (UserModel.Login?) -> Unit,
        onError: (String?) -> Unit
    ) {
        val jo = JSONObject()
        jo.put("username", username)
        jo.put("email", email)
        var body = ""
        body = Gson().toJson(jo)
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body)

        RetrofitClient.getInstance()
            ?.create(IUser::class.java)
            ?.loginPhone(requestBody)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({
//                if (it.success == true)
//                    onSuccess(it?.data)
//                else
//                    onError(it.messages)
            }, {
                onError(it.message)
            })?.let {
                CompositeDisposable().add(
                    it
                )
            }
    }

}