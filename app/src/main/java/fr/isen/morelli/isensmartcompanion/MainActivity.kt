package fr.isen.morelli.isensmartcompanion

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.bouchut.isensmartcompanion.R
import fr.isen.bouchut.isensmartcompanion.viewmodel.MainViewModel
import fr.isen.morelli.isensmartcompanion.data.HistoryEntry
import fr.isen.morelli.isensmartcompanion.screen.TabBarItem
import fr.isen.morelli.isensmartcompanion.screen.TabView
import fr.isen.morelli.isensmartcompanion.screens.EventsScreen
import fr.isen.morelli.isensmartcompanion.screens.HistoryScreen
import fr.isen.morelli.isensmartcompanion.screens.MainScreen
//import fr.isen.morelli.isensmartcompanion.screens.BottomNavigationBar.TabView
//import fr.isen.morelli.isensmartcompanion.screens.TabView
import fr.isen.morelli.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        Log.d("lifecycle", "MainActivity onCreate")
        setContent {

            val homeTab = TabBarItem(title = stringResource(R.string.Home), selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home)
            val eventsTab = TabBarItem(title = stringResource(R.string.event), selectedIcon = Icons.Filled.Notifications, unselectedIcon = Icons.Outlined.Notifications)
            val historyTab = TabBarItem(title = stringResource(R.string.History), selectedIcon = Icons.Filled.List, unselectedIcon = Icons.Outlined.List)

            val tabBarItems = listOf(homeTab, eventsTab, historyTab)

            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel()

            ISENSmartCompanionTheme {
                Scaffold(
                    bottomBar = { TabView(tabBarItems, navController) },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = homeTab.title) {
                        composable(homeTab.title) {
                            MainScreen(Modifier.padding(innerPadding))
                        }
                        composable(eventsTab.title) {
                            EventsScreen(Modifier.padding(innerPadding))
                        }
                        composable(historyTab.title) {
                            HistoryScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}
