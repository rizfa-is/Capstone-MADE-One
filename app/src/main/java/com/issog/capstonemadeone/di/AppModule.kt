package com.issog.capstonemadeone.di

import com.issog.capstonemadeone.core.domain.usecase.MovieAppInteractor
import com.issog.capstonemadeone.core.domain.usecase.MovieUseCase
import com.issog.capstonemadeone.ui.movie.MovieViewModel
import com.issog.capstonemadeone.ui.tvshow.TvShowViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
}