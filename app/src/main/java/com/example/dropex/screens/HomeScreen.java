package com.example.dropex.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dropex.R;
import com.example.dropex.fragments.CartFragment;
import com.example.dropex.fragments.HomeFragment;
import com.example.dropex.fragments.OrdersFragment;
import com.example.dropex.fragments.SideBarFragment;

public class HomeScreen extends AppCompatActivity {

    ImageView home;
    ImageView cart;
    ImageView orders;
    ImageView wishlist;

    CardView homeCard;
    CardView wishlistCard;
    CardView cartCard;
    CardView ordersCard;

    int sideBarCounter = 0;
    Fragment sidebarFragment;

    private void goTo(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void showSideBar(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void hideSideBar(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    private void onSelect(int idx) {
        ColorStateList normalColorStateList = ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.mild_white));
        ColorStateList selectedColorStateList = ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.yellow_white));

        homeCard.setBackgroundTintList(normalColorStateList);
        wishlistCard.setBackgroundTintList(normalColorStateList);
        cartCard.setBackgroundTintList(normalColorStateList);
        ordersCard.setBackgroundTintList(normalColorStateList);

        switch (idx) {
            case 0:
                homeCard.setBackgroundTintList(selectedColorStateList);
                break;
            case 1:
                wishlistCard.setBackgroundTintList(selectedColorStateList);
                break;
            case 2:
                cartCard.setBackgroundTintList(selectedColorStateList);
                break;
            case 3:
                ordersCard.setBackgroundTintList(selectedColorStateList);
                break;
        }
    }

    private void initSideBar() {
        sidebarFragment = new SideBarFragment();
        View appBar = findViewById(R.id.appBar);
        ImageView appBarMore = appBar.findViewById(R.id.appbarMore);
        appBarMore.setOnClickListener(v -> {
            if (sideBarCounter == 0) {
                showSideBar(sidebarFragment);
                sideBarCounter = 1;
            } else {
                hideSideBar(sidebarFragment);
                sideBarCounter = 0;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        home = findViewById(R.id.homeBtn);
        wishlist = findViewById(R.id.wishlistBtn);
        cart = findViewById(R.id.cartBtn);
        orders = findViewById(R.id.orderBtn);
        TextView appbarTitle = findViewById(R.id.appbarTitle);

        homeCard = findViewById(R.id.homeBtnCard);
        wishlistCard = findViewById(R.id.wishlistBtnCard);
        cartCard = findViewById(R.id.cartBtnCard);
        ordersCard = findViewById(R.id.orderBtnCard);

        initSideBar();

        onSelect(0);
        goTo(new HomeFragment());
        appbarTitle.setText(R.string.home);

        home.setOnClickListener(view -> {
            onSelect(0);
            goTo(new HomeFragment());
            appbarTitle.setText(R.string.home);
        });

        wishlist.setOnClickListener(view -> {
            onSelect(1);
            goTo(new HomeFragment());
            appbarTitle.setText(R.string.wishlist);
        });

        cart.setOnClickListener(view -> {
            onSelect(2);
            goTo(new CartFragment());
            appbarTitle.setText(R.string.cart);
        });

        orders.setOnClickListener(view -> {
            onSelect(3);
            goTo(new OrdersFragment());
            appbarTitle.setText(R.string.orders);
        });
    }
}