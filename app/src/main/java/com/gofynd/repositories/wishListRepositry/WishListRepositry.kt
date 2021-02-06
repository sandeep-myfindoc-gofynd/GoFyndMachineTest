package com.gofynd.repositories.wishListRepositry

import androidx.lifecycle.MutableLiveData
import com.gofynd.database.Wishlist
import com.gofynd.view.WillyWeatherApplication.Companion.myDatabase

class WishListRepositry {
    val wishList: MutableLiveData<List<Wishlist?>?>
        get() {
            val data =
                MutableLiveData<List<Wishlist?>?>()
            val wishListData =
                myDatabase!!.wishListDao()!!.wishListData
            data.postValue(wishListData)
            return data
        }

    companion object {
        private var repositry: WishListRepositry? = null
        val instance: WishListRepositry?
            get() {
                if (repositry == null) {
                    repositry = WishListRepositry()
                }
                return repositry
            }
    }
}