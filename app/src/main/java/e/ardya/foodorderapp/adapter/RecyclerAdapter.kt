package e.ardya.foodorderapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
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
        fun onAddOrder(menu: MenuModel.Data, position: Int)
        fun onRemoveOrder(menu: MenuModel.Data,position: Int)
    }
    var isVisible = false
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNama: TextView = itemView.findViewById(R.id.tv_nama_menu)
        var itemDesc: TextView = itemView.findViewById(R.id.tv_desc_menu)
        var itemHarga: TextView = itemView.findViewById(R.id.tv_harga_menu)
        var itemImage: ImageView = itemView.findViewById(R.id.iv_image_menu)
        var btnOrder: Button = itemView.findViewById(R.id.btn_order_menu)
        var btnAdd: Button = itemView.findViewById(R.id.btn_add_count)
        var btnRemove: Button = itemView.findViewById(R.id.btn_remove_count)
        var tvCount: TextView = itemView.findViewById(R.id.tv_menu_count)
        var itemRating: RatingBar = itemView.findViewById(R.id.ratingBar1)

        fun bind(menu: MenuModel.Data, listener: Listener, position: Int) {
            itemView.setOnClickListener { listener.onItemClick(menu) }
            btnOrder.setOnClickListener {
                listener.onOrder(menu, position)
                if(menu.Jumlah_Menu>0){
                    btnAdd.visibility = View.VISIBLE
                    tvCount.visibility = View.VISIBLE
                    btnRemove.visibility = View.VISIBLE
                    btnOrder.visibility = View.GONE
                } else{
                    btnAdd.visibility = View.GONE
                    tvCount.visibility = View.GONE
                    btnRemove.visibility = View.GONE
                    btnOrder.visibility = View.VISIBLE
                }
            }
            btnAdd.setOnClickListener { listener.onOrder(menu, position) }
            btnRemove.setOnClickListener {
                listener.onRemoveOrder(menu,position)
                if(menu.Jumlah_Menu < 1){
                    btnAdd.visibility = View.GONE
                    tvCount.visibility = View.GONE
                    btnRemove.visibility = View.GONE
                    btnOrder.visibility = View.VISIBLE
                }
            }
        }


        init {
//            Log.d("Log init=>",listMenu.toString())
            itemView.setOnClickListener {
                Log.d("Log onclick=>", it.tv_nama_menu.text.toString())
                btnOrder.visibility = View.GONE
            }
        }
    }

    fun showCounter(){
        this.isVisible = true
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_menu, viewGroup, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Log.d("bind=>", i.toString())
        Glide.with(context).load(RetrofitClient.getImage() + listMenu.get(i).gambar_menu)
            .apply(RequestOptions().centerCrop())
            .into(viewHolder.itemImage)
        viewHolder.bind(listMenu[i], listener, i)
        viewHolder.itemNama.text = listMenu[i].nama_menu
        viewHolder.itemDesc.text = listMenu[i].deskripsi_menu
        viewHolder.itemHarga.text = listMenu[i].harga_menu
        viewHolder.tvCount.text = listMenu[i].Jumlah_Menu.toString()
        viewHolder.itemRating.rating = if(listMenu[i].rating != null) listMenu[i].rating!!.toFloat() else 0.0f
        if(listMenu[i].Jumlah_Menu >= 1){
            viewHolder.btnAdd.visibility = View.VISIBLE
            viewHolder.tvCount.visibility = View.VISIBLE
            viewHolder.btnRemove.visibility = View.VISIBLE
            viewHolder.btnOrder.visibility = View.GONE
        } else {
            viewHolder.btnAdd.visibility = View.GONE
            viewHolder.tvCount.visibility = View.GONE
            viewHolder.btnRemove.visibility = View.GONE
            viewHolder.btnOrder.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

}