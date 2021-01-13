package e.ardya.foodorderapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.data.model.MenuModel
import kotlinx.android.synthetic.main.card_layout.view.*


class RecyclerAdapter(private val listMenu : ArrayList<MenuModel.Data>, private val listener : Listener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(menu : MenuModel.Data)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNama: TextView = itemView.findViewById(R.id.tv_nama_menu)
        var itemDesc: TextView = itemView.findViewById(R.id.tv_desc_menu)
        var itemHarga: TextView = itemView.findViewById(R.id.tv_harga_menu)

        fun bind(menu:MenuModel.Data, listener: Listener, position:Int){
            itemView.setOnClickListener{ listener.onItemClick(menu) }

        }

        init {
            Log.d("Log init=>",listMenu.toString())
            itemView.setOnClickListener {
                Log.d("Log onclick=>",it.tv_nama_menu.text.toString())
//                var position: Int = getAdapterPosition()
//                val context = itemView.context
//                val intent = Intent(context, DetailPertanyaan::class.java).apply {
//                    putExtra("NUMBER", position)
//                    putExtra("CODE", itemKode.text)
//                    putExtra("CATEGORY", itemKategori.text)
//                    putExtra("CONTENT", itemIsi.text)
//                }
//                context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Log.d("bind=>",i.toString())
        viewHolder.bind(listMenu[i],listener,i)
        viewHolder.itemNama.text = listMenu[i].Nama_Menu
        viewHolder.itemDesc.text = listMenu[i].Deskripsi_Menu
        viewHolder.itemHarga.text = listMenu[i].Harga_Menu

    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}