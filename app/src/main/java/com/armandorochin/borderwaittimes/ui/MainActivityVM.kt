package com.armandorochin.borderwaittimes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armandorochin.borderwaittimes.domain.usecase.GetMetadata
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val getMetadata: GetMetadata
) : ViewModel() {

    private val _uiState = MutableStateFlow<MetadataUiState>(MetadataUiState.Initial)
    val uiState: StateFlow<MetadataUiState> = _uiState.asStateFlow()

    fun loadMetadata() {
        viewModelScope.launch {
            getMetadata()
                .onSuccess { metadata ->
                    _uiState.value = MetadataUiState.Success(metadata)
                }
                .onFailure { exception ->
                    _uiState.value = MetadataUiState.Error(
                        exception.message ?: "Unknown error occurred"
                    )
                }
        }
    }

    fun retry() {
        loadMetadata()
    }
}