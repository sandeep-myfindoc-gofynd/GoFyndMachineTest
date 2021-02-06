package com.gofynd.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gofynd.database.Wishlist
import com.gofynd.repositories.wishListRepositry.WishListRepositry

class WishListViewModel(var wishListRepositry: WishListRepositry? = null,var wishlistMutatableData: MutableLiveData<List<Wishlist?>?>? = null) : ViewModel() {
    init {
        wishListRepositry = WishListRepositry.Companion.instance
    }
    fun getWishList() : MutableLiveData<List<Wishlist?>?>?{
        wishlistMutatableData = wishListRepositry!!.wishList
        return wishlistMutatableData
    }


}