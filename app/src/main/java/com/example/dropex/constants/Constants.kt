package com.example.dropex.constants

object Constants {
    const val EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
    const val PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
    const val DATABASE_URL = "https://dropex-4ee37-default-rtdb.asia-southeast1.firebasedatabase.app"
    const val PRODUCT_ID_EXTRA = "productId"
}