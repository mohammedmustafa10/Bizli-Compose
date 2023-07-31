package com.example.bizlibottommenubarcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bottomsheet2.R

sealed class BottomBarScreen(
    val route:String,
    val title:String,
    val icon: Int
){

    object Reports:BottomBarScreen(
        route="reports",
        title = "Reports",
        icon= R.drawable.reports
    )

    object Sales:BottomBarScreen(
        route="sales",
        title = "Sales",
        icon = R.drawable.sales
    )

    object CashFlow:BottomBarScreen(
        route="cashflow",
        title = "CashFlow",
        icon= R.drawable.cashflow
    )

    object More:BottomBarScreen(
        route="more",
        title = "More",
        icon= R.drawable.more
    )
}
