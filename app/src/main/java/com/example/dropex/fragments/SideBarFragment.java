package com.example.dropex.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dropex.R;
import com.example.dropex.screens.LoginScreen;
import com.google.firebase.auth.FirebaseAuth;

public class SideBarFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_side_bar, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout sidebarMain = view.findViewById(R.id.sidebarMain);
        Animation slideAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.sidebar_slide);
        sidebarMain.startAnimation(slideAnimation);

        TextView logoutBtn = view.findViewById(R.id.logoutBtnTV);
        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();

            Toast.makeText(getContext(), "Signed out", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), LoginScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });
    }
}