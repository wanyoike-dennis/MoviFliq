package com.dennis.movifliq.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dennis.movifliq.data.Movies

@Entity
data class MovieDatabase(
    @PrimaryKey(autoGenerate = true) val id : Int=0,
    @ColumnInfo(name = "movie_title")val title: String,
    @ColumnInfo(name = "overview")val overView:String,
    @ColumnInfo(name = "release_date")val releaseDate:String,
    @ColumnInfo(name = "movie_ratings")val rating:Float,
    @ColumnInfo(name = "movie_vote_count")val voteCount:Int,
    @ColumnInfo(name = "movie_thumbnail")val thumbnail:String,
)

fun MovieDatabase.toMovie() : Movies{
    return Movies(
        title=title,
        overview=overView,
        releaseDate=releaseDate,
        voteAverage = rating,
        voteCount = voteCount,
        posterPath = thumbnail

    )
}

fun Movies.toMovieEntity() : MovieDatabase{
    return MovieDatabase(
        title = title,
        overView = overview,
        releaseDate = releaseDate,
        rating = voteAverage,
        voteCount = voteCount,
        thumbnail = posterPath
    )
}