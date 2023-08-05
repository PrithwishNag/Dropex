package com.example.dropex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dropex.R
import com.example.dropex.adpaters.HomeGVAdapter
import com.example.dropex.database.ProductsRepository
import com.example.dropex.database.WishlistRepository
import com.example.dropex.models.ProductModel
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {
    private lateinit var homeGVAdapter: GridView
    private lateinit var loadingBar: ProgressBar
    private var productsRepository = ProductsRepository()
    private var wishlistRepository = WishlistRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeGVAdapter = view.findViewById(R.id.homeGV)
        loadingBar = view.findViewById(R.id.progressBar)

        homeGVAdapter.visibility = View.GONE
        loadingBar.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            val productIds = ArrayList<String>()
            val productModelArrayList = ArrayList<ProductModel?>()
            val wishlistModelList = wishlistRepository.getWishlistItems()
            for (wishlistItem in wishlistModelList) {
                val productId = wishlistItem!!.productId
                productIds.add(productId)
                productModelArrayList.add(productsRepository.getProductById(productId))
            }

            val adapter = HomeGVAdapter(view.context, productIds, productModelArrayList)
            homeGVAdapter.adapter = adapter

            loadingBar.visibility = View.GONE
            homeGVAdapter.visibility = View.VISIBLE
        }
    }
}