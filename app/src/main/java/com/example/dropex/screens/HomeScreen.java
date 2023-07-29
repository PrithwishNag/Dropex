package com.example.dropex.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dropex.R;
import com.example.dropex.fragments.HomeFragment;

public class HomeScreen extends AppCompatActivity {

    LinearLayout home;
    LinearLayout cart;
    LinearLayout orders;
    LinearLayout wishlist;

    TextView homeTv;
    TextView wishlistTv;
    TextView cartTv;
    TextView ordersTv;

    private void goTo(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void onSelect(int idx) {
        homeTv.setTypeface(null, Typeface.NORMAL);
        wishlistTv.setTypeface(null, Typeface.NORMAL);
        cartTv.setTypeface(null, Typeface.NORMAL);
        ordersTv.setTypeface(null, Typeface.NORMAL);

        switch(idx) {
            case 0: homeTv.setTypeface(homeTv.getTypeface(), Typeface.BOLD); break;
            case 1: wishlistTv.setTypeface(wishlistTv.getTypeface(), Typeface.BOLD); break;
            case 2: cartTv.setTypeface(cartTv.getTypeface(), Typeface.BOLD);break;
            case 3: ordersTv.setTypeface(ordersTv.getTypeface(), Typeface.BOLD); break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        home = findViewById(R.id.homeBtn);
        cart = findViewById(R.id.cartBtn);
        orders = findViewById(R.id.orderBtn);
        wishlist = findViewById(R.id.wishlistBtn);

        homeTv = findViewById(R.id.homeText);
        wishlistTv = findViewById(R.id.wishlistText);
        cartTv = findViewById(R.id.cartText);
        ordersTv = findViewById(R.id.ordersText);

        onSelect(0);
        goTo(new HomeFragment());

        home.setOnClickListener(view -> {
            onSelect(0);
            goTo(new HomeFragment());
        });

        wishlist.setOnClickListener(view -> {
            onSelect(1);
            goTo(new HomeFragment());
        });

        cart.setOnClickListener(view -> {
            onSelect(2);
            goTo(new HomeFragment());
        });

        orders.setOnClickListener(view -> {
            onSelect(3);
            goTo(new HomeFragment());
        });
    }
}