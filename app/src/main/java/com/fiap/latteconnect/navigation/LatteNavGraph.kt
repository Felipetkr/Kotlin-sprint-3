package com.fiap.latteconnect.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fiap.latteconnect.data.LatteMockData
import com.fiap.latteconnect.ui.screens.AboutScreen
import com.fiap.latteconnect.ui.screens.CollectionPointDetailScreen
import com.fiap.latteconnect.ui.screens.DashboardScreen
import com.fiap.latteconnect.ui.screens.DonationRequestScreen
import com.fiap.latteconnect.ui.screens.DonorRegisterScreen
import com.fiap.latteconnect.ui.screens.HomeScreen
import com.fiap.latteconnect.ui.screens.PartnerHospitalsScreen

@Composable
fun LatteNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(onNavigate = { route -> navController.navigate(route) })
        }
        composable(Routes.DONOR_REGISTER) {
            DonorRegisterScreen(
                onBackClick = { navController.popBackStack() },
                onViewHospitalsClick = { navController.navigate(Routes.PARTNER_HOSPITALS) }
            )
        }
        composable(Routes.REQUEST_DONATION) {
            DonationRequestScreen(
                onBackClick = { navController.popBackStack() },
                onViewHospitalsClick = { navController.navigate(Routes.PARTNER_HOSPITALS) }
            )
        }
        composable(Routes.PARTNER_HOSPITALS) {
            PartnerHospitalsScreen(
                onBackClick = { navController.popBackStack() },
                onPointClick = { pointId -> navController.navigate(Routes.collectionPointDetail(pointId)) }
            )
        }
        composable(
            route = Routes.COLLECTION_POINT_DETAIL,
            arguments = listOf(navArgument(Routes.POINT_ID_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            val pointId = backStackEntry.arguments?.getString(Routes.POINT_ID_ARG)
            CollectionPointDetailScreen(
                point = LatteMockData.collectionPointById(pointId),
                relatedPoints = LatteMockData.collectionPoints,
                onBackClick = { navController.popBackStack() },
                onDonorRegisterClick = { navController.navigate(Routes.DONOR_REGISTER) },
                onRequestDonationClick = { navController.navigate(Routes.REQUEST_DONATION) }
            )
        }
        composable(Routes.ABOUT) {
            AboutScreen(onBackClick = { navController.popBackStack() })
        }
        composable(Routes.DASHBOARD) {
            DashboardScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
