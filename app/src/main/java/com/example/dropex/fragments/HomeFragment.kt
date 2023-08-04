package com.example.dropex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.dropex.R
import com.example.dropex.adpaters.HomeGVAdapter
import com.example.dropex.database.ProductsRepository
import com.example.dropex.models.ProductModel

class HomeFragment : Fragment() {
    private lateinit var homeGVAdapter: GridView
    private lateinit var loadingBar: ProgressBar
    private var productsRepository = ProductsRepository()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeGVAdapter = view.findViewById(R.id.homeGV)
        loadingBar = view.findViewById(R.id.progressBar)

        val productModelArrayList = ArrayList<ProductModel?>()
        homeGVAdapter.visibility = View.GONE
        loadingBar.visibility = View.VISIBLE
        productsRepository.getAllProducts().addOnSuccessListener {
            val productIds = ArrayList<String?>()
            for (product in it.children) {
                val productModel = ProductModel(
                        name = product.child("name").value.toString(),
                        price = product.child("price").value.toString().toInt(),
                        imgUrl = product.child("imgUrl").value.toString()
                )
                productIds.add(product.key.toString())
                productModelArrayList.add(productModel)
            }
            loadingBar.visibility = View.GONE
            val adapter = HomeGVAdapter(view.context, productIds, productModelArrayList)
            homeGVAdapter.adapter = adapter
            homeGVAdapter.visibility = View.VISIBLE
            return@addOnSuccessListener
        }
    }
}