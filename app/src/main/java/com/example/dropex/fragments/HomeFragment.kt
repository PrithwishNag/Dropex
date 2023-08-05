package com.example.dropex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dropex.R
import com.example.dropex.adpaters.HomeGVAdapter
import com.example.dropex.database.ProductsRepository
import com.example.dropex.models.ProductModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var homeGVAdapter: GridView
    private lateinit var loadingBar: ProgressBar
    private lateinit var searchET: EditText
    private lateinit var searchBtn: ImageView
    private var productsRepository = ProductsRepository()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    fun initSearch() {
        searchBtn.setOnClickListener {
            val searchKey = searchET.text.toString()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeGVAdapter = view.findViewById(R.id.homeGV)
        loadingBar = view.findViewById(R.id.progressBar)
        searchET = view.findViewById(R.id.searchET)
        searchBtn = view.findViewById(R.id.searchBtn)

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