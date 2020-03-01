package com.example.cks.foodorderapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;

public class AddFood extends AppCompatActivity {

    private ImageButton imageButton;
    private EditText f_name, f_price, f_desc;
    private DatabaseReference mDatabase;
    private static int GALL_REQ = 1;
    private Uri uri;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        f_name = (EditText) findViewById(R.id.food_Name);
        f_desc = (EditText) findViewById(R.id.food_Desc);
        f_price = (EditText) findViewById(R.id.food_Price);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference().child("Item");

    }

    public void imageButtonClicked(View view) {
        Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        imageIntent.setType("image/*");
        startActivityForResult(imageIntent,GALL_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;
        if(requestCode == GALL_REQ && resultCode == RESULT_OK)
        {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

            } catch (IOException e) {
                e.printStackTrace();
            }
            imageButton = (ImageButton) findViewById(R.id.food_Image);
            imageButton.setImageBitmap(bitmap);
        }
    }

    public void AddFoodItemOnClick(View view) {
        final String txt_name = f_name.getText().toString().trim();
        final String txt_desc = f_desc.getText().toString().trim();
        final String txt_price = f_price.getText().toString().trim();

        if(!TextUtils.isEmpty(txt_name) && !TextUtils.isEmpty(txt_desc) && !TextUtils.isEmpty(txt_price))
        {
            StorageReference filepath = storageReference.child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    final Uri downloaduri = taskSnapshot.getDownloadUrl();
                    DatabaseReference storingdata = mDatabase.push();

                    storingdata.child("name").setValue(txt_name);
                    storingdata.child("desc").setValue(txt_desc);
                    storingdata.child("price").setValue(txt_price);
                    storingdata.child("image").setValue(downloaduri.toString());
                }
            });
        }
    }
}
