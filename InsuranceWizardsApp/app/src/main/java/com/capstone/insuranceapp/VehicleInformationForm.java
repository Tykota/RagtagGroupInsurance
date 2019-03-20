package com.capstone.insuranceapp;

import android.content.Intent;
import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class VehicleInformationForm extends AppCompatActivity {

    // Activity variables
    private TextView makeTV, modelTV, yearTV, vinTV;
    private String make, model, year, vin;
    private boolean carAdded = false;
    private List<Vehicle> vehicles;


    // Popup variables
    private View popupDialogView;
    private EditText makeET, modelET, yearET, vinET;
    private Button saveBtn, cancelBtn;
    private AlertDialog.Builder popupBuilder;
    private AlertDialog popupDialog;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_information_form);

        client = (Client)getIntent().getSerializableExtra("client");
        vehicles = new List<Vehicle>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Vehicle> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Vehicle vehicle) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Vehicle> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends Vehicle> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Vehicle get(int index) {
                return null;
            }

            @Override
            public Vehicle set(int index, Vehicle element) {
                return null;
            }

            @Override
            public void add(int index, Vehicle element) {

            }

            @Override
            public Vehicle remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Vehicle> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Vehicle> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<Vehicle> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        // set up text views
        makeTV = findViewById(R.id.car_make);
        modelTV = findViewById(R.id.car_model);
        yearTV = findViewById(R.id.car_year);
        vinTV = findViewById(R.id.car_vin);

        // set up add vehicle button
        Button addVehicleBtn = findViewById(R.id.addVehicleBTN);
        addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create popup window
                popupBuilder = new AlertDialog.Builder(VehicleInformationForm.this);
                popupBuilder.setTitle("Vehicle Information Form");
                popupBuilder.setCancelable(false);

                initPopupView();

                popupBuilder.setView(popupDialogView);

                popupDialog = popupBuilder.create();
                popupDialog.show();

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        make = makeET.getText().toString();
                        model = modelET.getText().toString();
                        year = yearET.getText().toString();
                        vin = vinET.getText().toString();

                        // validate input
                        AlertDialog.Builder errorAlertBuilder = new android.app.AlertDialog.Builder(VehicleInformationForm.this);
                        errorAlertBuilder.setTitle("Incomplete Form");
                        errorAlertBuilder.setPositiveButton("Okay", null);
                        errorAlertBuilder.setMessage("You need to enter all fields.");
                        AlertDialog errorAlert = errorAlertBuilder.create();

                        if (make == null || make.equals("")) {
                            errorAlert.show();
                        } else if (model == null || model.equals("")) {
                            errorAlert.show();
                        } else if (year == null || year.equals("")) {
                            errorAlert.show();
                        } else if (vin == null || vin.equals("")) {
                            errorAlert.show();
                        } else {
                            // update text fields
                            carAdded = true;
                            updateTextFields();

                            // close popup dialog
                            popupDialog.cancel();
                        }
                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // close popup dialog
                        popupDialog.cancel();
                    }
                });
            }
        });

        // set up continue button
        Button continueBtn = findViewById(R.id.submit);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carAdded) {
                    // Send client to firestore
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference docRef = db.collection("clients").document();
                    docRef.set(client);
                    Map<String, Object> map = new HashMap<>();
                    map.put("vehicles", vehicles);
                    docRef.set(map, SetOptions.merge());

                    Intent intent = new Intent(getApplicationContext(), ApplicationStatus.class);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder noCarsBuilder = new android.app.AlertDialog.Builder(VehicleInformationForm.this);
                    noCarsBuilder.setTitle("Invalid Input");
                    noCarsBuilder.setPositiveButton("Okay", null);
                    noCarsBuilder.setMessage("You need to add at least one vehicle.");
                    AlertDialog noCarsAlert = noCarsBuilder.create();
                    noCarsAlert.show();
                }
            }
        });
    }

    private void initPopupView() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        popupDialogView = layoutInflater.inflate(R.layout.vehicle_info_popup_form_layout, null);

        // set up edit texts
        makeET = popupDialogView.findViewById(R.id.make_input);
        modelET = popupDialogView.findViewById(R.id.model_input);
        yearET = popupDialogView.findViewById(R.id.year_input);
        vinET = popupDialogView.findViewById(R.id.vin_input);

        // set up buttons
        saveBtn = popupDialogView.findViewById(R.id.saveBtn);
        cancelBtn = popupDialogView.findViewById(R.id.cancelBtn);
    }

    private void updateTextFields() {
        addVehicle(make, model, year, vin);
        make = make + "\n";
        makeTV.append(make);
        model = model + "\n";
        modelTV.append(model);
        year = year + "\n";
        yearTV.append(year);
        vin = vin + "\n";
        vinTV.append(vin);
    }

    public void addVehicle(String make, String model, String year, String vin){
        vehicles.add(new Vehicle(make, model, year, vin));
    }
}
