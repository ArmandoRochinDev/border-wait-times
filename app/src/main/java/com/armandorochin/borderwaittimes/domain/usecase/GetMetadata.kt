package com.armandorochin.borderwaittimes.domain.usecase

import com.armandorochin.borderwaittimes.data.remote.bwt.BwtRepository
import com.armandorochin.borderwaittimes.domain.model.BwtMetadata
import javax.inject.Inject

class GetMetadata @Inject constructor(
    private val bwtRepository: BwtRepository
) {
    suspend operator fun invoke(): Result<BwtMetadata> {
        return bwtRepository.fetchBwtFromServer()
                .mapCatching { bwt ->
                    bwt.toBwtMetadata()
                }

    }
}