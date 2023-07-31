package com.example.bizlibottommenubarcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bottomsheet2.screens.CashFlowScreen
import com.example.bottomsheet2.screens.MoreScreen
import com.example.bottomsheet2.screens.ReportScreen
import com.example.bottomsheet2.screens.SalesScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Reports.route)
    {
        composable(BottomBarScreen.Reports.route){
            ReportScreen()
        }


        composable(BottomBarScreen.Sales.route){
            SalesScreen()
        }


        composable(BottomBarScreen.CashFlow.route){
            CashFlowScreen()
        }


        composable(BottomBarScreen.More.route){
            MoreScreen()
        }

    }

}