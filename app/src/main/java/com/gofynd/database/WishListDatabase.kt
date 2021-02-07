package com.gofynd.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wishlist::class], version = 1)
abstract class WishListDatabase : RoomDatabase() {
    abstract fun wishListDao(): WishListDao?
    companion object{
        private var INSTANCE: WishListDatabase? = null
        fun getInstance(context: Context?): WishListDatabase{
            if (INSTANCE == null){
                INSTANCE = context?.let {
                    Room.databaseBuilder(
                        it,
                        WishListDatabase::class.java,
                        "wish_data")
                        .allowMainThreadQueries().build()
                }
            }

            return INSTANCE as WishListDatabase
        }
    }
}