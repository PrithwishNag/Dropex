package com.example.dropex.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dropex.R;
import com.example.dropex.models.CartModel;

import java.util.ArrayList;

public class CartGVAdapter extends ArrayAdapter<CartModel> {

    public CartGVAdapter(@NonNull Context context, ArrayList<CartModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
        }

        CartModel cartModel = getItem(position);
        TextView productTVName = listItemView.findViewById(R.id.productNameTV);
        ImageView productImageIV = listItemView.findViewById(R.id.productImageIV);
        listItemView.findViewById(R.id.quantitySetter); // To load quantitySetter
        TextView quantityTV = listItemView.findViewById(R.id.quantityTV);
        TextView sizeTV = listItemView.findViewById(R.id.sizeTV);
        TextView totalPriceTV = listItemView.findViewById(R.id.totalPriceTV);

        String sizeText = "Size: " + cartModel.getSize().toString().toUpperCase();

        productTVName.setText(R.string.placeholder);
        productImageIV.setImageResource(R.drawable.sample);
        quantityTV.setText(String.valueOf(cartModel.getQuantity()));
        sizeTV.setText(sizeText);
        totalPriceTV.setText("$3232");
        return listItemView;
    }
}