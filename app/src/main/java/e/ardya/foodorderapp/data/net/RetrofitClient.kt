package e.ardya.foodorderapp.data.net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

private const val PROTOCOL = "http://"
//private const val BASE_URL = "192.168.100.6"
//private const val BASE_URL = "192.168.100.10"
//private const val BASE_URL = "192.168.1.74"
private const val BASE_URL = "192.168.1.87"

private const val PORT_V1 = ":8080"
private const val PATH = "/food-order/"
private const val IMAGE = "/uploads/"
private var API = PROTOCOL + BASE_URL + PORT_V1 + PATH
var IMG = PROTOCOL + BASE_URL + PORT_V1 + PATH + IMAGE

object RetrofitClient {
    private var retrofitV1: Retrofit? = null
    fun getInstance(): Retrofit? {
        if (retrofitV1 == null) {
            val clientBuilder = OkHttpClient.Builder()
//            clientBuilder.addInterceptor(ResponseInterceptor())
            clientBuilder.readTimeout(30, TimeUnit.SECONDS)
            clientBuilder.connectTimeout(5, TimeUnit.SECONDS)
            clientBuilder.cache(null)
            retrofitV1 = Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder.build())
                .build()
        }
        return retrofitV1
    }
//    private class ResponseInterceptor : Interceptor {
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val response = getRequestBuilder(chain)
//            try {
//                val strJson = response.body().toString()
//                Log.d("response_retrofit", "$strJson")
////                val jsonObject = JSONObject(strJson)
////                //val data = jsonObject.getJSONObject("data").toString()
////                val data = if (jsonObject.has("success") && jsonObject.getBoolean("success")) {
////                    if (!jsonObject.isNull("data"))
////                        jsonObject.get("data").toString()
////                    else {
////                        JSONObject().put("message", jsonObject.getString("message")).toString()
////                    }
////                } else {
////                    JSONObject().put("message", jsonObject.getString("message")).toString()
////                }
////                val data = jsonObject.toString()
//                val contentType = response.body()?.contentType()
//                val body = ResponseBody.create(contentType, strJson ?: "")
//                return response.newBuilder().body(body).build()
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//            return response
//        }
//        private fun getRequestBuilder(chain: Interceptor.Chain): Response {
//            val original = chain.request()
//            val token = SessionHelper["token", ""]
//            // Request customization: add request headers
//            val requestBuilder = original.newBuilder()
//                .apply {
//                    this.addHeader(
//                        "Content-Type",
//                        "application/json"
//                    ) // <-- this is the important line
//                    //  && isNeedAuth(original.url.pathSegments[1])
//                    if (token.isNotEmpty() && isNeedAuth(original.url.pathSegments[original.url.pathSegments.size - 1])) this.addHeader(
//                        "Authorization",
//                        "Bearer $token"
//                    )
//                    this.addHeader("Cache-Control", "no-cache")
//                }
//            return chain.proceed(requestBuilder.build())
//        }
//        private fun isNeedAuth(path: String?): Boolean {
//            return !(path == null || path.toString().trim().equals("dologin", true))
//        }
//    }

    fun getImage():String{
        return IMG
    }

}