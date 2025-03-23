package com.issog.capstonemadeone.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.issog.capstonemadeone.R
import com.issog.capstonemadeone.core.data.source.local.MovieNativeLibs
import com.issog.capstonemadeone.core.domain.model.Movie
import com.issog.capstonemadeone.core.utils.orDefault
import com.issog.capstonemadeone.databinding.FragmentDetailMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailMovieBinding
    private val viewModel: DetailMovieViewModel by viewModel()

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
            initViews(movieData)
            setupFavoriteListener(movieData)
        }
    }

    private fun FragmentDetailMovieBinding.setupFavoriteListener(movieData: Movie?) {
        var isFavorite = movieData?.favorite.orDefault()
        Log.d("favorite", isFavorite.toString())
        updateFavoriteIcon(isFavorite)

        fabFavorite.setOnClickListener {
            updateFavoriteIconOnFAB(
                isFavorite,
                onFavTrue = {
                    movieData?.let { it1 -> viewModel.updateFavorite(it1, true) }
                },
                onFavFalse = {
                    movieData?.let { it1 -> viewModel.updateFavorite(it1, false) }
                }
            )
            isFavorite = !isFavorite
        }
    }

    private fun FragmentDetailMovieBinding.updateFavoriteIcon(
        isFavorite: Boolean
    ) {
        fabFavorite.setIconResource(
            if (isFavorite) {
                R.drawable.ic_favorite_on
            } else {
                R.drawable.ic_favorite_off
            }
        )
    }

    private fun FragmentDetailMovieBinding.updateFavoriteIconOnFAB(
        isFavorite: Boolean,
        onFavTrue: () -> Unit = {},
        onFavFalse: () -> Unit = {}
    ) {
        fabFavorite.setIconResource(
            if (isFavorite) {
                onFavFalse.invoke()
                R.drawable.ic_favorite_off
            } else {
                onFavTrue.invoke()
                R.drawable.ic_favorite_on
            }
        )
    }

    private fun FragmentDetailMovieBinding.initViews(movieData: Movie?) {
        Glide.with(ivMovie.context)
            .load("${MovieNativeLibs.baseUrlImage()}${movieData?.posterPath}")
            .into(ivMovie)
        tvTitle.text = movieData?.title
        tvRating.text = "Rating: ${movieData?.voteAverage}"
        tvOverview.text = movieData?.overview
    }
}