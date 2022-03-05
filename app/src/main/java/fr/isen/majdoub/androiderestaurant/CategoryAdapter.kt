package fr.isen.majdoub.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(val categories: Array<out String>)  : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_category, parent, false)

        return ViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.dishtTile.text = categories[position]

        // sets the image to the imageview from our itemHolder class
       // holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
       // holder.textView.text = ItemsViewModel.text
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        //val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val dishtTile: TextView = itemView.findViewById(R.id.dishTitle)

        init{

            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}



