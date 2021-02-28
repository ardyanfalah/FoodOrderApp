package e.ardya.foodorderapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.data.model.TransaksiModel
import e.ardya.foodorderapp.data.net.RetrofitClient

class RecycleRatingAdapter(private val context: Context,
                           private val listMenu: ArrayList<TransaksiModel.DetailMenuPemesanan>,
                           private val listener: RecycleRatingAdapter.Listener
) : RecyclerView.Adapter<RecycleRatingAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(rating: TransaksiModel.DetailMenuPemesanan)
        fun onRatingBarChange(rating: TransaksiModel.DetailMenuPemesanan,position: Int,value:Float)
        fun onCatatanchange(rating: TransaksiModel.DetailMenuPemesanan,position: Int,value:Editable)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNamaMenu:TextView = itemView.findViewById(R.id.tv_menu_rating_name)
        var itemImageMenu:ImageView = itemView.findViewById(R.id.iv_image_rating_menu)
        var itemRatingMenu:RatingBar = itemView.findViewById(R.id.rb_rating_menu)
        var itemCatatanRating:EditText = itemView.findViewById(R.id.et_note_rating)

        fun bind(rating: TransaksiModel.DetailMenuPemesanan, listener: RecycleRatingAdapter.Listener, position: Int) {
            itemView.setOnClickListener { listener.onItemClick(rating) }
            itemRatingMenu.setOnRatingBarChangeListener { ratingBar, fl, b -> listener.onRatingBarChange(rating,position,fl) }
            itemCatatanRating.addTextChangedListener {listener.onCatatanchange(rating,position,it!!)}
        }

        init {
//            Log.d("Log init=>",listMenu.toString())
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_input_rating, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listMenu[position],listener,position)
        Glide.with(context).load(RetrofitClient.getImage() + listMenu.get(position).gambar_menu)
            .apply(RequestOptions().centerCrop())
            .into(viewHolder.itemImageMenu)
        viewHolder.itemNamaMenu.text = listMenu[position].nama_menu
        viewHolder.itemRatingMenu.rating = if(listMenu[position].rating != null )  listMenu[position].rating!!.toFloat()  else 0.0f
        viewHolder.itemCatatanRating.setText(listMenu[position].catatan)
    }

}