package e.ardya.foodorderapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R
import e.ardya.foodorderapp.data.model.TransaksiModel

class RecycleOrderAdapter(
    private val context: Context,
    private val listOrder: ArrayList<TransaksiModel.HeaderPemesanan>,
    private val listener: RecycleOrderAdapter.Listener
) : RecyclerView.Adapter<RecycleOrderAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(order: TransaksiModel.HeaderPemesanan)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemStatus:TextView = itemView.findViewById(R.id.tv_status_order)
        var itemWaktuOrder:TextView = itemView.findViewById(R.id.tv_datetime_order)
        fun bind(order: TransaksiModel.HeaderPemesanan, listener: RecycleOrderAdapter.Listener, position: Int) {
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listOrder[position],listener,position)
        viewHolder.itemStatus.text = listOrder[position].status_pemesanan
        viewHolder.itemWaktuOrder.text ="Dipesan pada "+ listOrder[position].waktu_pmsn
    }

}