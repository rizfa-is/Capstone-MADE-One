package com.issog.capstonemadeone.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.issog.capstonemadeone.core.data.Resources
import com.issog.capstonemadeone.core.domain.model.Movie
import com.issog.capstonemadeone.core.domain.usecase.MovieUseCase

class MovieViewModel(val movieUseCase: MovieUseCase): ViewModel() {
    val movieList: LiveData<Resources<List<Movie>>> =
        movieUseCase.getMovies().asLiveData()
}