package e.ardya.foodorderapp.data.model

object TransaksiModel {
    data class Menu(
        var Id_Menu: Int?,
        var Nama_Menu: String?,
        var Harga_Menu: String?,
        var Jenis_Menu: String?,
        var Status_Menu: String?,
        var Image_Menu: String?,
        var Deskripsi_Menu: String?,
        var Jumlah_Menu: String?
    )
}