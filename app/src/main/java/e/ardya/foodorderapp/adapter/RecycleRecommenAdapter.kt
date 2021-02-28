package e.ardya.foodorderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.data.model.MenuModel
import e.ardya.foodorderapp.data.net.RetrofitClient

class RecycleRecommenAdapter(
    private val context: Context,
    private val listMenu: ArrayList<MenuModel.Data>,
    private val listener: RecycleRecommenAdapter.Listener
) : RecyclerView.Adapter<RecycleRecommenAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(menu: MenuModel.Data)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImageRecommend:ImageView = itemView.findViewById(R.id.iv_image_menu)
        var itemNamaMenu:TextView = itemView.findViewById(R.id.tv_menu_name_item)
        var itemHargaMenu:TextView = itemView.findViewById(R.id.tv_harga_menu)
        var itemRatingMenu:TextView = itemView.findViewById(R.id.tv_rating_number)

        fun bind(menu: MenuModel.Data, listener: RecycleRecommenAdapter.Listener, position: Int) {
            itemView.setOnClickListener { listener.onItemClick(menu) }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_menu_recommend, viewGroup, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return listMenu.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMenu[position], listener, position)
        Glide.with(context).load(RetrofitClient.getImage() + listMenu.get(position).gambar_menu)
            .apply(RequestOptions().centerCrop())
            .into(holder.itemImageRecommend)
        holder.itemHargaMenu.text = listMenu[position].harga_menu
        holder.itemNamaMenu.text = listMenu[position].nama_menu
        holder.itemRatingMenu.text = listMenu[position].rating.toString()
    }
}