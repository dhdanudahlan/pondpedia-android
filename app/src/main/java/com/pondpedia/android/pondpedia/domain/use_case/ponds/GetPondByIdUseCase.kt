package com.pondpedia.android.pondpedia.domain.use_case.ponds

import com.pondpedia.android.pondpedia.domain.model.pond_management.Pond
import com.pondpedia.android.pondpedia.domain.repository.PondsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPondByIdUseCase (
    private val repository: PondsRepository
) {
    operator fun invoke(
        pondId: Long,
    ): Flow<Pond> {
        val pond = repository.getPondById(pondId).map {
                if(it.isNotEmpty()) {
                        it.first()
                } else {
                    Pond()
                }
            }
        return pond
    }
}
