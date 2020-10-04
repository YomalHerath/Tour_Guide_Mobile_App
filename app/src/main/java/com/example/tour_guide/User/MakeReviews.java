
package com.example.tour_guide.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tour_guide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MakeReviews<name> extends AppCompatActivity {

    private EditText RName,RPlace,RMail,RDescription;
    Button RSubmit;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView backBtn;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_make_reviews);

        backBtn = findViewById(R.id.back);

        RName = findViewById(R.id.reviewname);
        RPlace = findViewById(R.id.reviewmPlace);
        RMail = findViewById(R.id.reviewmail);
        RDescription = findViewById(R.id.reviewdescription);
        RSubmit = findViewById(R.id.reviewsubmit);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Reviews");

        RSubmit.setOnClickListener(new make_review.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String rName = RName.getText().toString();
                final String rPlace = RPlace.getText().toString();
                final String rMail = RMail.getText().toString();
                final String rDescription = RDescription.getText().toString();

                HashMap<String,Object> map = new HashMap<>();

                map.put("R_Name",rName);
                map.put("R_Place",rPlace);
                map.put("R_Mail",rMail);
                map.put("R_Description",rDescription);


                String R_Name = RName.getText().toString();
                String R_Place = RPlace.getText().toString();
                String R_Mail = RMail.getText().toString();
                String R_Description = RDescription.getText().toString();

                if (TextUtils.isEmpty(R_Name)){
                    String val = RName.getText().toString();

                    if (val.isEmpty()){
                        RName.setError("Required field!");
                        return;
                    }else{
                        RName.setError(null);
                        return;
                    }
                }

                if (TextUtils.isEmpty(R_Place)){
                    String val = RPlace.getText().toString();

                    if (val.isEmpty()){
                        RPlace.setError("Required field!");
                        return;
                    }else{
                        RPlace.setError(null);
                        return;
                    }
                }

                if (TextUtils.isEmpty(R_Mail)){
                    String val = RMail.getText().toString();

                    if (val.isEmpty()){
                        RMail.setError("Required field!");
                        return;
                    }else{
                        RMail.setError(null);
                        return;
                    }
                }

                if (TextUtils.isEmpty(R_Description)){
                    String val = RDescription.getText().toString();

                    if (val.isEmpty()){
                        RDescription.setError("Required field!");
                        return;
                    }else{
                        RDescription.setError(null);
                        return;
                    }
                }

                final String key = databaseReference.push().getKey();

                databaseReference.child(key).child(R_Name)
                        .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("user_review","onComplete: ");
                        Toast.makeText(MakeReviews.this, "Your Review Successfully Added!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), review_list.class));
                        finish();
                    }
                }) .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("user_review","onFailure: "+e.toString());
                        Toast.makeText(MakeReviews.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }) ;
            }
        });

        backBtn.setOnClickListener(new make_review.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeReviews.super.onBackPressed();
            }
        }
        );
    }
}