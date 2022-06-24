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

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.app.R
import com.example.app.presentation.PhoneContactsUiState
import com.example.app.presentation.PhoneContactsViewModel
import com.example.app.ui.util.PhoneContactsTopAppBar

@Composable
fun PhoneContactsScreen(
    tabContent: List<TabContent>,
    currentSection: Sections,
    onSectionChange: (Sections) -> Unit,
    viewModel: PhoneContactsViewModel,
    onAddItem: () -> Unit,
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
        val uiState by viewModel.uiState.collectAsState(initial = PhoneContactsUiState())

        if (uiState.items.isEmpty()) PhoneContactsEmptyContent() else PhoneContactsContent(
            tabContent = tabContent,
            currentSection = currentSection,
            onSectionChange = onSectionChange
        )
    }
}

@Composable
private fun PhoneContactsContent(
    tabContent: List<TabContent>,
    currentSection: Sections,
    onSectionChange: (Sections) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTabIndex = tabContent.indexOfFirst { it.section == currentSection }
    Column(modifier) {
        PhoneContactsTabRow(selectedTabIndex, tabContent, onSectionChange)
        Divider(
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
        )
        Box(modifier = Modifier.weight(1f)) {
            // display the current tab content which is a @Composable () -> Unit
            tabContent[selectedTabIndex].content()
        }
    }
}

@Composable
private fun PhoneContactsEmptyContent(
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
