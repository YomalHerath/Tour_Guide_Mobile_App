package com.example.tour_guide.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tour_guide.User.MakeReviews;
import com.example.tour_guide.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class user_profile extends AppCompatActivity {


    TextView viewUserName,viewUserMail,viewUserMobile,viewUserPassword,ProName,ProMail;
    //private String UserId;

    DatabaseReference reference,DbReference;
    //FirebaseDatabase fRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView backBtn;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        viewUserName = findViewById(R.id.viewusername);
        viewUserMail = findViewById(R.id.viewmail);
        viewUserMobile = findViewById(R.id.viewmobile);
        viewUserPassword = findViewById(R.id.viewpass);

        ProName = findViewById(R.id.profname);
        ProMail = findViewById(R.id.profmail);

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        backBtn = findViewById(R.id.imageView2);

        backBtn.setOnClickListener(new make_review.OnClickListener() {
            @Override
            public void onClick(View view) {
               user_profile.super.onBackPressed();
             }
        }
      );
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), login.class));
        finish();
    }

    public void makeReviews(View view) {
        startActivity(new Intent(getApplicationContext(), MakeReviews.class));

    }

}