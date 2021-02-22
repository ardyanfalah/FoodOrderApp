package e.ardya.foodorderapp.data.model

object MenuModel {
    data class ResponseType(
        var success: Boolean = false,
        var data: ArrayList<Data>?,
        var message: String?
    )
    data class Data(
        var id_menu: Int?,
        var nama_menu: String?,
        var harga_menu: String?,
        var id_ktgr: String?,
        var status_Menu: String?,
        var gambar_menu: String?,
        var deskripsi_menu: String?,
        var Jumlah_Menu: Int =0
        )
}