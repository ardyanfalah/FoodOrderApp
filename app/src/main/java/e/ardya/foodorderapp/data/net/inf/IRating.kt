package e.ardya.foodorderapp.data.net.inf

import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.RatingModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IRating {
    @GET("RatingAPI")
    fun getRating(): Observable<RatingModel.ResponseRating>

    @GET("RatingAPI/rekomendasi")
    fun getRatingRekomendasi(): Observable<MenuModel.ResponseType>

    @POST("RatingAPI")
    fun postRating(@Body param: RequestBody): Observable<RatingModel.ResponseType>
}