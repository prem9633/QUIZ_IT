package com.ayowainc.quizbox;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.ayowainc.quizbox.Category_Levels.History.Set1activity;
import com.ayowainc.quizbox.Category_Levels.History.Set2activity;
import com.ayowainc.quizbox.Category_Levels.History.Set3activity;
import com.ayowainc.quizbox.Category_Levels.Programming.Set11activity;
import com.ayowainc.quizbox.Category_Levels.Programming.Set22activity;
import com.ayowainc.quizbox.Category_Levels.Programming.Set33activity;
import com.ayowainc.quizbox.User.LoginActivity;
import com.ayowainc.quizbox.User.UserProfileActivity;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MenuHomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button navToggler_btn;
    LinearLayout linearLayout;
    Dialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); ///Enter into fullscreen mode

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navToggler_btn = findViewById(R.id.action_menu_presenter);
        linearLayout = findViewById(R.id.main_content);

        navigationDrawer();

        dialog = new Dialog(this, R.style.AnimateDialog);




    }





    //Dialog PopUp for Programming Level Popup Screen setup

    public void android_start(View view) {
        Button close_btn;
        Button beginner_btn;
        Button professional_btn;
        Button worldclass_btn;
        final ProgressBar progressBar;


        dialog.setContentView(R.layout.activity_custom_popup);
        close_btn = dialog.findViewById(R.id.close_lev);
        beginner_btn = dialog.findViewById(R.id.beginner);
        professional_btn = dialog.findViewById(R.id.professional);
        worldclass_btn = dialog.findViewById(R.id.world_class);
        progressBar = dialog.findViewById(R.id.pro_gress1);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        beginner_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
//////////////////////////On click action to MultimediaBeginnerActivity Activity
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(3000);
                Intent BG = new Intent(getApplicationContext(), Set11activity.class);
                startActivity(BG);

            }
        });
        professional_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//////////////////////////On click action to MultimediaProfessionalActivity Activity
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(3000);
                Intent PRO = new Intent(MenuHomeScreenActivity.this, Set22activity.class);
                startActivity(PRO);
            }
        });
        worldclass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//////////////////////////On click action to Programming World_class Activity
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(3000);
                Intent WC = new Intent(getApplicationContext(), Set33activity.class);
                startActivity(WC);

            }
        });

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //Dialog PopUp for History Level Popup Screen setup
    public void history_start(View view) {
        Button close_btn;
        Button beginner_btn;
        Button professional_btn;
        Button worldclass_btn;
        final ProgressBar progressBar;


        dialog.setContentView(R.layout.activity_custom_popup);
        close_btn = dialog.findViewById(R.id.close_lev);
        beginner_btn = dialog.findViewById(R.id.beginner);
        professional_btn = dialog.findViewById(R.id.professional);
        worldclass_btn = dialog.findViewById(R.id.world_class);
        progressBar = dialog.findViewById(R.id.pro_gress1);

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        beginner_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
//////////////////////////On click action to Set1activity Activity
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(3000);
                Intent BG = new Intent(getApplicationContext(), Set1activity.class);
                startActivity(BG);

            }
        });
        professional_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//////////////////////////On click action to Set2activity Activity
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(3000);
                Intent PRO = new Intent(MenuHomeScreenActivity.this, Set2activity.class);
                startActivity(PRO);
            }
        });
        worldclass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//////////////////////////On click action to History World_class Activity
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(3000);
                Intent WC = new Intent(getApplicationContext(), Set3activity.class);
                startActivity(WC);

            }
        });

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }






    private void navigationDrawer() {

        //Navigation Drawer

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        navToggler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.cat_heading));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                linearLayout.setScaleX(offsetScale);
                linearLayout.setScaleY(offsetScale);


                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = linearLayout.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                linearLayout.setTranslationX(xTranslation);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.user_profile) {

            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
            startActivity(intent);
            MenuHomeScreenActivity.super.onBackPressed();


        } else if (menuItem.getItemId() == R.id.logout) {
            //Logout
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        return true;
    }

}
