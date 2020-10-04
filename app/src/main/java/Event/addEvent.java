package Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tour_guide.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class addEvent extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE = 1;
    private EditText eventName, placeName, province, city, name, phone, price, description;
    private ImageView backBtn, pickEventImg;
    private Button choose, submit;

    private StorageTask storageTask;
    private ProgressDialog progressDialog;

    Uri imgUri;
    boolean IsImageAdded = false;

    DatabaseReference dbRef;
    StorageReference storeRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        backBtn = findViewById(R.id.back_pressed);
        pickEventImg = findViewById(R.id.eventImg);
        eventName = findViewById(R.id.event_Name);
        placeName = findViewById(R.id.place_Name);
        province = findViewById(R.id.province);
        city = findViewById(R.id.city);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        choose = findViewById(R.id.choose_btn);
        submit = findViewById(R.id.event_submit);
        progressDialog = new ProgressDialog(addEvent.this);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Events");
        storeRef = FirebaseStorage.getInstance().getReference().child("Events");

        //back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent.super.onBackPressed();
            }
        });

        //Choose Image
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String addEvntName = eventName.getText().toString();
                final String addPlace = placeName.getText().toString();
                final String provinceName = province.getText().toString();
                final String cityName = city.getText().toString();
                final String personName = name.getText().toString();
                final String contact = phone.getText().toString();
                final String eventPrice = price.getText().toString();
                final String des = description.getText().toString();

                if (storageTask != null && storageTask.isInProgress()) {
                    Toast.makeText(addEvent.this, "Upload in progress", Toast.LENGTH_LONG).show();
                }else if (IsImageAdded != false && addEvntName != null && addPlace != null && provinceName != null
                        && cityName != null && personName != null && contact != null && eventPrice != null && des != null) {
                    if(addEvntName.isEmpty()){
                        eventName.setError("Required");
                        Toast.makeText(addEvent.this, "Event Name cannot be Empty!", Toast.LENGTH_LONG).show();
                    }else if(addPlace.isEmpty()){
                        placeName.setError("Required");
                        Toast.makeText(addEvent.this, "Place Name cannot be Empty!", Toast.LENGTH_LONG).show();
                    }else if(provinceName.isEmpty()){
                        province.setError("Required");
                        Toast.makeText(addEvent.this, "Province cannot be Empty!", Toast.LENGTH_LONG).show();
                    }else if(cityName.isEmpty()){
                        city.setError("Required");
                        Toast.makeText(addEvent.this, "City cannot be Empty!", Toast.LENGTH_LONG).show();
                    }else if(personName.isEmpty()){
                        name.setError("Required");
                        Toast.makeText(addEvent.this, "Person Name cannot be Empty!", Toast.LENGTH_LONG).show();
                    }else if(contact.isEmpty()){
                        phone.setError("Required");
                        Toast.makeText(addEvent.this, "Contact No cannot be Empty!", Toast.LENGTH_LONG).show();
                    }else if(eventPrice.isEmpty()) {
                        price.setError("Required");
                        Toast.makeText(addEvent.this, "Price cannot be Empty!", Toast.LENGTH_LONG).show();
                    }else if(des.isEmpty()) {
                        description.setError("Required");
                        Toast.makeText(addEvent.this, "Description cannot be Empty!", Toast.LENGTH_LONG).show();
                    }else {
                        uploadFile(addEvntName, addPlace, provinceName, cityName, personName, contact, eventPrice, des);
                    }
                }
            }
        });
    }

    private void uploadFile(final String addEvntName ,final String addPlace, final String provinceName, final String cityName,
                            final String personName, final String contact, final String eventPrice, final String des) {
        progressDialog.setTitle("Uploading..Please Wait..");
        progressDialog.show();

        final String key = dbRef.push().getKey();
        storeRef.child(key + ".jpg").putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storeRef.child(key + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("EventName", addEvntName);
                        hashMap.put("PlaceName", addPlace);
                        hashMap.put("Province", provinceName);
                        hashMap.put("City", cityName);
                        hashMap.put("PersonName", personName);
                        hashMap.put("Phone", contact);
                        hashMap.put("price", eventPrice);
                        hashMap.put("Description", des);
                        hashMap.put("ImageUrl", uri.toString());

                        dbRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                progressDialog.dismiss();
                                Toast.makeText(addEvent.this, "Data Added Successfully !", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), admin_view_All_Events.class));
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
            imgUri = data.getData();
            IsImageAdded = true;
            pickEventImg.setImageURI(imgUri);
        }
    }
}