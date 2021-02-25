package e.ardya.foodorderapp.data.model

object RatingModel {
    data class ResponseType(
        var success: Boolean = false,
        var data: String?,
        var messages: String?
    )

    data class ResponseRating(
        var success: Boolean = false,
        var data: ArrayList<Rating>?,
        var messages: String?
    )

    data class Rating(
        val id_rating: Int,
        val idPlgn: Int?,
        val idMenu: Int,
        val nilai: Long,
        val catatan: String,
        val waktuRekam: String
    )
}