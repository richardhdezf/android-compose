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
import com.example.app.ui.PhoneContactsDestinationsArgs.PHONE_CONTACT_ID_ARG
import com.example.app.util.getViewModelFactory

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
/*
    val viewModel: PhoneContactsViewModel = viewModel(0
        factory = PhoneContactsViewModelFactory(appContainer.phoneContactsRepository)
    )
*/
    val viewModel: PhoneContactsViewModel =
        viewModel(factory = getViewModelFactory(appContainer = appContainer))
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(PhoneContactsDestinations.PHONE_CONTACTS_ROUTE) {
            PhoneContactsScreen(
                viewModel = viewModel,
                onAddItem = { navActions.navigateToAddEditPhoneContact(null) },
                onItemClick = { itemId -> navActions.navigateToPhoneContact(itemId) }
            )
        }
        composable(
            PhoneContactsDestinations.PHONE_CONTACT_ROUTE,
            arguments = listOf(navArgument(PHONE_CONTACT_ID_ARG) {
                type = NavType.StringType
            })
        ) { entry ->
            val itemId = entry.arguments?.getString(PHONE_CONTACT_ID_ARG)?.toInt()
            PhoneContactScreen(
                viewModel = viewModel,
                itemId = itemId!!,
/*
                onEditItem = { navActions.navigateToAddEditPhoneContact(itemId) },
*/
                onDeleteItem = { navActions.navigateToPhoneContacts() },
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            PhoneContactsDestinations.ADD_EDIT_PHONE_CONTACT_ROUTE,
            arguments = listOf(navArgument(PHONE_CONTACT_ID_ARG) {
                type = NavType.StringType; nullable = true
            })
        ) { entry ->
            val itemId = entry.arguments?.getString(PHONE_CONTACT_ID_ARG)?.toInt()
            AddEditPhoneContactScreen(
                viewModel = viewModel,
                itemId = itemId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}