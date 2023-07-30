package com.example.dropex.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.dropex.R;
import com.example.dropex.adpaters.ProductGVAdapter;
import com.example.dropex.models.ProductModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    GridView productGVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        productGVAdapter = view.findViewById(R.id.productGV);
        ArrayList<ProductModel> courseModelArrayList = new ArrayList<>();

        courseModelArrayList.add(new ProductModel("DSA", "", 213, R.drawable.sample));
        courseModelArrayList.add(new ProductModel("JAVA", "", 4242, R.drawable.sample));
        courseModelArrayList.add(new ProductModel("C++", "", 3454, R.drawable.sample));
        courseModelArrayList.add(new ProductModel("Python", "", 434, R.drawable.sample));
        courseModelArrayList.add(new ProductModel("Javascript", "", 537,R.drawable.sample));
        courseModelArrayList.add(new ProductModel("DSA", "", 686, R.drawable.sample));

        ProductGVAdapter adapter = new ProductGVAdapter(view.getContext(), courseModelArrayList);
        productGVAdapter.setAdapter(adapter);
    }
}