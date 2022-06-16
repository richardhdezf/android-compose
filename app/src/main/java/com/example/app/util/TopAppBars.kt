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

package com.example.app.util

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.app.R
import com.example.app.ui.theme.AndroidComposeAppTheme

@Composable
fun PhoneContactsTopAppBar(@StringRes title: Int) {
    TopAppBar(
        title = { Text(text = stringResource(title)) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PhoneContactTopAppBar(@StringRes title: Int, onBack: () -> Unit, onDelete: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(title))
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, stringResource(id = R.string.menu_back))
            }
        },
        actions = {
            IconButton(onClick = onDelete) {
                Icon(Icons.Filled.Delete, stringResource(id = R.string.menu_delete_phone_contact))
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AddEditPhoneContactTopAppBar(@StringRes title: Int, onBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(title)) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, stringResource(id = R.string.menu_back))
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun PhoneContactsTopAppBarPreview() {
    AndroidComposeAppTheme{
        Surface {
            PhoneContactsTopAppBar(R.string.app_name)
        }
    }
}

@Preview
@Composable
private fun PhoneContactTopAppBarPreview() {
    AndroidComposeAppTheme {
        Surface {
            PhoneContactTopAppBar(R.string.phone_contact, { }, { })
        }
    }
}

@Preview
@Composable
private fun AddEditPhoneContactTopAppBarPreview() {
    AndroidComposeAppTheme {
        Surface {
            AddEditPhoneContactTopAppBar(R.string.add_phone_contact) { }
        }
    }
}
