package com.issog.capstonemadeone.ui.detail

import androidx.lifecycle.ViewModel
import com.issog.capstonemadeone.core.domain.model.Movie
import com.issog.capstonemadeone.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(val movieUseCase: MovieUseCase): ViewModel() {
    fun updateFavorite(movie: Movie, isFavorite: Boolean) {
        movieUseCase.updateFavoriteMovie(movie, isFavorite)
    }
}