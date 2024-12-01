#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_capstonemadeone_core_data_source_local_MovieNativeLibs_movieApiToken(JNIEnv *env, jobject thiz) {
    std::string key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhZGRkMTM1YmYwNDM0NGM0NjQ4ZmQ1OWQzZGFkOWMxMyIsIm5iZiI6MTYxNTk3NjQ2Mi42MDQsInN1YiI6IjYwNTFkODBlMjRiMzMzMDAzZDIwMTAwNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Pfq2Dv5HxiVrjd6XwI70VG1YSbLKon82qP6uw2MVlwo";
    return env->NewStringUTF(key.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_capstonemadeone_core_data_source_local_MovieNativeLibs_baseUrlMovie(JNIEnv *env,
                                                                                   jobject thiz) {
    std::string key = "https://api.themoviedb.org/3/discover/";
    return env->NewStringUTF(key.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_capstonemadeone_core_data_source_local_MovieNativeLibs_pathGetMovies(JNIEnv *env,
                                                                                    jobject thiz) {
    std::string key = "movie";
    return env->NewStringUTF(key.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_capstonemadeone_core_data_source_local_MovieNativeLibs_pathGetTvShows(JNIEnv *env,
                                                                                     jobject thiz) {
    std::string key = "tv";
    return env->NewStringUTF(key.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_issog_capstonemadeone_core_data_source_local_MovieNativeLibs_baseUrlImage(JNIEnv *env,
                                                                                   jobject thiz) {
    std::string key = "https://image.tmdb.org/t/p/w500";
    return env->NewStringUTF(key.c_str());
}