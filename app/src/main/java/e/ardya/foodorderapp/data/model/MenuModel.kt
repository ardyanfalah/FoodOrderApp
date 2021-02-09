package e.ardya.foodorderapp.data.model

object MenuModel {
    data class ResponseType(
        var success: Boolean = false,
        var data: ArrayList<Data>?,
        var message: String?
    )
    data class Data(
        var Id_Menu: Int?,
        var Nama_Menu: String?,
        var Harga_Menu: String?,
        var Jenis_Menu: String?,
        var Status_Menu: String?,
        var Image_Menu: String?,
        var Deskripsi_Menu: String?,
        var Jumlah_Menu: Int =0
        )
}