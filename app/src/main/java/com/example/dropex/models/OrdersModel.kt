package com.example.dropex.models

import com.example.dropex.enums.Size

class OrdersModel(
    var id: Int, var quantity: Int, var size: Size, var userId: Int, var productId: Int
)