package com.example.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app.di.AppContainer
import com.example.app.presentation.AddEditPhoneContactViewModel
import com.example.app.presentation.PhoneContactViewModel
import com.example.app.presentation.PhoneContactsViewModel
import com.example.app.ui.PhoneContactsDestinationsArgs.PHONE_CONTACT_ID_ARG
import com.example.app.ui.addEditPhoneContact.AddEditPhoneContactScreen
import com.example.app.ui.phoneContact.PhoneContactScreen
import com.example.app.ui.phoneContacts.PhoneContactsScreen
import com.example.app.ui.util.getPhoneContactsViewModelFactory

@Composable
fun PhoneContactsNavGraph(
    appContainer: AppContainer,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = PhoneContactsDestinations.PHONE_CONTACTS_ROUTE,
    navActions: PhoneContactsNavigationActions = remember(navController) {
        PhoneContactsNavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(PhoneContactsDestinations.PHONE_CONTACTS_ROUTE) {
            val viewModel: PhoneContactsViewModel =
                viewModel(factory = getPhoneContactsViewModelFactory(appContainer = appContainer))
            PhoneContactsScreen(
                viewModel = viewModel,
                onAddItem = { navActions.navigateToAddEditPhoneContact(null) },
                onItemClick = { itemId -> navActions.navigateToPhoneContact(itemId) }
            )
        }
        composable(
            PhoneContactsDestinations.PHONE_CONTACT_ROUTE,
            arguments = listOf(
                navArgument(PHONE_CONTACT_ID_ARG) {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val viewModel: PhoneContactViewModel =
                viewModel(
                    factory = getPhoneContactsViewModelFactory(
                        appContainer = appContainer,
                        entry.arguments
                    )
                )
            val screenAttributes = PhoneContactScreenAttributes(
                onEditItem = { navActions.navigateToAddEditPhoneContact(it) },
                onDeleteItem = { navActions.navigateToPhoneContacts() },
                onBack = { navController.popBackStack() }
            )

            PhoneContactScreen(
                viewModel = viewModel,
                screenAttrs = screenAttributes
            )
        }
        composable(
            PhoneContactsDestinations.ADD_EDIT_PHONE_CONTACT_ROUTE,
            arguments = listOf(
                navArgument(PHONE_CONTACT_ID_ARG) {
                    type = NavType.StringType; nullable = true
                }
            )
        ) { entry ->
            val viewModel: AddEditPhoneContactViewModel =
                viewModel(
                    factory = getPhoneContactsViewModelFactory(
                        appContainer = appContainer,
                        entry.arguments
                    )
                )
            val screenAttributes = AddEditPhoneContactScreenAttributes(
                onSave = { navActions.navigateToPhoneContacts() },
                onBack = { navController.popBackStack() }
            )

            AddEditPhoneContactScreen(
                viewModel = viewModel,
                screenAttrs = screenAttributes
            )
        }
    }
}

data class PhoneContactScreenAttributes(
    val onEditItem: (Int) -> Unit,
    val onDeleteItem: () -> Unit,
    val onBack: () -> Unit,
)

data class AddEditPhoneContactScreenAttributes(
    val onSave: () -> Unit,
    val onBack: () -> Unit,
)
