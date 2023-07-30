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
import com.example.dropex.models.ProductModel;

import java.util.ArrayList;

public class ProductGVAdapter extends ArrayAdapter<ProductModel> {

    public ProductGVAdapter(@NonNull Context context, ArrayList<ProductModel> courseModelArrayList) {
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

        ProductModel productModel = getItem(position);
        TextView productTVName = listItemView.findViewById(R.id.productTVName);
        TextView productTVPrice = listItemView.findViewById(R.id.productTVPrice);
        ImageView productIVImage = listItemView.findViewById(R.id.productIVImage);

        productTVName.setText(productModel.getName());
        productTVPrice.setText("$" + productModel.getPrice());
        productIVImage.setImageResource(productModel.getImgId());
        return listItemView;
    }
}
