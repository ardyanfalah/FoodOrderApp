package e.ardya.foodorderapp.data.net.inf

import e.ardya.foodorderapp.data.model.UserModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface IUser {

    @POST("AuthAPI/login")
    fun loginPhone(@Body param: RequestBody): Observable<UserModel.ResponseLogin>

    @POST("AuthAPI/register")
    fun register(@Body param: RequestBody): Observable<UserModel.ResponseRegister>

}