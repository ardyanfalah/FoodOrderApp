package e.ardya.foodorderapp.data.net.inf

import e.ardya.foodorderapp.data.model.TempatModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ITempat {

    @GET("TempatAPI")
    fun getTempat(): Observable<TempatModel.ResponseTempat>

    @GET("TempatAPI/count_empty")
    fun getEmptyPlace(): Observable<TempatModel.ResponseAvailability>

}