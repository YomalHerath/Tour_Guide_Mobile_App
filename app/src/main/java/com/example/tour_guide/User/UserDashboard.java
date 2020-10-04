package com.example.tour_guide.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.tour_guide.R;
import com.example.tour_guide.UserEvents.User_View_All_Events;
import com.example.tour_guide.Userhotels.user_view_all_hotels;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static final float END_SCALE = 0.7f;

    //Drawer Menu
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView menuIcon;
    private LinearLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        navigationDrawer();

    }

    //View All Places
    public void viewPlaceCategories(View view) {
        startActivity(new Intent(getApplicationContext(), UserViewTravellingPlaces.class));
    }

    //View All Events
    public void viewEvents(View view) {
        startActivity(new Intent(getApplicationContext(), User_View_All_Events.class));
    }

    //view All Hotels
    public void viewHotels(View view) {
        startActivity(new Intent(getApplicationContext(), user_view_all_hotels.class));
    }

    //View Categories from Navigation View
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                break;
            case R.id.nav_place_categories:
                startActivity(new Intent(getApplicationContext(), UserViewTravellingPlaces.class));
                break;
            case R.id.nav_events:
                startActivity(new Intent(getApplicationContext(), User_View_All_Events.class));
                break;
            case R.id.nav_hotels:
                startActivity(new Intent(getApplicationContext(), user_view_all_hotels.class));
                break;
        }
        return true;
    }

    //Navigation Drawer Functions
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    //view reviewList
    public void viewReviewList(View view) {
        startActivity(new Intent(getApplicationContext(), review_list.class));
    }

    public void profile(View view) {
        startActivity(new Intent(getApplicationContext(), user_profile.class));
    }

}