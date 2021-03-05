package e.ardya.foodorderapp.data.net.inf

import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IPemesanan {
//    @POST("PemesananAPI/create_pemesanan")
    @POST("PemesananAPI/create_pemesanan")
    fun savePemesanan(@Body param: RequestBody): Observable<TransaksiModel.ResponseType>

    @GET("PemesananAPI/{id}")
    fun getPemesanan(@Path("id") id:Int): Observable<TransaksiModel.ResponsePemesanan>

    @GET("PemesananDetailAPI")
    fun getDetailPemesanan(): Observable<TransaksiModel.ResponsePemesananDetail>
}