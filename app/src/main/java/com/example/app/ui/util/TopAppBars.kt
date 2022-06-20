package com.example.app.ui.util

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
@Suppress("FunctionNaming")
fun PhoneContactsTopAppBar(@StringRes title: Int) {
    TopAppBar(
        title = { Text(text = stringResource(title)) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
@Suppress("FunctionNaming")
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
@Suppress("FunctionNaming")
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
@Suppress("UnusedPrivateMember", "FunctionNaming")
private fun PhoneContactsTopAppBarPreview() {
    AndroidComposeAppTheme {
        Surface {
            PhoneContactsTopAppBar(R.string.app_name)
        }
    }
}

@Preview
@Composable
@Suppress("UnusedPrivateMember", "FunctionNaming")
private fun PhoneContactTopAppBarPreview() {
    AndroidComposeAppTheme {
        Surface {
            PhoneContactTopAppBar(R.string.phone_contact, { }, { })
        }
    }
}

@Preview
@Composable
@Suppress("UnusedPrivateMember", "FunctionNaming")
private fun AddPhoneContactTopAppBarPreview() {
    AndroidComposeAppTheme {
        Surface {
            AddEditPhoneContactTopAppBar(R.string.add_phone_contact) { }
        }
    }
}

@Preview
@Composable
@Suppress("UnusedPrivateMember", "FunctionNaming")
private fun EditPhoneContactTopAppBarPreview() {
    AndroidComposeAppTheme {
        Surface {
            AddEditPhoneContactTopAppBar(R.string.edit_phone_contact) { }
        }
    }
}
