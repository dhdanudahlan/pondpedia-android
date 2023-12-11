package com.pondpedia.android.pondpedia.di

import android.app.Application
import androidx.room.Room
import com.pondpedia.android.pondpedia.data.local.database.PondPediaDatabase
import com.pondpedia.android.pondpedia.data.repository.PondDetailsRepositoryImpl
import com.pondpedia.android.pondpedia.data.repository.PondsRepositoryImpl
import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository
import com.pondpedia.android.pondpedia.domain.repository.PondsRepository
import com.pondpedia.android.pondpedia.domain.use_case.ponds.AddPondUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.DeletePondByIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.GetCategoryListUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.GetPondByIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.GetPondListByCategoryUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.UpsertCategoryUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetCommodityGrowthRecordsByCommodityIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetCommodityHealthRecordsByCommodityIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetCommodityListByPondIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetFeedingRecordsByPondIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetPondRecordsByPondIdUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.GetWaterRecordsByPondIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PondManagementModule {

    @Provides
    @Singleton
    fun provideGetPondListByCategoryUseCase(repository: PondsRepository): GetPondListByCategoryUseCase {
        return GetPondListByCategoryUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPondByIdUseCase(repository: PondsRepository): GetPondByIdUseCase {
        return GetPondByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCategoryListUseCase(repository: PondsRepository): GetCategoryListUseCase {
        return GetCategoryListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetPondRecordsByPondIdUseCase(repository: PondDetailsRepository): GetPondRecordsByPondIdUseCase {
        return GetPondRecordsByPondIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetWaterRecordsByPondIdUseCase(repository: PondDetailsRepository): GetWaterRecordsByPondIdUseCase {
        return GetWaterRecordsByPondIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetFeedingRecordsByPondIdUseCase(repository: PondDetailsRepository): GetFeedingRecordsByPondIdUseCase {
        return GetFeedingRecordsByPondIdUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideGetCommodityByPondIdUseCase(repository: PondDetailsRepository): GetCommodityListByPondIdUseCase {
        return GetCommodityListByPondIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCommodityGrowthRecordsByCommodityIdUseCase(repository: PondDetailsRepository): GetCommodityGrowthRecordsByCommodityIdUseCase {
        return GetCommodityGrowthRecordsByCommodityIdUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetCommodityHealthRecordsByCommodityIdUseCase(repository: PondDetailsRepository): GetCommodityHealthRecordsByCommodityIdUseCase {
        return GetCommodityHealthRecordsByCommodityIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddPondUseCase(repository: PondsRepository): AddPondUseCase {
        return AddPondUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpsertCategoryUseCase(repository: PondsRepository): UpsertCategoryUseCase {
        return UpsertCategoryUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeletePondByIdUseCase(repository: PondsRepository): DeletePondByIdUseCase {
        return DeletePondByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePondsRepository(
        db: PondPediaDatabase,
    ): PondsRepository {
        return PondsRepositoryImpl(db.pondsDao, db.pondDetailsDao)
    }

    @Provides
    @Singleton
    fun providePondManagementRepository(
        db: PondPediaDatabase,
    ): PondDetailsRepository {
        return PondDetailsRepositoryImpl(db.pondsDao, db.pondDetailsDao)
    }


    @Provides
    @Singleton
    fun providePondDatabase(app: Application): PondPediaDatabase {
        val database = Room.databaseBuilder(
            app,
            PondPediaDatabase::class.java,
            "pond_database_1.2"
        ).build()

        return database
    }

    /*@Provides
    @Singleton
    fun providePondApi(): PondApi {
        return Retrofit.Builder()
            .baseUrl(PondApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PondApi::class.java)
    }*/
}
