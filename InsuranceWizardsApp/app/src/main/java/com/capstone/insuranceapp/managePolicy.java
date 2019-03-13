package com.capstone.insuranceapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class managePolicy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_manage_policy);

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("clients").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ((TextView)findViewById(R.id.VIN)).setText("1HBGH414JSMN109186");
                                ((TextView)findViewById(R.id.MAKE)).setText("Ford");
                                ((TextView)findViewById(R.id.MODEL)).setText("Explorer");


                                //In time we associate an account with the car
                                //Then get the Vins from the account
                                // Make the ID of each linear layout the VIN of each car
                                //EG ID of Layout 1 is vin G3RD9I1OPbxf1LoBLg9n

                                Object data = document.getData();
                                Log.d("SUCCESS", document.getId() + " = " + document.getData());
                                if(document.getId() == "G3RD9I1OPbxf1LoBLg9n"){

                                    Log.d("SUCCESS", document.getId() + " = " + document.getData());
                                }
                            }
                        } else {
                            Log.w("Error", "Error getting documents.", task.getException());
                        }
                    }
                });



        Button addCarButton = (Button) findViewById(R.id.AddCar);
        addCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCar.class);
                startActivity(intent);
            }
        });


        Button deleteCar = (Button) findViewById(R.id.delete);
        deleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Remove Car from Database Here
                findViewById(R.id.carExample).setVisibility(View.INVISIBLE);

            }
        });
    }

}
