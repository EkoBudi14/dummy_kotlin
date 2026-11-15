package com.example.myapplication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserView(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel()
) {
    val state by userViewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier.fillMaxSize()
    ) {
        when(val s = state) {
            is UiState.OnLoading -> Box(
                modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            is UiState.OnError ->
                Text(s.message)


            is UiState.OnSuccess -> LazyColumn() {
                items(s.users, key = { it.id }) {
                    ListItem(
                        headlineContent = { Text(it.name) },
                        supportingContent = { Text(it.email) }
                    )
                }
            }
        }
    }
}

