package com.example.composesample.data

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: ArrayList<Movie>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)

data class Movie(
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("overview")
    val overView: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double
)