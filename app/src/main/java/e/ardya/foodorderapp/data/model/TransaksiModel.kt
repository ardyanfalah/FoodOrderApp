package e.ardya.foodorderapp.data.model

import java.util.*
import kotlin.collections.ArrayList

object TransaksiModel {
    data class ResponseType(
        var success: Boolean = false,
        var data: String?,
        var messages: String?
    )

    data class ResponsePemesanan(
        var success: Boolean = false,
        var data: ArrayList<PemesananWithDetail>?,
        var messages: String?
    )

    data class ResponsePemesananDetail(
        var success: Boolean = false,
        var data: ArrayList<DetailMenuPemesanan>?,
        var messages: String?
    )

    data class ItemTransaksi(
        var id_pmsn: Int?=null,
        var id_admin: Int?=null,
        var id_plgn: Int?=null,
        var id_menu: Int?=null,
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
        val waktu_pmsn : String,
        val waktu_dtg : String?,
        val waktu_byr : String?,
        val status_pemesanan : String,
        val total_harga : Int,
        val gambar_bukti_pembayaran: String? = null,
        val is_takeout: String? = null
    )

    data class DetailPemesanan(
        val id_detail_pemesanan : Int,
        val id_pmsn : Int,
        val id_menu : Int,
        val jumlah_pesan : Int
    )

    data class  DetailMenuPemesanan(

        val id_detail_pemesanan: Int,
        val id_pmsn: Int?,
        val id_menu: Int?,
        val jumlah_pesan: Int,
        val rating_status: String,
        val nama_menu: String,
        val harga_menu: String,
        val jumlah_harga_pesan: String,
        var gambar_menu: String,
        var rating:Int?,
        var catatan:String?
        )

    data class PemesananWithDetail(
        val id_admin: String,
        val id_plgn: String,
        val id_pmsn: String,
        val menu: ArrayList<DetailMenuPemesanan>,
        val nama_admin: String,
        val nama_plgn: String,
        val status_pemesanan: String,
        val total_harga: String,
        val waktu_byr: Any,
        val waktu_dtg: Any,
        val waktu_pmsn: String,
        val gambar_bukti_pembayaran: String? = null,
        val tempat: String?=null

    )


    data class DetailTempat(
        val id_detail_tempat: Int,
        val id_pmsn: Int,
        val id_tmpt: Int
    )
}