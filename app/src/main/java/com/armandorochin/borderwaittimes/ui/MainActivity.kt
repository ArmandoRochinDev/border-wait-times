package com.armandorochin.borderwaittimes.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.borderwaittimes.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityVM: MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupMetadataObserver()
    }

    private fun setupMetadataObserver(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainActivityVM.uiState.collect { state ->
                    when (state) {
                        is MetadataUiState.Initial -> {
                            // Show initial state
                            Log.v(TAG, "setupMetadataObserver: getting data from server...")
                            mainActivityVM.loadMetadata()
                        }
                        is MetadataUiState.Success -> {
                            // Display data: state.data
                            Log.i(TAG, "setupMetadataObserver: ${state.data}")
                            showToast(state.data.toString())
                        }
                        is MetadataUiState.Error -> {
                            // Show error: state.message
                            Log.e(TAG, "setupMetadataObserver: ${state.message}")
                            showToast(state.message)
                            //mainActivityVM.retry()
                        }
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val TAG = "MainActivity"
    }
}