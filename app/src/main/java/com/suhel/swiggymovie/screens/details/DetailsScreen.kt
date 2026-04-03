package com.suhel.swiggymovie.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.suhel.swiggymovie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(vm: DetailsViewModel = hiltViewModel(), onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    IconButton(onBack) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            DetailsContent(vm)
        }
    }
}

@Composable
private fun DetailsContent(vm: DetailsViewModel) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        DetailsUiState.Loading -> {
            CircularProgressIndicator()
        }

        is DetailsUiState.Failure -> {
            Text((uiState as DetailsUiState.Failure).message)
        }

        is DetailsUiState.Success -> {
            val asSuccess = uiState as DetailsUiState.Success
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = asSuccess.details.posterUrl,
                    contentDescription = null
                )
                Text(asSuccess.details.title)
                Text(asSuccess.details.year)
            }
        }
    }
}
