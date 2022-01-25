package com.ayowainc.quizbox.Category_Levels.History;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ayowainc.quizbox.MenuHomeScreenActivity;
import com.ayowainc.quizbox.R;
import com.ayowainc.quizbox.User.LoginActivity;
import com.ayowainc.quizbox.User.UserProfileActivity;
import com.ayowainc.quizbox.questionsModelClass;

import com.google.android.gms.ads.InterstitialAd;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Set1activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button navToggler,  Next_btn;
    LinearLayout linearLayout, linearLayout1;
    TextView txtQuestions, txtnumberIndicator;
    Dialog result;
    private int count = 0;
    private int position = 0;
    private List<questionsModelClass> list;
    private int score = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); ///Eneter into fullscreen mode
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_questions_view);






        //All Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navToggler = findViewById(R.id.action_menu_presenter);
        linearLayout = findViewById(R.id.main_content);
        linearLayout1 = findViewById(R.id.options_layout);
        txtQuestions = findViewById(R.id.question_view);
        txtnumberIndicator = findViewById(R.id.no_of_questions_view);

        Next_btn = findViewById(R.id.next_btn);




        result = new Dialog(this, R.style.AnimateDialog);

        list = new ArrayList<>();
        list.add(new questionsModelClass("In which month of 1914 did the First World War begin?", "August", "May", "June", "March", "August"));
        list.add(new questionsModelClass("What were people told to “keep burning” in the hit song of 1914?", "Candles", "Home ﬁres", "Hair", "Fire Wood", "Home ﬁres"));
        list.add(new questionsModelClass("Which new weapon was introduced in battle in 1916?", "Tank", "Machine Guns", "Grenades", "C4", "Tank"));
        list.add(new questionsModelClass("What was the occupation of Edith Cavell, who was shot by the Germans on a spying charge?", "Doctor", "Nurse", "Soldier", "None of these", "Nurse"));
        list.add(new questionsModelClass("What was the 1914—18 war known as until 1939?", "The great war", "somme battle", "First world war", "None of these", "The great war"));
        list.add(new questionsModelClass("What was the nationality of dancer Mata Hari, shot as a spy?", "French", "German", "English", "Dutch", "Dutch"));
        list.add(new questionsModelClass("Who became Prime Minister of Britain in 1916?", "Lloyd Daniels", "Lloyd Jackson", "Lloyd Brown", "Lloyd George", "Lloyd George"));
        list.add(new questionsModelClass("What did George V ban in his household to encourage others to do the same and help the war effort?", "Cigarrete", "Candles", "Alcohol", "Gun Powder", "Alcohol"));
        list.add(new questionsModelClass("How did Lord Kitchener die?", "Lost at sea", "Snake Bite", "Poisoned", "Shot", "Lost at sea"));
        list.add(new questionsModelClass("At which battle in 1916 was there said to be a million fatalities?", "Somme Battle", "The Great War", "First World War", "None of these", "Somme Battle"));


        for (int i = 0; i < 4; i++) {
            linearLayout1.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAns((Button) v);
                }
            });
        }

        txtnumberIndicator.setText(position + 1 + "/" + list.size());

        playAnim(txtQuestions, 0, list.get(position).getQuestions());

        Next_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                Next_btn.setEnabled(false);
                Next_btn.setAlpha(0.7f);
                enableOptions(true);
                position++;


                if (position == list.size()) {

                    //Score Activities
                    if (score <= 2) {

                        Button try_again;
                        result.setContentView(R.layout.activity_fail_20_layout);
                        try_again = result.findViewById(R.id.try_again_btn);


                        try_again.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent BG = new Intent(getApplicationContext(), Set1activity.class); //If User get 20% let him or her play again
                                startActivity(BG);
                            }
                        });


                        Objects.requireNonNull(result.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        result.show();


                    } else if (score <= 4) {

                        Button try_again;
                        result.setContentView(R.layout.activity_pass_50_layout);
                        try_again = result.findViewById(R.id.try_again_btn);


                        try_again.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent BG = new Intent(getApplicationContext(), Set1activity.class); ///If User get 50% let him or her play again
                                startActivity(BG);
                            }
                        });


                        Objects.requireNonNull(result.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        result.show();


                    } else if (score <= 9) {

                        Button promote_btn;
                        result.setContentView(R.layout.activity_pass_70_layout);
                        promote_btn = result.findViewById(R.id.nl_btn);


                        promote_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent BG = new Intent(getApplicationContext(), Set2activity.class); ///If User get 70% let him to next level
                                startActivity(BG);
                            }
                        });



                        Objects.requireNonNull(result.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        result.show();

                    } else if (score == 10) {

                        Button promote_btn;
                        result.setContentView(R.layout.activity_pass_100_layout);
                        promote_btn = result.findViewById(R.id.nl_btn);

                        promote_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent BG = new Intent(getApplicationContext(), Set2activity.class); ///If User get 100% promote him or her to next level
                                startActivity(BG);
                            }
                        });



                        Objects.requireNonNull(result.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        result.show();

                    }
                    return;
                }

                count = 0;
                playAnim(txtQuestions, 0, list.get(position).getQuestions());

            }
        });

        navigationDrawer();


    }

    private void playAnim(final View view, final int value, final String data) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0) {
                        option = list.get(position).getOptionA();
                    } else if (count == 1) {
                        option = list.get(position).getOptionB();
                    } else if (count == 2) {
                        option = list.get(position).getOptionC();
                    } else if (count == 3) {
                        option = list.get(position).getOptionD();
                    }
                    playAnim(linearLayout1.getChildAt(count), 0, option);
                    count++;
                }
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAnimationEnd(Animator animation) {

                if (value == 0) {

                    try {
                        ((TextView) view).setText(data);
                        txtnumberIndicator.setText(position + 1 + "/" + list.size());
                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);


                    playAnim(view, 1, data);

                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void checkAns(Button selectedOptions) {
        enableOptions(false);
        Next_btn.setEnabled(true);
        Next_btn.setAlpha(1);
        if (selectedOptions.getText().toString().equals(list.get(position).getCorrectAnswer())) {
            //correct Answer
            score++;

            selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#14E39A")));

        } else {
            //wrong Answer
            selectedOptions.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF2B55")));
            Button correctOption = linearLayout1.findViewWithTag(list.get(position).getCorrectAnswer());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#14E39A")));


        }
    }

    private void enableOptions(boolean enable) {
        for (int i = 0; i < 4; i++) {
            linearLayout1.getChildAt(i).setEnabled(enable);
            if (enable) {
                linearLayout1.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2133A0")));
            }
        }
    }


    private void navigationDrawer() {

        //Navigation Drawer

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        navToggler.setOnClickListener(new View.OnClickListener() {
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

                final float diffScaledOffset = slideOffset*(1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                linearLayout.setScaleX(offsetScale);
                linearLayout.setScaleY(offsetScale);


                final float xOffset = drawerView.getWidth()*slideOffset;
                final float xOffsetDiff = linearLayout.getWidth()*diffScaledOffset/2;
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
            Set1activity.super.onBackPressed();


        } else if (menuItem.getItemId() == R.id.user_profile) {
            Intent user_profile = new Intent(getApplicationContext(), UserProfileActivity.class);
            startActivity(user_profile);
        } else if (menuItem.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        return true;
    }


}

