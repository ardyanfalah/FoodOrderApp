package e.ardya.foodorderapp.data.model

object UserModel {
    data class ResponseLogin(
        var success: Boolean,
        var data: Any? = null,
        var message: String
    )

    data class ResponseRegister(
        var success: Boolean,
        var data: Any? = null,
        var message: String
    )
}