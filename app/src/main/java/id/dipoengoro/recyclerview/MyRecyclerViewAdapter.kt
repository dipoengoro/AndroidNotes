package id.dipoengoro.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter(
    private val list: List<Item>,
    private val onItemClicked: (String, Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    ) { onItemClicked(list[it].name, it) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}

class ViewHolder(view: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {

    init {
        itemView.setOnClickListener { onItemClicked(adapterPosition) }
    }

    private val myText: TextView = view.findViewById(R.id.textRecycler)

    fun bind(item: Item) {
        myText.text = item.name
    }

}