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

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.data.PhoneContact
import com.example.app.presentation.PhoneContactsViewModel

enum class Sections(@StringRes val titleResId: Int) {
    All(R.string.phone_contacts_section_all),
    Recent(R.string.phone_contacts_section_recent),
}

class TabContent(val section: Sections, val content: @Composable () -> Unit)

@Composable
fun rememberTabContent(
    viewModel: PhoneContactsViewModel,
    onItemClick: (Int) -> Unit
): List<TabContent> {
    val uiState by viewModel.uiState.collectAsState()
    val items = uiState.items
    val allSection = TabContent(Sections.All) {
        TabWithItems(
            items = items, onItemClick = onItemClick
        )
    }

    val recentSection = TabContent(Sections.Recent) {
        val favoriteItems = items.filter { it.isFavorite }
        TabWithItems(
            items = favoriteItems, onItemClick = onItemClick
        )
    }

    return listOf(allSection, recentSection)
}

@Composable
private fun TabWithItems(
    items: List<PhoneContact>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn {
        items(items) { item ->
            PhoneContactItem(
                item = item,
                onClick = onItemClick
            )
        }
    }
}

@Composable
private fun PhoneContactItem(
    item: PhoneContact,
    onClick: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.horizontal_margin),
                vertical = dimensionResource(id = R.dimen.list_item_padding),
            )
            .clickable { onClick(item.id!!) }
    ) {
        Column {
            Text(text = item.name, style = MaterialTheme.typography.h6)
            Text(text = item.phone, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun PhoneContactsTabRow(
    selectedTabIndex: Int,
    tabContent: List<TabContent>,
    onSectionChange: (Sections) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = MaterialTheme.colors.primary
    ) {
        PhoneContactsTabRowContent(selectedTabIndex, tabContent, onSectionChange)
    }
}

@Composable
private fun PhoneContactsTabRowContent(
    selectedTabIndex: Int,
    tabContent: List<TabContent>,
    onSectionChange: (Sections) -> Unit,
    modifier: Modifier = Modifier
) {
    tabContent.forEachIndexed { index, content ->
        val colorText = if (selectedTabIndex == index) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
        }
        Tab(
            selected = selectedTabIndex == index,
            onClick = { onSectionChange(content.section) },
            modifier = Modifier.heightIn(min = 48.dp)
        ) {
            Text(
                text = stringResource(id = content.section.titleResId),
                color = colorText,
                style = MaterialTheme.typography.subtitle2,
                modifier = modifier.paddingFromBaseline(top = 20.dp)
            )
        }
    }
}
