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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.util.AddEditPhoneContactTopAppBar

//Only add values
@Composable
fun AddEditPhoneContactScreen(
    viewModel: PhoneContactsViewModel,
    itemId: Int?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val topBarTitle =  R.string.add_phone_contact

    var nameInput by rememberSaveable { mutableStateOf("") }
    var phoneInput by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { AddEditPhoneContactTopAppBar(topBarTitle, onBack) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.save(itemId, nameInput, phoneInput)
                onBack()
            }) {
                Icon(Icons.Filled.Done, stringResource(id = R.string.save_phone_contact))
            }
        }
    ) { paddingValues ->
        AddEditPhoneContactContent(
            name = nameInput,
            phone = phoneInput,
            onNameChanged = { nameInput = it },
            onPhoneChanged = { phoneInput = it },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun AddEditPhoneContactContent(
    name: String,
    phone: String,
    onNameChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.horizontal_margin))
            .verticalScroll(rememberScrollState())
    ) {
        val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high)
        )
        OutlinedTextField(
            value = name,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onNameChanged,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.name_hint),
                    style = MaterialTheme.typography.h6
                )
            },
            textStyle = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
            maxLines = 1,
            colors = textFieldColors
        )
        OutlinedTextField(
            value = phone,
            onValueChange = onPhoneChanged,
            placeholder = { Text(stringResource(id = R.string.phone_hint)) },
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth(),
            colors = textFieldColors
        )
    }
}
