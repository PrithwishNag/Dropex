package com.example.dropex.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dropex.R
import com.example.dropex.adpaters.OrderGVAdapter
import com.example.dropex.customComponents.WrappingGridView
import com.example.dropex.enums.Size
import com.example.dropex.models.OrdersModel

class PaymentGatewayScreen : AppCompatActivity() {
    private lateinit var orderGVAdapter: WrappingGridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_gateway_screen)
        orderGVAdapter = findViewById(R.id.summaryItemsGV)
        val ordersModelArrayList = ArrayList<OrdersModel?>()
        ordersModelArrayList.add(OrdersModel(1, 2, Size.l, 1, 2))
        ordersModelArrayList.add(OrdersModel(1, 1, Size.m, 1, 3))
        ordersModelArrayList.add(OrdersModel(1, 1, Size.xxl, 1, 4))
        val adapter = OrderGVAdapter(this, ordersModelArrayList)
        orderGVAdapter.adapter = adapter
    }
}