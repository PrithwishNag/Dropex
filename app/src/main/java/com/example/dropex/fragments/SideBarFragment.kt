package com.example.dropex.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dropex.R
import com.example.dropex.screens.LoginScreen
import com.google.firebase.auth.FirebaseAuth

class SideBarFragment : Fragment() {
    private lateinit var view: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_side_bar, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sidebarMain = view.findViewById<LinearLayout>(R.id.sidebarMain)
        val slideAnimation = AnimationUtils.loadAnimation(context, R.anim.sidebar_slide)
        sidebarMain.startAnimation(slideAnimation)
        val logoutBtn = view.findViewById<TextView>(R.id.logoutBtnTV)
        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(context, "Signed out", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, LoginScreen::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
        }
    }
}