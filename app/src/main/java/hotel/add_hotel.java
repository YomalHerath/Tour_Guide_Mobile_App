package  hotel;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tour_guide.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class add_hotel extends AppCompatActivity  {

    private static final int REQUEST_CODE_IMAGE = 101;
    private ImageView backButton, AddPlaceImage;
    private EditText AddHotelName, ProvinceName, HotelType, Phone, Email, HotelDesc;

    private Button choose, submit;
    private StorageTask storageTask;
    private ProgressDialog progressDialog;

    Uri imageUri;
    boolean IsImageAdded = false;

    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        backButton = findViewById(R.id.back_pressed);
        AddPlaceImage = findViewById(R.id.placeImg);
        AddHotelName = findViewById(R.id.hotel_name);
        ProvinceName = findViewById(R.id.add_province);
        HotelType = findViewById(R.id.add_hotel_type);
        Phone = findViewById(R.id.add_phone);
        Email = findViewById(R.id.add_Email);
        HotelDesc = findViewById(R.id.add_hotel_desc);
        choose = findViewById(R.id.choose_btn);
        submit = findViewById(R.id.add_travel_place_submit);
        progressDialog = new ProgressDialog(add_hotel.this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Hotels");
        storageReference = FirebaseStorage.getInstance().getReference().child("Hotels");

        //Back Button Click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_hotel.super.onBackPressed();
            }
        });

        //Chose Image File In Storage
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String HotelName = AddHotelName.getText().toString();
                final String Description = HotelDesc.getText().toString();
                final String Province = ProvinceName.getText().toString();
                final String Type = HotelType.getText().toString();
                final String HotelPhone = Phone.getText().toString();
                final String HotelEmail = Email.getText().toString();



                if (storageTask != null && storageTask.isInProgress()) {
                    Toast.makeText(add_hotel.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else if (IsImageAdded != false && HotelName != null && Description != null && Province != null) {
                    uploadfile(HotelName,Description,Province, Type,HotelPhone,HotelEmail);
                }
            }
        });

    }

    private void uploadfile(final String AddHotelName, final String HotelDesc, final String ProvinceName, final String HotelType, final String Phone, final String Email) {

        progressDialog.setTitle("Image is Uploading....");
        progressDialog.show();

        final String key = databaseReference.push().getKey();
        storageReference.child(key + ".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(key + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("HotelName", AddHotelName);
                        hashMap.put("Description", HotelDesc);
                        hashMap.put("Province", ProvinceName);
                        hashMap.put("HotelType", HotelType);
                        hashMap.put("Phone", Phone);
                        hashMap.put("Email", Email);
                        hashMap.put("ImageUrl", uri.toString());

                        databaseReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                                Toast.makeText(add_hotel.this, "Data Successfully Uploaded", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), add_hotel.class));
                            }
                        });
                    }
                });
            }
        });
    }

    //View Selecting Image From the Gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_IMAGE && data != null) {
            imageUri = data.getData();
            IsImageAdded = true;
            AddPlaceImage.setImageURI(imageUri);
        }
    }
}