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
import com.example.dropex.adpaters.OrderGVAdapter;
import com.example.dropex.enums.Size;
import com.example.dropex.models.CartModel;
import com.example.dropex.models.OrdersModel;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    GridView orderGVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        orderGVAdapter = view.findViewById(R.id.ordersGV);
        ArrayList<OrdersModel> ordersModelArrayList = new ArrayList<>();

        ordersModelArrayList.add(new OrdersModel(1, 2, Size.l, 1, 2));
        ordersModelArrayList.add(new OrdersModel(1, 1, Size.m, 1, 3));
        ordersModelArrayList.add(new OrdersModel(1, 1, Size.xxl, 1, 4));

        OrderGVAdapter adapter = new OrderGVAdapter(view.getContext(), ordersModelArrayList);
        orderGVAdapter.setAdapter(adapter);
    }
}