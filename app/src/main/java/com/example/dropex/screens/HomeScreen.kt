package com.example.dropex.screens

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.dropex.R
import com.example.dropex.constants.Constants
import com.example.dropex.enums.HomeScreenTabs
import com.example.dropex.fragments.CartFragment
import com.example.dropex.fragments.HomeFragment
import com.example.dropex.fragments.OrdersFragment
import com.example.dropex.fragments.SideBarFragment
import com.example.dropex.fragments.WishlistFragment

class HomeScreen : AppCompatActivity() {
    private lateinit var home: ImageView
    private lateinit var cart: ImageView
    private lateinit var orders: ImageView
    private lateinit var wishlist: ImageView
    private lateinit var homeCard: CardView
    private lateinit var wishlistCard: CardView
    private lateinit var cartCard: CardView
    private lateinit var ordersCard: CardView
    private lateinit var appbarTitle: TextView
    private var sideBarCounter = 0
    private lateinit var sidebarFragment: Fragment

    private fun goTo(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun showSideBar(show: Int) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (show == 1) {
            fragmentTransaction.add(R.id.frameLayout, sidebarFragment)
        } else {
            fragmentTransaction.remove(sidebarFragment)
        }
        fragmentTransaction.commit()
    }

    private fun onSelect(idx: Int) {
        val normalColorStateList = ColorStateList.valueOf(
                ContextCompat.getColor(
                        applicationContext, R.color.mild_white
                )
        )
        val selectedColorStateList = ColorStateList.valueOf(
                ContextCompat.getColor(
                        applicationContext, R.color.yellow_white
                )
        )
        homeCard.backgroundTintList = normalColorStateList
        wishlistCard.backgroundTintList = normalColorStateList
        cartCard.backgroundTintList = normalColorStateList
        ordersCard.backgroundTintList = normalColorStateList
        when (idx) {
            0 -> homeCard.backgroundTintList = selectedColorStateList
            1 -> wishlistCard.backgroundTintList = selectedColorStateList
            2 -> cartCard.backgroundTintList = selectedColorStateList
            3 -> ordersCard.backgroundTintList = selectedColorStateList
        }
    }

    private fun initSideBar() {
        val appBar = findViewById<View>(R.id.appBar)
        val appBarMore = appBar.findViewById<ImageView>(R.id.appbarMore)
        appBarMore.setOnClickListener {
            sideBarCounter = if (sideBarCounter == 0) {
                showSideBar(1)
                1
            } else {
                showSideBar(0)
                0
            }
        }
    }

    private fun initialise() {
        home = findViewById(R.id.homeBtn)
        wishlist = findViewById(R.id.wishlistBtn)
        cart = findViewById(R.id.cartBtn)
        orders = findViewById(R.id.orderBtn)
        appbarTitle = findViewById(R.id.appbarTitle)
        homeCard = findViewById(R.id.homeBtnCard)
        wishlistCard = findViewById(R.id.wishlistBtnCard)
        cartCard = findViewById(R.id.cartBtnCard)
        ordersCard = findViewById(R.id.orderBtnCard)
        sidebarFragment = SideBarFragment()
    }

    private fun initNavigation() {
        val extras = intent.extras
        if (extras != null) {
            when (extras.getString(Constants.HOME_SCREEN_TAB_EXTRA)) {
                HomeScreenTabs.home.name -> {
                    onSelect(0)
                    goTo(HomeFragment())
                    appbarTitle.setText(R.string.home)
                }

                HomeScreenTabs.wishlist.name -> {
                    onSelect(1)
                    goTo(WishlistFragment())
                    appbarTitle.setText(R.string.wishlist)
                }

                HomeScreenTabs.cart.name -> {
                    onSelect(2)
                    goTo(CartFragment())
                    appbarTitle.setText(R.string.cart)
                }

                HomeScreenTabs.orders.name -> {
                    onSelect(3)
                    goTo(OrdersFragment())
                    appbarTitle.setText(R.string.orders)
                }
            }
        } else {
            onSelect(0)
            goTo(HomeFragment())
            appbarTitle.setText(R.string.home)
        }

        home.setOnClickListener {
            onSelect(0)
            goTo(HomeFragment())
            appbarTitle.setText(R.string.home)
        }
        wishlist.setOnClickListener {
            onSelect(1)
            goTo(WishlistFragment())
            appbarTitle.setText(R.string.wishlist)
        }
        cart.setOnClickListener {
            onSelect(2)
            goTo(CartFragment())
            appbarTitle.setText(R.string.cart)
        }
        orders.setOnClickListener {
            onSelect(3)
            goTo(OrdersFragment())
            appbarTitle.setText(R.string.orders)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        initialise()
        initSideBar()
        initNavigation()
    }
}