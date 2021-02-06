package com.gofynd.view.Wishlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gofynd.R
import com.gofynd.database.Wishlist
import com.gofynd.databinding.ActivityWishlistBinding
import com.gofynd.view.SuperActivity
import com.gofynd.viewModel.WishListViewModel


class WishlistActivity : SuperActivity() {
    private var binding: ActivityWishlistBinding? = null
    private var viewModel: WishListViewModel? = null
    private var adapter: WishListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_wishlist)
        viewModel = ViewModelProviders.of(this).get(WishListViewModel::class.java)
        binding?.viewModel = viewModel
        updateToolbar(resources.getString(R.string.title_wishlist))
        initRecylerView()
        fetchWishListData()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.wishlist_list_menu,menu)
        val myActionMenuItem = menu!!.findItem(R.id.menuSearch)
        var searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean { // Toast like print
                /*UserFeedback.show("SearchOnQueryTextSubmit: $query")
                if (!searchView.isIconified()) {
                    searchView.setIconified(true)
                }*/
                myActionMenuItem.collapseActionView()
                return true
            }

            override fun onQueryTextChange(s: String): Boolean { // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                Log.d("s",s.toString())

                adapter?.filter?.filter(s)
                return true
            }
        })
        return true

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var intent = Intent(this, WishlistActivity::class.java)
        when(item.getItemId())

        {
            R.id.menuFavorite-> /*startActivity(intent)*/""
            else-> Log.d("Invalid","");


        }

        return true
    }
    private fun fetchWishListData() {
        viewModel?.getWishList()?.observe(this, Observer {
            wishList->
            adapter?.response = wishList as List<Wishlist>?
            adapter?.filteredResponse = wishList as List<Wishlist>?
            adapter?.notifyDataSetChanged()
            Log.d("Size",Integer.toString(wishList!!.size) )
        })
    }

    private fun initRecylerView() {
        adapter = WishListAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        binding?.rvWishListMovies?.layoutManager = layoutManager
        binding?.rvWishListMovies?.adapter = adapter
    }
}
