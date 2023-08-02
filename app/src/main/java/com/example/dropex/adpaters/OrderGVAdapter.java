package com.example.dropex.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dropex.R;
import com.example.dropex.models.OrdersModel;

import java.util.ArrayList;

public class OrderGVAdapter extends ArrayAdapter<OrdersModel> {

    public OrderGVAdapter(@NonNull Context context, ArrayList<OrdersModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
        }

        OrdersModel ordersModel = getItem(position);
        TextView productTVName = listItemView.findViewById(R.id.productNameTV);
        ImageView productImageIV = listItemView.findViewById(R.id.productImageIV);
        TextView quantityTV = listItemView.findViewById(R.id.quantityTV);
        TextView sizeTV = listItemView.findViewById(R.id.sizeTV);
        TextView totalPriceTV = listItemView.findViewById(R.id.totalPriceTV);

        String sizeText = "Size: " + ordersModel.getSize().toString().toUpperCase();
        String quantityText = "Quantity: " + ordersModel.getQuantity();

        productTVName.setText(R.string.placeholder);
        productImageIV.setImageResource(R.drawable.sample);
        quantityTV.setText(quantityText);
        sizeTV.setText(sizeText);
        totalPriceTV.setText("$3232");
        return listItemView;
    }
}
