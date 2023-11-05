package com.pondpedia.compose.pondpedia.domain.use_case.ponds

import com.pondpedia.compose.pondpedia.domain.repository.PondsRepository

class DeletePondByIdUseCase (
    private val repository: PondsRepository
) {
    suspend operator fun invoke(pondId: Long) {
        repository.deletePondById(pondId = pondId)
    }
}
