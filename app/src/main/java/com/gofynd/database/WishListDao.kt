package com.gofynd.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WishListDao {
    @Insert
    fun addTowishdata(wishlist: Wishlist?)

    @get:Query("SELECT * FROM wish")
    val wishListData: List<Wishlist?>?

    @Query("SELECT EXISTS (SELECT 1 FROM wish WHERE movieId=:id)")
    fun isWish(id: Int): Int

    @Delete
    fun delete(Wishlist: Wishlist?)
}