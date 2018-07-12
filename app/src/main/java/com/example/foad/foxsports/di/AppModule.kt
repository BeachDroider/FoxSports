package com.example.foad.foxsports.di

import com.example.foad.foxsports.BuildConfig
import com.example.foad.foxsports.api.StatService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideOpenWeatherMapService(gson: Gson): StatService {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(StatService::class.java)
    }

    @Singleton
    @Provides
    fun provideGson() : Gson{
        return GsonBuilder().create()
    }
}