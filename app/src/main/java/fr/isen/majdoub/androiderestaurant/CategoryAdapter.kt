package fr.isen.majdoub.androiderestaurant

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.majdoub.androiderestaurant.Menu
import java.lang.reflect.Array.get

class CategoryAdapter(val contexte : Context,val  categories: Menu)  : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_category, parent, false)

        return ViewHolder(view,mListener )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {

        val menu = categories.items[position]
        holder.dishTile.text = menu.name_fr
        holder.dishPrice.text = menu.prices[0].price +"€"

        if(menu.images[0].isNotEmpty()) {
            Picasso.get().load(menu.images[0].ifEmpty { null }).error(R.drawable.plat).into(holder.imageDish)
        }
    }

    override fun getItemCount(): Int {
        return categories.items.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View,listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        //val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val dishTile: TextView = ItemView.findViewById(R.id.nameDish)
        val dishPrice: TextView = ItemView.findViewById(R.id.priceDish)
        val imageDish : ImageView = ItemView.findViewById(R.id.pictureDish)

        init{
            ItemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }
}






