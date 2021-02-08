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
import kotlinx.android.synthetic.main.item_menu.view.*


class RecyclerAdapter(
    private val context: Context,
    private val listMenu: ArrayList<MenuModel.Data>,
    private val listener: Listener
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(menu: MenuModel.Data)
        fun onOrder(menu: MenuModel.Data, position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNama: TextView = itemView.findViewById(R.id.tv_nama_menu)
        var itemDesc: TextView = itemView.findViewById(R.id.tv_desc_menu)
        var itemHarga: TextView = itemView.findViewById(R.id.tv_harga_menu)
        var itemImage: ImageView = itemView.findViewById(R.id.iv_image_menu)
        var btnOrder: Button = itemView.findViewById(R.id.btn_order_menu)
        var btnAdd: Button = itemView.findViewById(R.id.btn_add_count)
        var btnRemove: Button = itemView.findViewById(R.id.btn_remove_count)
        var tvCount: TextView = itemView.findViewById(R.id.tv_menu_count)

        fun bind(menu: MenuModel.Data, listener: Listener, position: Int) {
            itemView.setOnClickListener { listener.onItemClick(menu) }
            btnOrder.setOnClickListener { listener.onOrder(menu, position) }
        }

        init {
//            Log.d("Log init=>",listMenu.toString())
            itemView.setOnClickListener {
                Log.d("Log onclick=>", it.tv_nama_menu.text.toString())
            }

        }

    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_menu, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Log.d("bind=>", i.toString())
        Glide.with(context).load(RetrofitClient.getImage() + listMenu.get(i).Image_Menu)
            .apply(RequestOptions().centerCrop())
            .into(viewHolder.itemImage)
        viewHolder.bind(listMenu[i], listener, i)
        viewHolder.itemNama.text = listMenu[i].Nama_Menu
        viewHolder.itemDesc.text = listMenu[i].Deskripsi_Menu
        viewHolder.itemHarga.text = listMenu[i].Harga_Menu

    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

}