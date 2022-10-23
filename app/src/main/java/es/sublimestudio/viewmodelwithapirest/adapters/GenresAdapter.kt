package es.sublimestudio.viewmodelwithapirest.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import es.sublimestudio.viewmodelwithapirest.R
import es.sublimestudio.viewmodelwithapirest.models.Genre

class GenresAdapter(val onClick: (Genre) -> Unit): RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    var data = mutableListOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(genresData: List<Genre>) {
        data = genresData.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genero = itemView.findViewById<TextView>(R.id.name)
        val card = itemView.findViewById<CardView>(R.id.card)

        fun bind(item: Genre) {
            genero.text = item.name
            card.setOnClickListener {
                Log.v("Pulso sobre", item.id.toString())
                onClick(item)
            }
        }
    }
}