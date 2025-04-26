package com.example.mvvmcoroutine.model

data class GetCardResponse(
    val `data`: List<Data>,
    val message: String?, // List fetched successfully
    val success: Boolean? // true
) {
    data class Data(
        val _id: String?, // 680b957e43bc21b8a949e0d5
        val cardHolderName: String?, // AnKiT
        val createdAt: String?, // 2025-04-25T14:00:30.476Z
        val driverId: Any?, // null
        val isDeleted: Boolean?, // false
        val lastFourDigitCard: String?, // 4242
        val paymentBrand: String?, // VISA
        val regCardToken: String?, // 8ac7a4a2966b054001966d3ff3b74da9
        val status: Boolean?, // true
        val tokenExpiryMonth: String?, // 01
        val tokenExpiryYear: String?, // 2035
        val updatedAt: String?, // 2025-04-25T14:00:30.476Z
        val userId: UserId?
    ) {
        data class UserId(
            val LastFourDigits: String?, // 4242
            val _id: String?, // 67727e4e1451cd24d8cd8bb6
            val accessToken: String?, // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOjExMTExMTExMTEsImlhdCI6MTc0NTU3MTA0NywiZXhwIjoxNzQ4MTYzMDQ3fQ.h7XF-48KUSDFior1hDn19lYmtyq0y6T3zrQ4pvdTh08
            val countryCode: String?, // +966
            val createdAt: Long?, // 1745571047115
            val customerId: String?, // cus_RUg89jYyS9WJ42
            val deviceToken: String?, // ff20qcHTSsKBrUeldcYTr9:APA91bEqyOsUGTFkVivn_zWkSzLUNJMaC8uwntReJqVmLcUZqQrbXKE6atMI3P6AhsRAu6bLfPYWymMYByNh0l7Rjmss6DjPE164moUQIlEIuCXOIFszn_U
            val deviceType: Int?, // 1
            val email: String?, // ankitdeveloper143@gmail.com
            val firstName: String?, // Developer 
            val isBlocked: Int?, // 0
            val isDeleted: Int?, // 0
            val isEmailVerified: Boolean?, // true
            val isNotify: Boolean?, // true
            val isProfileCreated: Boolean?, // true
            val isVerified: Boolean?, // true
            val lastName: String?, // Ankit
            val location: Location?,
            val mobile: Int?, // 1111111111
            val otpInfo: OtpInfo?,
            val profileImage: String?, // https://intalaq-bucket.s3.me-central-1.amazonaws.com/1737110067561/1000013689.jpg
            val referralCode: String?, // B00U9Q4I
            val referrerDate: Any?, // null
            val referrerId: Any?, // null
            val saveCardRegToken: String?, // 8ac7a4a2966b054001966d3ff3b74da9
            val serviceType: Int?, // 0
            val social_id: String?,
            val social_type: String?,
            val tokenExpiryMonth: String?, // 01
            val tokenExpiryYear: String?, // 2035
            val updatedAt: Long?, // 1737449939458
            val walletAmount: Int?, // 90603
            val walletPin: String? // 1234
        ) {
            data class Location(
                val address: String?, // 142,Uttar Pradesh,Sector 63,Noida,IN,142,201301
                val city: String?, // Noida
                val coordinates: List<Double?>?,
                val type: String? // Point
            )

            data class OtpInfo(
                val expTime: String?, // 2025-04-25T08:50:47.115Z
                val otp: Any? // null
            )
        }
    }
}