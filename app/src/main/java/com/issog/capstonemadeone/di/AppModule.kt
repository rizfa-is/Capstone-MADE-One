package com.issog.capstonemadeone.di

import com.issog.capstonemadeone.core.domain.usecase.MovieAppInteractor
import com.issog.capstonemadeone.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {

}