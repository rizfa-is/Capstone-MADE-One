package com.issog.capstonemadeone.core.di

import androidx.room.Room
import com.issog.capstonemadeone.core.data.MovieRepository
import com.issog.capstonemadeone.core.data.source.local.LocalDataSource
import com.issog.capstonemadeone.core.data.source.local.MovieNativeLibs
import com.issog.capstonemadeone.core.data.source.local.room.MovieDatabase
import com.issog.capstonemadeone.core.data.source.remote.RemoteDataSource
import com.issog.capstonemadeone.core.data.source.remote.network.ApiService
import com.issog.capstonemadeone.core.domain.repository.IMovieRepository
import com.issog.capstonemadeone.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<MovieDatabase>().movieDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.header("Authorization", MovieNativeLibs.movieApiToken())
                chain.proceed(requestBuilder.build())
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(MovieNativeLibs.baseUrlMovie())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(), get(), get()) }
}