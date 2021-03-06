package cl.desafiolatam.apirest


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.apirest.model.pojo.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.photos_list_item.view.*


class PhotoAdapter(private var myDataset: MutableList<Photo>) :
    RecyclerView.Adapter<PhotoAdapter.PostHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        //crear nueva vista
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photos_list_item, parent, false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        var post = myDataset[position]

        holder.title.text = post.title
        Picasso.get()
            .load(post.thumbnailUrl)
            .into(holder.imagen)
    }

    fun updateItem(it: List<Photo>) {
         myDataset.clear()
      // myDataset.addAll(it)
        myDataset = it.toMutableList()
        notifyDataSetChanged()
    }

    class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.txTitle
        var imagen: ImageView = itemView.imageView
    }

}
