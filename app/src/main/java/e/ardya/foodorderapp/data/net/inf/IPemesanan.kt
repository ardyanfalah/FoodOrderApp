package e.ardya.foodorderapp.data.net.inf

import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface IPemesanan {
    @POST("PemesananAPI")
    fun savePemesanan(@Body param: RequestBody): Observable<TransaksiModel.ResponseType>
}