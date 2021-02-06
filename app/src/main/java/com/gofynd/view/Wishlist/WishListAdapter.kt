package com.gofynd.view.Wishlist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gofynd.R
import com.gofynd.database.Wishlist
import com.gofynd.databinding.LayoutSubitemWishlistBinding
import java.util.*
import kotlin.collections.ArrayList

class WishListAdapter : RecyclerView.Adapter<WishListAdapter.ViewHolder> , Filterable {
    private var mcontext: Context
    var response: List<Wishlist>? = null
        set(value) {
            field = value
        }
    var filteredResponse: List<Wishlist>? = null
        set(value) {
            field = value
        }

    constructor(mcontext: Context) {
        this.mcontext = mcontext
    }

    constructor(mcontext: Context, response: ArrayList<Wishlist>?) {
        this.mcontext = mcontext
        this.response = response
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var view : View =
            LayoutInflater.from(parent.context).inflate(
            R.layout.layout_subitem_wishlist,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        var  wishlist:Wishlist = filteredResponse!!.get(position)
        var basePath : String;
        basePath = "https://image.tmdb.org/t/p/w500/";
        holder.imgMoviewPoster?.let {
            Glide.with(mcontext)
                .load(basePath+wishlist.posterPath)
                .placeholder(R.drawable.loading)
                .into(it)
        }
        holder.labelMoviewTitle?.setText(wishlist.movieTitle)
        holder.labelReleaseDate?.setText(wishlist.releaseDate)
        //holder.labelMoviewTitle?.setText(wishlist.movieTitle)
    }

    override fun getItemCount(): Int {
        return if (filteredResponse != null) filteredResponse!!.size else 0
    }

    inner class ViewHolder(var view : View) :
        RecyclerView.ViewHolder(view) {
        var imgMoviewPoster: ImageView? = null
        var labelMoviewTitle: TextView? = null
        var labelReleaseDate: TextView? = null
        init {
            imgMoviewPoster = view.findViewById(R.id.imgMoviewPoster)
            labelMoviewTitle = view.findViewById(R.id.labelMoviewTitle)
            labelReleaseDate = view.findViewById(R.id.labelReleaseDate)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(query : CharSequence?): FilterResults? {
                val charSearch = query.toString()
                if(charSearch.isEmpty()){

                    filteredResponse = response as List<Wishlist>
                }else{
                    val resultList = ArrayList<Wishlist>()
                    for (row in response as List<Wishlist>){
                        Log.d(charSearch+"query",query.toString())
                        if (row.movieTitle?.toLowerCase()!!.contains(query.toString())){
                            resultList.add(row)
                        }
                    }
                    filteredResponse = resultList
                    Log.d("query",Integer.toString(resultList.size))
                }
                val filterResults = FilterResults()
                filterResults.values = filteredResponse
                return filterResults
            }

            override fun publishResults(query: CharSequence?, results : FilterResults?) {
                    filteredResponse = results?.values as ArrayList<Wishlist>
                notifyDataSetChanged()
                }

        }
    }
}