
package com.example.tour_guide.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tour_guide.Admin.AdminDashboard;
import com.example.tour_guide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class  login extends AppCompatActivity {

    TextInputLayout username,pass;
    Button login;
    FirebaseAuth fAuth;

    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.loginbutton);

        fAuth = FirebaseAuth.getInstance();

        dbReference = FirebaseDatabase.getInstance().getReference().child("Users");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mail = username.getEditText().getText().toString().trim();
                final String password = pass.getEditText().getText().toString().trim();

                if (TextUtils.isEmpty(mail)){
                    username.setError("Email Required!");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    pass.setError("Password Required!");
                    return;
                }
                if (password.length() < 4){
                    pass.setError("Incorrect password! ");
                    return;
                }

//                if (username.getEditText().getText().toString().equals("admin") && pass.getEditText().getText().toString().equals("admin")){
//                    Toast.makeText(login.this, "ADMIN ACCESS DONE!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(),AdminDashboard.class));
//
//                }


                fAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(login.this, "Logged in Success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),UserDashboard.class));
                            finish();
                        }
                        else{
                            Toast.makeText(login.this, "invalid login!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    public void clickToSignup(View view) {
        startActivity(new Intent(getApplicationContext(), signup.class));
        finish();
    }
}