package com.example.mediapp.di

import android.content.Context
import com.example.mediapp.api.Api_Builder
import com.example.mediapp.prefData.MyPreference
import com.example.mediapp.repo.Repo
import com.google.firebase.appdistribution.gradle.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModule {


    @Provides
    @Singleton
    fun provideApi():Api_Builder{
        return Api_Builder
    }

    @Provides
    @Singleton
    fun provideRepo(
        apiService: Api_Builder
    ):Repo{
        return Repo(apiService)
    }


//    @Provides
//    @Singleton
//    fun provideContext(@ApplicationContext context: Context): Context {
//        return context
//    }

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context): MyPreference {
        return MyPreference(context )
    }
}