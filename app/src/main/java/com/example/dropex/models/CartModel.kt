package com.example.dropex.models

import com.example.dropex.enums.Size

class CartModel(
    var quantity: Int, var size: String, var userId: String, var productId: String
)