package com.example.tour_guide.User;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.tour_guide.HelperClasses.UserhelperClass;
import com.example.tour_guide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signup extends AppCompatActivity {

    Button signUp;
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;

    TextInputLayout firstName,lastName,Mobile,Mail,Password;
    TextView viewUserName,viewUserMail,viewUserMobile,viewUserPassword,ProName,ProMail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);


        signUp = findViewById(R.id.signupbutton);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.LastName);
        Mobile = findViewById(R.id.mobile);
        Mail = findViewById(R.id.email);
        Password = findViewById(R.id.password);


        viewUserName = findViewById(R.id.viewusername);
        viewUserMail = findViewById(R.id.viewmail);
        viewUserMobile = findViewById(R.id.viewmobile);
        viewUserPassword = findViewById(R.id.viewpass);

        ProName = findViewById(R.id.profname);
        ProMail = findViewById(R.id.profmail);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),login.class));
            finish();
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HashMap<String,Object> map = new HashMap<>();


                map.put("F_Name",firstName.getEditText().getText().toString());
                map.put("L_Name",lastName.getEditText().getText().toString());
                map.put("Mobile",Mobile.getEditText().getText().toString());
                map.put("Mail",Mail.getEditText().getText().toString().trim());
                map.put("Password",Password.getEditText().getText().toString().trim());

                String F_name = firstName.getEditText().getText().toString();
                String L_name = lastName.getEditText().getText().toString();
                String mobile = Mobile.getEditText().getText().toString();
                String mail = Mail.getEditText().getText().toString().trim();
                String password = Password.getEditText().getText().toString().trim();



                if (TextUtils.isEmpty(F_name)){
                    String val = firstName.getEditText().getText().toString();

                    if (val.isEmpty()){
                        firstName.setError("Required field!");
                        return;
                    }else{
                        firstName.setError(null);
                        firstName.setErrorEnabled(false);
                        return;
                    }
                }

                if (TextUtils.isEmpty(L_name)){
                    String val = lastName.getEditText().getText().toString();

                    if (val.isEmpty()){
                        lastName.setError("Required field!");
                        return;
                    }else{
                        lastName.setError(null);
                        lastName.setErrorEnabled(false);
                        return;
                    }

                }

                if (TextUtils.isEmpty(mobile)){
                    String val = Mobile.getEditText().getText().toString();

                    if (val.isEmpty()){
                        Mobile.setError("Required field!");
                        return;
                    }else{
                        Mobile.setError(null);
                        Mobile.setErrorEnabled(false);
                        return;
                    }

                }

                if (TextUtils.isEmpty(mail)){
                    String val = Mail.getEditText().getText().toString();
                    String Check = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";

                    if (val.isEmpty()) {
                        Mail.setError("Required field!");
                        return;
                    } else if (!val.matches(Check)) {
                        Mail.setError("Invalid email address!");
                        return;
                    } else {
                        Mail.setError(null);
                        Mail.setErrorEnabled(false);
                        return;
                    }
                }

                if (TextUtils.isEmpty(password)){
                    String val = Password.getEditText().getText().toString();

                    if (val.isEmpty()){
                        Password.setError("Required field!");
                        return;

                    }else{
                        Password.setError(null);
                        Password.setErrorEnabled(false);
                        return;
                    }
                }

                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(F_name);
                databaseReference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("user_review","onComplete: ");
                    }
                });


                ////////////////////////////////

                fAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(signup.this,"User is created!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),login.class));

                        }else{
                            Toast.makeText(signup.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }


    public void clickToLogin(View view) {
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }

}




