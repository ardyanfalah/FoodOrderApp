package e.ardya.foodorderapp.data.net.inf

import e.ardya.foodorderapp.data.model.UserModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface IUser {

    @POST("login")
    fun loginPhone(@Body param: RequestBody): Observable<UserModel.ResponseLogin>

}