package e.ardya.foodorderapp.data.model

import java.util.*
import kotlin.collections.ArrayList

object TransaksiModel {
    data class ResponseType(
        var success: Boolean = false,
        var data: String?,
        var message: String?
    )

    data class RequestPemesanan(
        var headerPemesanan: HeaderPemesanan,
        var detailPemesanan: ArrayList<DetailPemesanan>
    )

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

    data class HeaderPemesanan(
        val id_pmsn : Int,
        val id_admin : Int,
        val id_plgn : Int,
        val id_tmpt : String?,
        val waktu_pmsn : String,
        val waktu_dtg : String?,
        val waktu_byr : String?,
        val status_pemesanan : String,
        val total_harga : Int
    )

    data class DetailPemesanan(
        val id_detail_pemesanan : Int,
        val id_pmsn : Int,
        val id_menu : Int,
        val jumlah_pesan : Int
    )
}