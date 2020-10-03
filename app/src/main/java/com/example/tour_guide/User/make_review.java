package com.example.tour_guide.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tour_guide.R;

public abstract class make_review extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView backBtn;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_make_review);

        backBtn = findViewById(R.id.back);

        backBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
              make_review.super.onBackPressed();
            }
        }
        );
    }
    public static abstract class OnClickListener implements View.OnClickListener {

    }

}
