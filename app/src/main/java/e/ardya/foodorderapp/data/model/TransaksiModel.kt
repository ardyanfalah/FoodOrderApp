package e.ardya.foodorderapp.data.model

import java.util.*

object TransaksiModel {
    data class ItemTransaksi(
        var id_pmsn: Int?=null,
        var id_admin: Int?=null,
        var id_plgn: Int?=null,
        var id_menu: Int?=null,
        var id_tmpt: Int?=null,
        var waktu_pmsn: String?=null,
        var waktu_dtg: String?=null,
        var waktu_byr: String?=null,
        var jumlah_pesan: Int?=null,
        var total_harga: Int?=null,
        var status_pemesanan: String?=null,
        var harga_menu: String?=null,
        var nama_menu: String?=null
    )
}