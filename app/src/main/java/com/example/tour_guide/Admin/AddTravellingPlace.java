package com.example.tour_guide.Admin;

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

public class AddTravellingPlace extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE = 101;
    private ImageView backButton, AddPlaceImage;
    private EditText AddPlaceName, AddProvinceName, AddDesc;

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
        setContentView(R.layout.activity_add_travelling_place);

        backButton = findViewById(R.id.back_pressed);
        AddPlaceImage = findViewById(R.id.placeImg);
        AddPlaceName = findViewById(R.id.add_place_name);
        AddProvinceName = findViewById(R.id.add_province);
        AddDesc = findViewById(R.id.add_place_desc);
        choose = findViewById(R.id.choose_btn);
        submit = findViewById(R.id.add_travel_place_submit);
        progressDialog = new ProgressDialog(AddTravellingPlace.this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("TravelPlaces");
        storageReference = FirebaseStorage.getInstance().getReference().child("TravelPlaces");

        //Back Button Click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTravellingPlace.super.onBackPressed();
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
                final String placeName = AddPlaceName.getText().toString();
                final String placeDescription = AddDesc.getText().toString();
                final String placeProvince = AddProvinceName.getText().toString();

                if (storageTask != null && storageTask.isInProgress()) {
                    Toast.makeText(AddTravellingPlace.this, "Upload in progress", Toast.LENGTH_SHORT).show();

                } else if (IsImageAdded != false && placeName != null && placeDescription != null && placeProvince != null) {
                    if (placeName.isEmpty()) {
                        AddPlaceName.setError("Required Field");
                    } else if (placeDescription.isEmpty()) {
                        AddDesc.setError("Required Field");
                    } else if (placeProvince.isEmpty()) {
                        AddProvinceName.setError("Required Field");
                    } else {
                        uploadfile(placeName, placeDescription, placeProvince);
                    }
                }
            }
        });

    }

    private void uploadfile(final String placeName, final String placeDescription, final String placeProvince) {

        progressDialog.setTitle("Travelling Place Adding....");
        progressDialog.show();

        final String key = databaseReference.push().getKey();
        storageReference.child(key + ".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.child(key + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("PlaceName", placeName);
                        hashMap.put("Description", placeDescription);
                        hashMap.put("Province", placeProvince);
                        hashMap.put("ImageUrl", uri.toString());

                        databaseReference.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                                Toast.makeText(AddTravellingPlace.this, "Data Successfully Uploaded", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), TravellingPlaces.class));
                                finish();
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