package com.example.app.ui.addEditPhoneContact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.presentation.AddEditPhoneContactViewModel
import com.example.app.ui.AddEditPhoneContactScreenAttributes
import com.example.app.ui.util.AddEditPhoneContactTopAppBar

@Composable
fun AddEditPhoneContactScreen(
    viewModel: AddEditPhoneContactViewModel,
    screenAttrs: AddEditPhoneContactScreenAttributes,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val itemState = viewModel.getItem().collectAsState()
    val topBarTitle =
        if (itemState.value.isEditing) R.string.edit_phone_contact else R.string.add_phone_contact

    Scaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { AddEditPhoneContactTopAppBar(topBarTitle, screenAttrs.onBack) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.save()
                screenAttrs.onSave()
            }) {
                Icon(Icons.Filled.Done, stringResource(id = R.string.save_phone_contact))
            }
        }
    ) { paddingValues ->
        AddEditPhoneContactContent(
            name = itemState.value.name,
            phone = itemState.value.phone,
            isFavorite = itemState.value.isFavorite,
            onNameChanged = { viewModel.updateName(it) },
            onPhoneChanged = { viewModel.updatePhone(it) },
            onFavoriteChanged = { viewModel.updateFavoriteStatus(it) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun AddEditPhoneContactContent(
    name: String,
    phone: String,
    isFavorite: Boolean,
    onNameChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onFavoriteChanged: (Boolean) -> Unit,
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
                .fillMaxWidth(),
            colors = textFieldColors
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.list_item_padding))
        ) {
            Checkbox(
                checked = isFavorite,
                onCheckedChange = { onFavoriteChanged(it) }
            )
            Text(
                text = stringResource(id = R.string.is_favorite),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.vertical_checkout_text_margin),
                    start = dimensionResource(id = R.dimen.horizontal_margin)
                )
            )
        }
    }
}
