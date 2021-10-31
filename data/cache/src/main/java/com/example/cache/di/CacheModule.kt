package com.example.cache.di

import androidx.room.Room
import com.example.cache.db.AppDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val cacheModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDataBase::class.java,
            "flick.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDataBase>().moviesDao() }
    single { get<AppDataBase>().reviewsDao() }
}
