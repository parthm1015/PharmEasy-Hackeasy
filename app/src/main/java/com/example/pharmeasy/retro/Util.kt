package com.pharmeasy.picker.retro

import AuthenticationInterceptor

import com.example.pharmeasy.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager

var success_time = 1
var failure_time = 2
val REQ_CODE_UCODE_SELECTION = 129
var CHANNEL_ID = "123456"
var notificationId = 1

val errorcode_app_update = "APP_IS_OUTDATED"

val issueTypeMissingBarcode = "missingBarcode"

val issueTypeWrongBarcode = "wrongBarcode"

val iso_format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

private fun retrofit(interceptor: AuthenticationInterceptor? = null): Retrofit {

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val builder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(NetworkHeadersInterceptor())
            .addInterceptor(GZipResponseInterceptor())
            .addInterceptor(loggingInterceptor)

    if (interceptor != null)
        builder.addInterceptor(interceptor)

    if (BuildConfig.BUILD_TYPE != "release") {
        getTrustAllHostsSSLSocketFactory()?.let {
            builder.sslSocketFactory(it)
        }
    }
    //builder.sslSocketFactory(getSslContextForCertificateFile(FetchrApplication.getApplicationContex(), "customcertificate.pem").socketFactory)


    return Retrofit.Builder().baseUrl("https://staging.thea.scm.gomercury.in/api/")
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

fun <T> retro(service: Class<T>): T {
    return retrofit().create(service)
}


val gson by lazy {
    GsonBuilder().create()
}


fun isAlphaNumeric(s: String): Boolean {
    val pattern = "^[a-zA-Z0-9]*$"
    return s.matches(pattern.toRegex())
}


fun formatDate(date:String):String{
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val date = formatter.parse(date)
    val desiredFormat = SimpleDateFormat("MM/yy", Locale.ENGLISH).format(date)
    return desiredFormat
}
fun getTrustAllHostsSSLSocketFactory(): SSLSocketFactory? {
    try {
        val trustAllCerts = SslUtils.getTrustManager()
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf<TrustManager>(trustAllCerts), SecureRandom())
        return sslContext.socketFactory
    } catch (e: KeyManagementException) {
        e.printStackTrace()
        return null
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        return null
    }
}