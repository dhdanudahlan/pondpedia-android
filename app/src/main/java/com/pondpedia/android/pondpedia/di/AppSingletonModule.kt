package com.pondpedia.android.pondpedia.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.pondpedia.android.pondpedia.BuildConfig
import com.pondpedia.android.pondpedia.R
import com.pondpedia.android.pondpedia.core.app.PondPediaApplication
import com.pondpedia.android.pondpedia.core.util.Constants
import com.pondpedia.android.pondpedia.core.util.Constants.DATASTORE_KEY
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_IN_REQUEST
import com.pondpedia.android.pondpedia.core.util.Constants.SIGN_UP_REQUEST
import com.pondpedia.android.pondpedia.core.util.manager.AuthAuthenticator
import com.pondpedia.android.pondpedia.core.util.manager.AuthInterceptor
import com.pondpedia.android.pondpedia.core.util.manager.TokenManager
import com.pondpedia.android.pondpedia.data.local.database.PondPediaDatabase
import com.pondpedia.android.pondpedia.data.remote.api.PondPediaApiService
import com.pondpedia.android.pondpedia.data.remote.api.PondPediaApiService.Companion.BASE_URL
import com.pondpedia.android.pondpedia.data.repository.AuthRepositoryImpl
import com.pondpedia.android.pondpedia.data.repository.ProfileRepositoryImpl
import com.pondpedia.android.pondpedia.domain.manager.LocalUserManager
import com.pondpedia.android.pondpedia.domain.manager.LocalUserManagerImpl
import com.pondpedia.android.pondpedia.domain.repository.AuthRepository
import com.pondpedia.android.pondpedia.domain.repository.ProfileRepository
import com.pondpedia.android.pondpedia.domain.use_case.ReadAppEntryUseCase
import com.pondpedia.android.pondpedia.domain.use_case.SaveAppEntryUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
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
            "pond_database_1.3"
        ).build()

        return database
    }

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
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
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun providePondPediaApiService(
        retrofit: Retrofit
    ): PondPediaApiService {
        return retrofit.create(PondPediaApiService::class.java)
    }

}