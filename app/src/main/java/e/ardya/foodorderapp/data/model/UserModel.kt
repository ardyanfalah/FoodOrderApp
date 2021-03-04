package e.ardya.foodorderapp.data.model

object UserModel {
    data class ResponseLogin(
        var success: Boolean,
        var data: User? = null,
        var message: String
    )

    data class ResponseRegister(
        var success: Boolean,
        var data: String? = null,
        var messages: String
    )

    data class User(
        val alamat: String?=null,
        val email: String?=null,
        val id_plgn: Int?=null,
        val nama_plgn: String?=null,
        val no_hp: String?=null,
        val password: String?=null,
        val status: String?=null
    )

    data class Login(
        val email: String,
        val password: String
    )


}