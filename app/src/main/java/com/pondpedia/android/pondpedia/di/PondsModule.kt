package com.pondpedia.android.pondpedia.di

import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddCommodityGrowthRecordsUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddCommodityHealthRecordsUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddCommodityUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddFeedingRecordsUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddPondRecordsUseCase
import com.pondpedia.android.pondpedia.domain.use_case.ponds.pond_details.AddWaterRecordsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PondsModule {

    @Provides
    @Singleton
    fun provideAddCommodityGrowthRecordsUseCase(repository: PondDetailsRepository): AddCommodityGrowthRecordsUseCase {
        return AddCommodityGrowthRecordsUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideAddCommodityHealthRecordsUseCase(repository: PondDetailsRepository): AddCommodityHealthRecordsUseCase {
        return AddCommodityHealthRecordsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddCommodityUseCase(repository: PondDetailsRepository): AddCommodityUseCase {
        return AddCommodityUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAddFeedingRecordsUseCase(repository: PondDetailsRepository): AddFeedingRecordsUseCase {
        return AddFeedingRecordsUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideAddWaterRecordsUseCase(repository: PondDetailsRepository): AddWaterRecordsUseCase {
        return AddWaterRecordsUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideAddPondRecordsUseCase(repository: PondDetailsRepository): AddPondRecordsUseCase {
        return AddPondRecordsUseCase(repository)
    }

}