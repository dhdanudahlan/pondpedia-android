package com.pondpedia.android.pondpedia.domain.use_case

import com.pondpedia.android.pondpedia.domain.manager.LocalUserManager

class SaveAppEntryUseCase(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}