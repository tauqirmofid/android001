package com.example.assignment001;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;
import java.util.regex.Pattern;

public class Assignment3 extends AppCompatActivity {
    // Declaring UI components
    private EditText nameEditText, idEditText, emailEditText, phoneEditText, passwordEditText;
    private Spinner deptSpinner;
    private Button submitButton, backToLoginButton, homeButton;
    private ImageView closeButton;
    private LinearLayout inputLayout, outputLayout;
    private TextView outputText;

    // Declaring validation patterns
    private Pattern namePattern = Pattern.compile("[a-zA-Z\\s]+");
    private Pattern emailPattern = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    private Pattern phonePattern = Pattern.compile("^\\d{10}$");

    // Variables to store user input
    private String name, id, email, phone, password, dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment3);

        // Initializing UI components
        nameEditText = findViewById(R.id.name);
        idEditText = findViewById(R.id.sId);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.num);
        passwordEditText = findViewById(R.id.pass);
        deptSpinner = findViewById(R.id.spinner);
        submitButton = findViewById(R.id.submit);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);

        // New Buttons
        backToLoginButton = findViewById(R.id.backToLoginButton);
        homeButton = findViewById(R.id.homeButton);
        closeButton = findViewById(R.id.closeButton); // Initialize the close button

        // Setting up the Spinner with department options
        String[] departments = {"Select Department", "CSE", "EEE", "ARCH", "CE", "BuA", "ENG", "LAW", "IS", "BNG", "THM", "PH"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, departments);
        deptSpinner.setAdapter(adapter);

        // Spinner item selection listener
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept = deptSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Button click listener for validation
        submitButton.setOnClickListener(v -> {
            // Getting user input
            name = nameEditText.getText().toString().trim();
            id = idEditText.getText().toString().trim();
            email = emailEditText.getText().toString().trim();
            phone = phoneEditText.getText().toString().trim();
            password = passwordEditText.getText().toString().trim();

            // Validating input
            if (name.isEmpty()) {
                nameEditText.setError("Name cannot be empty");
                nameEditText.requestFocus();
            } else if (!namePattern.matcher(name).matches()) {
                nameEditText.setError("Only alphabets and spaces allowed");
                nameEditText.requestFocus();
            } else if (id.isEmpty()) {
                idEditText.setError("ID cannot be empty");
                idEditText.requestFocus();
            } else if (email.isEmpty()) {
                emailEditText.setError("Email cannot be empty");
                emailEditText.requestFocus();
            } else if (!emailPattern.matcher(email).matches()) {
                emailEditText.setError("Invalid email format");
                emailEditText.requestFocus();
            } else if (phone.isEmpty()) {
                phoneEditText.setError("Mobile number cannot be empty");
                phoneEditText.requestFocus();
            } else if (!phonePattern.matcher(phone).matches()) {
                phoneEditText.setError("Enter a valid 10-digit mobile number");
                phoneEditText.requestFocus();
            } else if (password.isEmpty()) {
                passwordEditText.setError("Password cannot be empty");
                passwordEditText.requestFocus();
            } else if (Objects.equals(dept, "Select Department")) {
                Toast.makeText(this, "Please select a department", Toast.LENGTH_SHORT).show();
            } else {
                // If all inputs are valid, display the output layout
                inputLayout.setVisibility(View.GONE);
                outputLayout.setVisibility(View.VISIBLE);
                String output = "Name: " + name + "\nID: " + id + "\nEmail: " + email + "\nMobile Number: " + phone + "\nDepartment: " + dept;
                outputText.setText(output);
            }
        });

        // Button click listener to return to the log-in page
        backToLoginButton.setOnClickListener(v -> {
            inputLayout.setVisibility(View.VISIBLE);
            outputLayout.setVisibility(View.GONE);
        });

        // Button click listener to go to the home page (MainActivity)
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(Assignment3.this, MainActivity.class);
            startActivity(intent);
        });

        // Click listener for the red cross (close button)
        closeButton.setOnClickListener(v -> {
            Intent intent = new Intent(Assignment3.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
