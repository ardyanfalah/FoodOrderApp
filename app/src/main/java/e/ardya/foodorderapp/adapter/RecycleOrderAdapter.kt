package e.ardya.foodorderapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Color.parseColor
import android.opengl.Visibility
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.data.model.TransaksiModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class RecycleOrderAdapter(
    private val context: Context,
    private val listOrder: ArrayList<TransaksiModel.PemesananWithDetail>,
    private val listener: RecycleOrderAdapter.Listener
) : RecyclerView.Adapter<RecycleOrderAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(order: TransaksiModel.PemesananWithDetail)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemStatus:TextView = itemView.findViewById(R.id.tv_status_order)
        var itemWaktuOrder:TextView = itemView.findViewById(R.id.tv_datetime_order)
        var itemMenu1:TextView = itemView.findViewById(R.id.tv_menu_1)
        var itemMenu2:TextView = itemView.findViewById(R.id.tv_menu_2)
        var itemCountMenu1:TextView = itemView.findViewById(R.id.tv_count_menu_1)
        var itemCountMenu2:TextView = itemView.findViewById(R.id.tv_count_menu_2)
        fun bind(order: TransaksiModel.PemesananWithDetail, listener: RecycleOrderAdapter.Listener, position: Int) {
            itemView.setOnClickListener { listener.onItemClick(order) }
        }

        init {
//            Log.d("Log init=>",listMenu.toString())
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_personal_order, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listOrder[position],listener,position)
        if(listOrder[position].status_pemesanan == "Menunggu_Verifikasi"){
            viewHolder.itemStatus.setTextColor(parseColor("#FF0000"))
            viewHolder.itemStatus.text = "Pesanan Diterima"

        } else if(listOrder[position].status_pemesanan == "Proses_Pembuatan"){
            viewHolder.itemStatus.setTextColor(parseColor("#FFBB01"))
            viewHolder.itemStatus.text = "Pesanan Diproses"
        } else {
            viewHolder.itemStatus.setTextColor(parseColor("#16CF00"))
            viewHolder.itemStatus.text = "Pesanan selesai"
        }
        val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(listOrder[position].waktu_pmsn)
        val formattedDate = SimpleDateFormat("EEEE, d MMM yyyy hh:mm ", Locale("id", "ID")).format(date)
        viewHolder.itemWaktuOrder.text ="Dipesan pada "+ formattedDate
        if (listOrder[position].menu.size >= 2){
            viewHolder.itemMenu1.text = listOrder[position].menu[0].nama_menu
            viewHolder.itemMenu2.text = listOrder[position].menu[1].nama_menu
            viewHolder.itemCountMenu1.text = "x"+listOrder[position].menu[0].jumlah_pesan.toString()
            viewHolder.itemCountMenu2.text = "x"+listOrder[position].menu[1].jumlah_pesan.toString()
        } else if(listOrder[position].menu.size == 1){
            viewHolder.itemMenu1.text = listOrder[position].menu[0].nama_menu
            viewHolder.itemMenu2.visibility = View.GONE
            viewHolder.itemCountMenu1.text = "x"+listOrder[position].menu[0].jumlah_pesan.toString()
            viewHolder.itemCountMenu2.visibility = View.GONE
        } else if(listOrder[position].menu.isEmpty()){
            viewHolder.itemMenu1.visibility = View.GONE
            viewHolder.itemMenu2.visibility = View.GONE
            viewHolder.itemCountMenu1.visibility = View.GONE
            viewHolder.itemCountMenu2.visibility = View.GONE
        }
    }

}