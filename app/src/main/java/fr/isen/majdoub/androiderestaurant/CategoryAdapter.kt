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

class CategoryAdapter(private val  items: List<Items>, val mListener: (Items) -> Unit)  : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_category, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val menu = items[position]
        val id = menu.id.toInt()
        holder.dishTile.text = menu.name_fr
        holder.dishPrice.text = menu.prices[0].price +"€"

            Picasso.get().load(menu.images[0].ifEmpty { null })
                .placeholder(
                    when(id) {
                        in 126..128 -> R.drawable.starter
                        in 129..131 -> R.drawable.plat
                        in 132..134 -> R.drawable.dessert
                        else -> R.drawable.home_picture
                    })
                .error(R.drawable.plat)
                .into(holder.imageDish)

        holder.itemView.setOnClickListener {
            mListener(menu)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        //val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val dishTile: TextView = ItemView.findViewById(R.id.nameDish)
        val dishPrice: TextView = ItemView.findViewById(R.id.priceDish)
        val imageDish : ImageView = ItemView.findViewById(R.id.pictureDish)
    }
}






