package com.example.dropex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dropex.R
import com.example.dropex.adpaters.HomeGVAdapter
import com.example.dropex.database.ProductsRepository
import com.example.dropex.models.ProductModel
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeFragment : Fragment() {
    private lateinit var homeGVAdapter: GridView
    private lateinit var loadingBar: ProgressBar
    private lateinit var searchET: EditText
    private lateinit var searchBtn: CardView
    private var productsRepository = ProductsRepository()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun initSearch() {
        searchBtn.setOnClickListener {
            val searchKey = searchET.text.toString()
            viewLifecycleOwner.lifecycleScope.launch {
                val productsMap = productsRepository.getAllProducts()
                val productIdsInitial = productsMap.keys.toList()
                val productModelArrayListInitial = productsMap.values.toList()
                val productIds = ArrayList<String>()
                val productModelArrayList = ArrayList<ProductModel?>()
                for (i in 0 until productsMap.size) {
                    if (productModelArrayListInitial[i]!!.name.contains(searchKey, true)) {
                        productIds.add(productIdsInitial[i])
                        productModelArrayList.add(productModelArrayListInitial[i])
                    }
                }
                val adapter = HomeGVAdapter(requireContext(), productIds, productModelArrayList)
                homeGVAdapter.adapter = adapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeGVAdapter = view.findViewById(R.id.homeGV)
        loadingBar = view.findViewById(R.id.progressBar)
        searchET = view.findViewById(R.id.searchET)
        searchBtn = view.findViewById(R.id.searchBtn)

        initSearch()

        viewLifecycleOwner.lifecycleScope.launch {
            val productsMap: Map<String, ProductModel?> = productsRepository.getAllProducts()
            val productIds = productsMap.keys.toList()
            val productModelArrayList = productsMap.values.toList()
            val adapter = HomeGVAdapter(view.context, productIds, productModelArrayList)
            homeGVAdapter.adapter = adapter
            loadingBar.visibility = View.GONE
            homeGVAdapter.visibility = View.VISIBLE
        }
    }
}