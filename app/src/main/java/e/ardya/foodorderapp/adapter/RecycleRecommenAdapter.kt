package e.ardya.foodorderapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import e.ardya.foodorderapp.R

class RecycleRecommenAdapter : RecyclerView.Adapter<RecycleRecommenAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        var itemKode: TextView
//        var itemKategori: TextView
//        var itemIsi: TextView

        init {
//            itemKode = itemView.findViewById(R.id.kodePertanyaan)
//            itemKategori = itemView.findViewById(R.id.kategori)
//            itemIsi = itemView.findViewById(R.id.isiPertanyaan)

//            itemView.setOnClickListener {
//                var position: Int = getAdapterPosition()
//                val context = itemView.context
//                val intent = Intent(context, DetailPertanyaan::class.java).apply {
//                    putExtra("NUMBER", position)
//                    putExtra("CODE", itemKode.text)
//                    putExtra("CATEGORY", itemKategori.text)
//                    putExtra("CONTENT", itemIsi.text)
//                }
//                context.startActivity(intent)
//            }
        }
    }
    private val kode = arrayOf("d116df5",
        "36ffc75", "f5cfe78", "5b87628",
        "db8d14e", "9913dc4", "e120f96",
        "466251b")

    private val kategori = arrayOf("Kekayaan", "Teknologi",
        "Keluarga", "Bisnis",
        "Keluarga", "Hutang",
        "Teknologi", "Pidana")

    private val isi = arrayOf("pertanyaan 9",
        "pertanyaan 11", "pertanyaan 17", "test forum",
        "pertanyaan 12", "pertanyaan 18", "pertanyaan 20",
        "pertanyaan 21")

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_recommend_layout, viewGroup, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return kode.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //        viewHolder.itemKode.text = kode[i]
//        viewHolder.itemKategori.text = kategori[i]
//        viewHolder.itemIsi.text = isi[i]
    }
}