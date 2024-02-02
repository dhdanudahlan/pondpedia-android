package com.pondpedia.android.pondpedia.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.pondpedia.android.pondpedia.BuildConfig
import com.pondpedia.android.pondpedia.core.util.Constants.DATASTORE_KEY
import com.pondpedia.android.pondpedia.core.util.manager.AuthInterceptor
import com.pondpedia.android.pondpedia.core.util.manager.ThreadManager
import com.pondpedia.android.pondpedia.core.util.manager.TokenManager
import com.pondpedia.android.pondpedia.data.local.database.PondPediaDatabase
import com.pondpedia.android.pondpedia.data.remote.api.PondPediaApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_KEY)
@Module
@InstallIn(SingletonComponent::class)
class AppSingletonModule {

    @Provides
    @Singleton
    fun providePondDatabase(app: Application): PondPediaDatabase {
        val database = Room.databaseBuilder(
            app,
            PondPediaDatabase::class.java,
            "pond_database_1.5"
        ).build()

        return database
    }

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun provideThreadManager(@ApplicationContext context: Context): ThreadManager = ThreadManager(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
    @Singleton
    @Provides
    fun providesMoshi() = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .client(okHttpClient)
        .build()

    @Provides
    fun providePondPediaApiService(
        retrofit: Retrofit
    ): PondPediaApiService {
        return retrofit.create(PondPediaApiService::class.java)
    }

}