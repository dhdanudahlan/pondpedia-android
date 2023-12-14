package com.pondpedia.android.pondpedia.di

import com.pondpedia.android.pondpedia.domain.repository.PondDetailsRepository
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateEmailUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateInformationSourceUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateNameUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateOccupationUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidatePasswordUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidatePhoneNumberUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateRepeatedPasswordUseCase
import com.pondpedia.android.pondpedia.domain.use_case.auth.signup.ValidateTermsUseCase
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
object AuthModule {


}