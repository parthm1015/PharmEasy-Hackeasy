package com.example.pharmeasy.service


object SessionService {

    var token: String = ""
    var userId: String = ""
    var name: String? = ""
    var username: String = ""
    var role: String = ""
    var referenceId = ""

    var breakStart: Long = System.currentTimeMillis()
    var review = false
    var deviceId = ""
    var fcmToken = ""
    var isScanBin = false
    var tenant: String = ""
}
