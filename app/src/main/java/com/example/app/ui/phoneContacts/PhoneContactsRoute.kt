package com.example.app.ui.phoneContacts

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.app.presentation.PhoneContactsViewModel

@Composable
fun PhoneContactsRoute(
    viewModel: PhoneContactsViewModel,
    onAddItem: () -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val tabContent = rememberTabContent(viewModel, onItemClick)
    val (currentSection, updateSection) = rememberSaveable {
        mutableStateOf(tabContent.first().section)
    }

    PhoneContactsScreen(
        tabContent = tabContent,
        currentSection = currentSection,
        onSectionChange = updateSection,
        viewModel = viewModel,
        onAddItem = onAddItem,
        modifier = modifier,
        scaffoldState = scaffoldState
    )
}
