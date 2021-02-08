package e.ardya.foodorderapp.data.model

import java.util.*

object TransaksiModel {
    data class ItemTransaksi(
        var Id_Trx: Int?=null,
        var Id_Admin: Int?=null,
        var Id_Pelanggan: Int?=null,
        var Id_Menu: Int?=null,
        var Jumlah_Makanan: Int?=null,
        var Harga_Menu: String?=null,
        var Tanggal_Trx: Date?=null
    )
}