package com.gofynd.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Wishlist::class], version = 1)
abstract class WishListDatabase : RoomDatabase() {
    abstract fun wishListDao(): WishListDao?
}