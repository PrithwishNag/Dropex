package com.example.dropex.adpaters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dropex.R;
import com.example.dropex.models.ProductModel;
import com.example.dropex.screens.HomeScreen;
import com.example.dropex.screens.ProductDetailsScreen;
import com.example.dropex.screens.SplashScreen;

import java.util.ArrayList;

public class HomeGVAdapter extends ArrayAdapter<ProductModel> {

    public HomeGVAdapter(@NonNull Context context, ArrayList<ProductModel> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.card_product, parent, false);
        }

        listItemView.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ProductDetailsScreen.class);
            getContext().startActivity(intent);
        });

        ProductModel productModel = getItem(position);
        TextView productTVName = listItemView.findViewById(R.id.productNameTV);
        TextView productTVPrice = listItemView.findViewById(R.id.productPriceTV);
        ImageView productIVImage = listItemView.findViewById(R.id.productImageIV);

        productTVName.setText(productModel.getName());
        productTVPrice.setText("$" + productModel.getPrice());
        productIVImage.setImageResource(productModel.getImgId());
        return listItemView;
    }
}
