package com.pondpedia.android.pondpedia.domain.use_case.ponds

import com.pondpedia.android.pondpedia.domain.repository.PondsRepository

class DeletePondByIdUseCase (
    private val repository: PondsRepository
) {
    suspend operator fun invoke(pondId: Long) = repository.deletePondById(pondId = pondId)

}
