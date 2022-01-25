package com.ayowainc.quizbox.User;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ayowainc.quizbox.MenuHomeScreenActivity;
import com.ayowainc.quizbox.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button navToggler_btn, back_btn;
    LinearLayout linearLayout;
    TextInputLayout textInputLayout_Email, textInputLayout_Password, textInputLayout_UserName, textInputLayout_FullName;
    FirebaseAuth firebaseAuth;
    private boolean mIsBound = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); ///Eneter into fullscreen mode
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navToggler_btn = findViewById(R.id.action_menu_presenter);
        linearLayout = findViewById(R.id.main_content);
        textInputLayout_Email = findViewById(R.id.EMail);
        textInputLayout_Password = findViewById(R.id.PassWOrd);
        textInputLayout_UserName = findViewById(R.id.USername);
        textInputLayout_FullName = findViewById(R.id.fullName);
        back_btn = findViewById(R.id.prof_back_btn);


        //Init Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("The QuizBoxers");

        assert user != null;
        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String fullname = "" + ds.child("fullname").getValue();
                    String username = "" + ds.child("username").getValue();
                    String password = "" + ds.child("password").getValue();
                    String email = "" + ds.child("email").getValue();

                    //set data
                    Objects.requireNonNull(textInputLayout_FullName.getEditText()).setText(fullname);
                    Objects.requireNonNull(textInputLayout_UserName.getEditText()).setText(username);
                    Objects.requireNonNull(textInputLayout_Password.getEditText()).setText(password);
                    Objects.requireNonNull(textInputLayout_Email.getEditText()).setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        navigationDrawer();
    }


    private void navigationDrawer() {

        //Navigation Drawer

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);


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

        if (menuItem.getItemId() == R.id.home) {
            Intent home = new Intent(getApplicationContext(), MenuHomeScreenActivity.class);
            startActivity(home);
            UserProfileActivity.super.onBackPressed();


        } else if (menuItem.getItemId() == R.id.logout) {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            super.onBackPressed();
        }
        return true;
    }
}


