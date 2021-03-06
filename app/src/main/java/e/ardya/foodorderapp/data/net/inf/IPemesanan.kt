package e.ardya.foodorderapp.data.net.inf

import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface IPemesanan {
//    @POST("PemesananAPI/create_pemesanan")
    @Multipart
    @POST("PemesananAPI/create_pemesanan")
    fun savePemesanan(
        @Part("data") data:RequestBody,
        @Part image:MultipartBody.Part
    ): Observable<TransaksiModel.ResponseType>

    @GET("PemesananAPI/{id}")
    fun getPemesanan(@Path("id") id:Int): Observable<TransaksiModel.ResponsePemesanan>

    @GET("PemesananDetailAPI")
    fun getDetailPemesanan(): Observable<TransaksiModel.ResponsePemesananDetail>
}