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
package com.example.app.ui.phoneContact

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.app.R
import com.example.app.presentation.PhoneContactUiState
import com.example.app.presentation.PhoneContactViewModel
import com.example.app.ui.PhoneContactScreenAttributes
import com.example.app.ui.util.PhoneContactTopAppBar

@Composable
fun PhoneContactScreen(
    viewModel: PhoneContactViewModel,
    screenAttrs: PhoneContactScreenAttributes,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val itemState = viewModel.loadAt().collectAsState(initial = PhoneContactUiState())

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        topBar = {
            PhoneContactTopAppBar(
                R.string.phone_contact,
                onBack = screenAttrs.onBack,
                onDelete = {
                    viewModel.delete()
                    screenAttrs.onDeleteItem()
                }
            )
        },
        floatingActionButton = {
            if (!itemState.value.isLoading) FloatingActionButton(onClick = {
                screenAttrs.onEditItem(
                    itemState.value.id!!
                )
            }) {
                Icon(Icons.Filled.Edit, stringResource(id = R.string.edit_phone_contact))
            }
        }
    ) { paddingValues ->
        if (itemState.value.isLoading) PhoneContactEmptyContent() else {
            val itemAttributes =
                PhoneContactContentAttrs(
                    itemState.value.name,
                    itemState.value.phone,
                    itemState.value.isFavorite
                )
            PhoneContactContent(
                attributes = itemAttributes,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun PhoneContactContent(
    attributes: PhoneContactContentAttrs,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.horizontal_margin))
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = attributes.name, style = MaterialTheme.typography.h6)
        Text(text = attributes.phone, style = MaterialTheme.typography.body1)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.list_item_padding))
        ) {
            Checkbox(checked = attributes.isFavorite, onCheckedChange = null)
            Text(
                text = stringResource(id = R.string.is_favorite),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.horizontal_margin)
                )
            )
        }
    }
}

@Composable
private fun PhoneContactEmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading))
    }
}

data class PhoneContactContentAttrs(
    val name: String,
    val phone: String,
    val isFavorite: Boolean
)
