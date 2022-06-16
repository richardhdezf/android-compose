package com.example.app.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.app.ui.PhoneContactsDestinationsArgs.PHONE_CONTACT_ID_ARG
import com.example.app.ui.PhoneContactsScreens.ADD_EDIT_PHONE_CONTACT_SCREEN
import com.example.app.ui.PhoneContactsScreens.PHONE_CONTACTS_SCREEN
import com.example.app.ui.PhoneContactsScreens.PHONE_CONTACT_SCREEN

private object PhoneContactsScreens {
    const val PHONE_CONTACTS_SCREEN = "phoneContacts"
    const val PHONE_CONTACT_SCREEN = "phoneContact"
    const val ADD_EDIT_PHONE_CONTACT_SCREEN = "addEditPhoneContact"
}

object PhoneContactsDestinationsArgs {
    const val PHONE_CONTACT_ID_ARG = "phoneContactId"
}

object PhoneContactsDestinations {
    const val PHONE_CONTACTS_ROUTE = PHONE_CONTACTS_SCREEN
    const val PHONE_CONTACT_ROUTE = "$PHONE_CONTACT_SCREEN/{$PHONE_CONTACT_ID_ARG}"
    const val ADD_EDIT_PHONE_CONTACT_ROUTE =
        "$ADD_EDIT_PHONE_CONTACT_SCREEN?$PHONE_CONTACT_ID_ARG={$PHONE_CONTACT_ID_ARG}"
}

/**
 * Models the navigation actions in the app.
 */
class PhoneContactsNavigationActions(private val navController: NavHostController) {

    fun navigateToPhoneContacts() {
        navController.navigate(PHONE_CONTACTS_SCREEN) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
/*
                saveState = false
*/
            }
/*
            launchSingleTop = true
*/
/*
            restoreState = false
*/
        }
    }

    fun navigateToPhoneContact(phoneContactId: Int) {
        navController.navigate("$PHONE_CONTACT_SCREEN/$phoneContactId")
    }

    fun navigateToAddEditPhoneContact(phoneContactId: Int?) {
        navController.navigate(
            ADD_EDIT_PHONE_CONTACT_SCREEN.let {
                if (phoneContactId != null) "$it?$PHONE_CONTACT_ID_ARG=$phoneContactId" else it
            }
        )
    }
}
