package com.example.bottomsheet2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bizlibottommenubarcompose.BottomBarScreen
import com.example.bizlibottommenubarcompose.BottomNavGraph
import com.example.bottomsheet2.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController= rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController)},
        content = {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)) {
            BottomNavGraph(navController =navController)
        }
    })

}

@Composable
fun BottomBar(navController: NavHostController) {

    val screens= listOf(
        BottomBarScreen.Reports,
        BottomBarScreen.Sales,
        BottomBarScreen.CashFlow,
        BottomBarScreen.More
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination= navBackStackEntry?.destination

    BottomNavigation(backgroundColor = Color.White) {
        screens.forEach { screen->
            AddItem(screen = screen,
                currentDestination = currentDestination,
                navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(screen: BottomBarScreen,
                     currentDestination: NavDestination?,
                     navController: NavHostController
) {

    BottomNavigationItem(
        label = {
            Text(text = screen.title, fontSize = 12.sp)
        },
        icon = { Icon(painter = painterResource(id = screen.icon), tint = colorResource(id = R.color.colorPrimary),
            contentDescription = "Navigation Icon")},
        selected = currentDestination?.hierarchy?.any {
            it.route== screen.route
        } == true,
        onClick = {navController.navigate(screen.route){
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        } },
        alwaysShowLabel = false,
    )
}

