package com.kienct.sodep

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import com.kienct.sodep.ui.theme.SodepTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SodepDrawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onHomeClicked: (String) -> Unit,
    content: @Composable () -> Unit) {
    SodepTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    SodepDrawerContent(
                        onHomeClicked = onHomeClicked
                    )
                }
            },
            content = content
        )
    }
}