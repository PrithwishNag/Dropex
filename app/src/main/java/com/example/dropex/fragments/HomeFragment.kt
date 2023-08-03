package com.example.dropex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.dropex.R
import com.example.dropex.adpaters.HomeGVAdapter
import com.example.dropex.models.ProductModel

class HomeFragment : Fragment() {
    private lateinit var homeGVAdapter: GridView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeGVAdapter = view.findViewById(R.id.homeGV)
        val productModelArrayList = ArrayList<ProductModel?>()
        productModelArrayList.add(ProductModel(0, "DSA", "", 213, R.drawable.sample))
        productModelArrayList.add(ProductModel(0, "JAVA", "", 4242, R.drawable.sample))
        productModelArrayList.add(ProductModel(0, "C++", "", 3454, R.drawable.sample))
        productModelArrayList.add(ProductModel(0, "Python", "", 434, R.drawable.sample))
        productModelArrayList.add(ProductModel(0, "Javascript", "", 537, R.drawable.sample))
        productModelArrayList.add(ProductModel(0, "DSA", "", 686, R.drawable.sample))
        val adapter = HomeGVAdapter(view.context, productModelArrayList)
        homeGVAdapter.adapter = adapter
    }
}