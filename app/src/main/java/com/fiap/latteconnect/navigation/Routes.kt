package com.fiap.latteconnect.navigation

object Routes {
    const val HOME = "home"
    const val DONOR_REGISTER = "donor_register"
    const val REQUEST_DONATION = "request_donation"
    const val PARTNER_HOSPITALS = "partner_hospitals"
    const val COLLECTION_POINT_DETAIL = "partner_hospitals/{pointId}"
    const val POINT_ID_ARG = "pointId"
    const val ABOUT = "about"
    const val DASHBOARD = "dashboard"

    fun collectionPointDetail(pointId: String): String = "partner_hospitals/$pointId"
}
