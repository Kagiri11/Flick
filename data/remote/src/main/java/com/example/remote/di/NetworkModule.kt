package com.example.remote.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.domain.utils.Constants
import com.example.remote.MovieNetworkService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun networkConnected(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected) {
        isConnected = true
    }
    return isConnected
}

val networkModule = module {

    single {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .cache(Cache(androidContext().cacheDir, cacheSize))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (networkConnected(androidContext())!!) {
                    request.newBuilder().header("Cache-control", "public,max-age=" + 5).build()
                } else {
                    request.newBuilder().header(
                        "Cache-control",
                        "public, only-if-cached,max-stale" + 60 * 60 * 24 * 7
                    ).build()
                }
                chain.proceed(request)
            }
            .build()
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieNetworkService::class.java)
    }
}
