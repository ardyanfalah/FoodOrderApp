package e.ardya.foodorderapp.data.model

object UserModel {
    data class ResponseLogin(
        var success: Boolean,
        var data: User? = null,
        var message: String
    )

    data class ResponseRegister(
        var success: Boolean,
        var data: Any? = null,
        var message: String
    )

    data class User(
        val alamat: String,
        val email: String,
        val id_plgn: Int,
        val nama_plgn: String,
        val no_hp: String,
        val password: String,
        val status: String
    )

    data class Login(
        val email: String,
        val password: String
    )


}