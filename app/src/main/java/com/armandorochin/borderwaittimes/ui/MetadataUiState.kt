package com.armandorochin.borderwaittimes.ui

import com.armandorochin.borderwaittimes.domain.model.BwtMetadata

sealed interface MetadataUiState {
    data object Initial : MetadataUiState
    data class Success(val data: BwtMetadata) : MetadataUiState
    data class Error(val message: String) : MetadataUiState
}