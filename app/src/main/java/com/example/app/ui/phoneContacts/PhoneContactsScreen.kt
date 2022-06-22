/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.app.ui.phoneContacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.app.R
import com.example.app.data.PhoneContact
import com.example.app.presentation.PhoneContactsUiState
import com.example.app.presentation.PhoneContactsViewModel
import com.example.app.ui.util.PhoneContactsTopAppBar

@Composable
fun PhoneContactsScreen(
    viewModel: PhoneContactsViewModel,
    onAddItem: () -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            PhoneContactsTopAppBar(R.string.app_name)
        },
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = onAddItem) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.add_phone_contact))
            }
        }
    ) {
        val uiState by viewModel.load().collectAsState(initial = PhoneContactsUiState() )

        if (uiState.items.isEmpty()) TasksEmptyContent() else PhoneContactsContent(
            items = uiState.items,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun PhoneContactsContent(
    items: List<PhoneContact>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn {
        items(items) { item ->
            PhoneContactItem(
                item = item,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
private fun PhoneContactItem(
    item: PhoneContact,
    onItemClick: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_margin),
                vertical = dimensionResource(id = R.dimen.list_item_padding),
            )
            .clickable { onItemClick(item.id!!) }
    ) {
        Column {
            Text(text = item.name, style = MaterialTheme.typography.h6)
            Text(text = item.phone, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
private fun TasksEmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.no_phone_contacts))
    }
}
