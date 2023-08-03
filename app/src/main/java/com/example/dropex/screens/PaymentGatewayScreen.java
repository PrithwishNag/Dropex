package com.example.dropex.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dropex.R;
import com.example.dropex.adpaters.OrderGVAdapter;
import com.example.dropex.enums.Size;
import com.example.dropex.models.OrdersModel;
import com.example.dropex.customComponents.WrappingGridView;

import java.util.ArrayList;

public class PaymentGatewayScreen extends AppCompatActivity {

    WrappingGridView orderGVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway_screen);

        orderGVAdapter = findViewById(R.id.summaryItemsGV);
        ArrayList<OrdersModel> ordersModelArrayList = new ArrayList<>();

        ordersModelArrayList.add(new OrdersModel(1, 2, Size.l, 1, 2));
        ordersModelArrayList.add(new OrdersModel(1, 1, Size.m, 1, 3));
        ordersModelArrayList.add(new OrdersModel(1, 1, Size.xxl, 1, 4));

        OrderGVAdapter adapter = new OrderGVAdapter(this, ordersModelArrayList);
        orderGVAdapter.setAdapter(adapter);
    }
}