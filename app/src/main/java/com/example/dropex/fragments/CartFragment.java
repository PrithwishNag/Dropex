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
import com.example.dropex.adpaters.CartGVAdapter;
import com.example.dropex.adpaters.HomeGVAdapter;
import com.example.dropex.enums.Size;
import com.example.dropex.models.CartModel;
import com.example.dropex.models.ProductModel;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    GridView cartGVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cartGVAdapter = view.findViewById(R.id.cartGV);
        ArrayList<CartModel> cartModelArrayList = new ArrayList<>();

        cartModelArrayList.add(new CartModel(1, 2, Size.l, 1, 2));
        cartModelArrayList.add(new CartModel(1, 1, Size.m, 1, 3));
        cartModelArrayList.add(new CartModel(1, 1, Size.xxl, 1, 4));

        CartGVAdapter adapter = new CartGVAdapter(view.getContext(), cartModelArrayList);
        cartGVAdapter.setAdapter(adapter);
    }
}