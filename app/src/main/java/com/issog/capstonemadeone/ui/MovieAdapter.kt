package com.issog.capstonemadeone.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.issog.capstonemadeone.core.data.source.local.MovieNativeLibs
import com.issog.capstonemadeone.core.domain.model.Movie
import com.issog.capstonemadeone.databinding.ItemMovieBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList: ArrayList<Movie> by lazy { arrayListOf() }

    inner class MovieViewHolder(private val itemMovieBinding: ItemMovieBinding): ViewHolder(itemMovieBinding.root) {
        fun bind(movie: Movie) {
            itemMovieBinding.apply {
                Glide.with(ivMovie.context)
                    .load("${MovieNativeLibs.baseUrlImage()}${movie.posterPath}")
                    .into(ivMovie)
                tvTitle.text = movie.title
                tvDesc.text = "Rating: ${movie.voteAverage}"
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder =
        MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    fun setMovieData(movies: List<Movie>) {
        movies.filter { !it.isTvShows }
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    fun setTvShowData(tvShows: List<Movie>) {
        tvShows.filter { it.isTvShows }
        movieList.clear()
        movieList.addAll(tvShows)
        notifyDataSetChanged()
    }
}