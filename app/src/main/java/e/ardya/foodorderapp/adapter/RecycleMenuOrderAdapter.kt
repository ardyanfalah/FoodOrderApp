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
import e.ardya.foodorderapp.data.net.RetrofitClient

class RecycleMenuOrderAdapter(private val context: Context, private val listMenu : ArrayList<MenuModel.Data>, private val listener : Listener) : RecyclerView.Adapter<RecycleMenuOrderAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(menu : MenuModel.Data)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNama: TextView = itemView.findViewById(R.id.tv_menu_name_item)
        var jumlahPesanan: TextView = itemView.findViewById(R.id.tv_menu_count)
        var itemHarga: TextView = itemView.findViewById(R.id.tv_menu_price_item)

        fun bind(menu: MenuModel.Data, listener: Listener, position:Int){
            itemView.setOnClickListener{ listener.onItemClick(menu) }
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
        Log.d("bind=>",i.toString())
        viewHolder.bind(listMenu[i],listener,i)
        viewHolder.itemNama.text = listMenu[i].Nama_Menu
        viewHolder.itemHarga.text = listMenu[i].Harga_Menu

    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}