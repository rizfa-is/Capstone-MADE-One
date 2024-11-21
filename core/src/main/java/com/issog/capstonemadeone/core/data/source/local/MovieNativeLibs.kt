package com.issog.capstonemadeone.core.data.source.local

object MovieNativeLibs {
    init {
        System.loadLibrary("movie-native-libs")
    }

    external fun baseUrlMovie(): String
    external fun movieApiToken(): String
    external fun pathGetMovies(): String
    external fun pathGetTvShows(): String
}