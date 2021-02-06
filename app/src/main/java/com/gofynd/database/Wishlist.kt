package com.gofynd.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish")
class Wishlist {
    @PrimaryKey
    var movieId = 0
    @ColumnInfo(name = "Title")
    var movieTitle: String? = null
    @ColumnInfo(name = "ReleaseDate")
    var releaseDate: String? = null
    @ColumnInfo(name = "PosterPath")
    var posterPath: String? = null

}