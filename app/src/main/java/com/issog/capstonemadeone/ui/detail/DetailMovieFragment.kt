package com.issog.capstonemadeone.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.issog.capstonemadeone.R
import com.issog.capstonemadeone.core.data.source.local.MovieNativeLibs
import com.issog.capstonemadeone.core.domain.model.Movie
import com.issog.capstonemadeone.databinding.FragmentDetailMovieBinding

class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateMovieData()
    }

    private fun populateMovieData() {
        binding.apply {
            val movieData = arguments?.getParcelable<Movie>("Movie")
            Glide.with(ivMovie.context)
                .load("${MovieNativeLibs.baseUrlImage()}${movieData?.posterPath}")
                .into(ivMovie)
            tvTitle.text = movieData?.title
            tvRating.text = "Rating: ${movieData?.voteAverage}"
            tvOverview.text = movieData?.overview
        }
    }
}