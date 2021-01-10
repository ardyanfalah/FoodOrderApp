package e.ardya.foodorderapp.data.net.inf

import e.ardya.foodorderapp.data.model.MenuModel
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface IMenu {
    @GET("ProductAPI")
    fun getData() : Observable<MenuModel.ResponseType>
}