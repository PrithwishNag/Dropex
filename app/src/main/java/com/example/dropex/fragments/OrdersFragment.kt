package com.example.dropex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dropex.R
import com.example.dropex.adpaters.OrderGVAdapter
import com.example.dropex.database.OrdersRepository
import com.example.dropex.database.ProductsRepository
import com.example.dropex.models.ProductModel
import kotlinx.coroutines.launch

class OrdersFragment : Fragment() {
    private lateinit var orderGV: GridView
    private var ordersRepository: OrdersRepository = OrdersRepository()
    private var productsRepository: ProductsRepository = ProductsRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        orderGV = view.findViewById(R.id.ordersGV)
        val loadingBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val noItemsFoundTV = view.findViewById<TextView>(R.id.noItemsFoundTV)

        orderGV.visibility = View.GONE
        loadingBar.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            val ordersModelArrayList = ordersRepository.getOrders()
            if(ordersModelArrayList.size == 0) {
                loadingBar.visibility = View.GONE
                noItemsFoundTV.visibility = View.VISIBLE
                return@launch
            }
            val productModelArrayList = ArrayList<ProductModel?>()
            for (cartModel in ordersModelArrayList) {
                productModelArrayList.add(productsRepository.getProductById(cartModel!!.productId))
            }
            val adapter = OrderGVAdapter(view.context, ordersModelArrayList, productModelArrayList)
            orderGV.adapter = adapter

            orderGV.visibility = View.VISIBLE
            loadingBar.visibility = View.GONE
            noItemsFoundTV.visibility = View.GONE
        }
    }
}