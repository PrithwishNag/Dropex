package com.example.dropex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.dropex.R
import com.example.dropex.adpaters.OrderGVAdapter
import com.example.dropex.enums.Size
import com.example.dropex.models.OrdersModel

class OrdersFragment : Fragment() {
    private lateinit var orderGVAdapter: GridView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        orderGVAdapter = view.findViewById(R.id.ordersGV)
        val ordersModelArrayList = ArrayList<OrdersModel?>()
        ordersModelArrayList.add(OrdersModel(1, 2, Size.l, 1, 2))
        ordersModelArrayList.add(OrdersModel(1, 1, Size.m, 1, 3))
        ordersModelArrayList.add(OrdersModel(1, 1, Size.xxl, 1, 4))
        val adapter = OrderGVAdapter(view.context, ordersModelArrayList)
        orderGVAdapter.adapter = adapter
    }
}