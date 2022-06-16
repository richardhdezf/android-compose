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
package com.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.app.R
import com.example.app.data.PhoneContact
import com.example.app.util.PhoneContactTopAppBar

@Composable
fun PhoneContactScreen(
    viewModel: PhoneContactsViewModel,
    itemId: Int,
/*
    onEditItem: () -> Unit,
*/
    onDeleteItem: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val itemState = viewModel.getItem(itemId).collectAsState(null)

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        topBar = {
            PhoneContactTopAppBar(R.string.phone_contact, onBack = onBack, onDelete = {
                viewModel.delete(itemState.value!!)
                onDeleteItem()
            })
        }/*,
        floatingActionButton = {
            FloatingActionButton(onClick = { onEditItem() }) {
                Icon(Icons.Filled.Edit, stringResource(id = R.string.edit_phone_contact))
            }
        }
*/
    ) { paddingValues ->
        if (itemState.value == null) PhoneContactEmptyContent() else PhoneContactContent(
            item = itemState.value!!,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun PhoneContactContent(
    item: PhoneContact,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.horizontal_margin))
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = item.name, style = MaterialTheme.typography.h6)
        Text(text = item.phone, style = MaterialTheme.typography.body1)
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