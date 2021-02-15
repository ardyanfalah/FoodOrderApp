package e.ardya.foodorderapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.data.net.RetrofitClient

class RecycleMenuOrderAdapter(private val context: Context, private val listOrder : ArrayList<TransaksiModel.ItemTransaksi>, private val listener : Listener) : RecyclerView.Adapter<RecycleMenuOrderAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(order :TransaksiModel.ItemTransaksi)
        fun onAddOrder(order :TransaksiModel.ItemTransaksi, position: Int)
        fun onRemoveOrder(order :TransaksiModel.ItemTransaksi,position: Int)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNama: TextView = itemView.findViewById(R.id.tv_menu_name_item)
        var jumlahPesanan: TextView = itemView.findViewById(R.id.tv_menu_count)
        var itemHarga: TextView = itemView.findViewById(R.id.tv_menu_price_item)
        var btnAdd: Button = itemView.findViewById(R.id.btn_add_count)
        var btnRemove: Button = itemView.findViewById(R.id.btn_remove_count)
        fun bind(order: TransaksiModel.ItemTransaksi, listener: Listener, position:Int){
            itemView.setOnClickListener{ listener.onItemClick(order) }
            btnAdd.setOnClickListener{ listener.onAddOrder(order,position) }
            btnRemove.setOnClickListener{ listener.onRemoveOrder(order,position) }
        }

        init {
//            Log.d("Log init=>",listMenu.toString())
            jumlahPesanan.text = "1"
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_order_detail, viewGroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var tempHarga = 0
        tempHarga = if(listOrder[i].Jumlah_Makanan!=null && listOrder[i].Harga_Menu != null){
            listOrder[i].Jumlah_Makanan!!.toInt().times(listOrder[i].Harga_Menu!!.toInt())
        } else {
            listOrder[i].Harga_Menu!!.toInt()
        }
        Log.d("bind=>",i.toString())
        viewHolder.bind(listOrder[i],listener,i)
        viewHolder.itemNama.text = listOrder[i].Nama_Menu
        viewHolder.itemHarga.text = tempHarga.toString()
        viewHolder.jumlahPesanan.text = listOrder[i].Jumlah_Makanan.toString()
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }
}