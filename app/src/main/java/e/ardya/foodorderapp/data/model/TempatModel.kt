package e.ardya.foodorderapp.data.model

object TempatModel {
    data class ResponseTempat(
        val data: ArrayList<Data>?,
        val messages: String,
        val success: Boolean
    )

    data class ResponseAvailability(
        val data: Boolean,
        val messages: String,
        val success: Boolean
    )

    data class Data(
        val deskripsi: String?,
        val id_tmpt: Int,
        val no_tmpt: String,
        val status_tmpt: String
    )
}