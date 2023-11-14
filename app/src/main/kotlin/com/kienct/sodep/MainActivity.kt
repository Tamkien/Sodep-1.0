package com.kienct.sodep

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kienct.sodep.databinding.ContentMainBinding
import com.kienct.sodep.ui.theme.SodepTheme
import kotlinx.coroutines.launch


/**
 * Main activity for the app.
 */
class MainActivity : AppCompatActivity() {
    private val viewModel: ViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val drawerOpen by viewModel.drawerShouldBeOpened.collectAsStateWithLifecycle()
            if (drawerOpen) {
                // Open drawer and reset state in VM.
                LaunchedEffect(Unit) {
                    // wrap in try-finally to handle interruption whiles opening drawer
                    try {
                        drawerState.open()
                    } finally {
                        viewModel.resetOpenDrawerAction()
                    }
                }
            }

            // Intercepts back navigation when the drawer is open
            val scope = rememberCoroutineScope()
            if (drawerState.isOpen) {
                BackHandler {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }

            SodepDrawer(
                drawerState = drawerState,
                onHomeClicked = {
                    findNavController().popBackStack(R.id.nav_home, false)
                    scope.launch {
                        drawerState.close()
                    }
                },
            ) {
                AndroidViewBinding(
                    ContentMainBinding::inflate
                )
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp() || super.onSupportNavigateUp()
    }

    /**
     * See https://issuetracker.google.com/142847973
     */
    private fun findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}

@Composable
fun SodepApp() {
    SodepTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}

//make a preview of HomeScreen
@Composable
@Preview
fun PreviewScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        HomeScreen()
    }
}