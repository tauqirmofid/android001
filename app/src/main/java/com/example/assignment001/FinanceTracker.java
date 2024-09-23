package com.example.assignment001;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FinanceTracker extends AppCompatActivity {

    private CheckBox checkFood, checkTransportation, checkEntertainment;
    private RadioGroup paymentMethodGroup;
    private RadioButton selectedPaymentMethod;
    private RatingBar satisfactionRatingBar;
    private SeekBar spendingLimitSeekBar;
    private TextView spendingLimitTextView;
    private Button submitButton;

    private ArrayList<String> selectedCategories = new ArrayList<>();
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_tracker);


        checkFood = findViewById(R.id.check_food);
        checkTransportation = findViewById(R.id.check_transportation);
        checkEntertainment = findViewById(R.id.check_entertainment);
        paymentMethodGroup = findViewById(R.id.payment_method_group);
        satisfactionRatingBar = findViewById(R.id.rating_bar);
        spendingLimitSeekBar = findViewById(R.id.seek_bar);
        spendingLimitTextView = findViewById(R.id.spendLimitTextView);
        submitButton = findViewById(R.id.submit_button);

        builder = new AlertDialog.Builder(this);


        spendingLimitSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                spendingLimitTextView.setText("Monthly Spending Limit: BDT : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        submitButton.setOnClickListener(v -> showSummaryDialog());
    }

    private void showSummaryDialog() {
        selectedCategories.clear();

        if (checkFood.isChecked()) selectedCategories.add("Food");
        if (checkTransportation.isChecked()) selectedCategories.add("Transportation");
        if (checkEntertainment.isChecked()) selectedCategories.add("Entertainment");

        int selectedId = paymentMethodGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            selectedPaymentMethod = findViewById(selectedId);
        }

        StringBuilder summaryMessage = new StringBuilder();
        summaryMessage.append("Selected Categories: ").append(selectedCategories.toString()).append("\n");
        summaryMessage.append("Payment Method: ").append(selectedPaymentMethod != null ? selectedPaymentMethod.getText() : "None").append("\n");
        summaryMessage.append("Satisfaction Rating: ").append(satisfactionRatingBar.getRating()).append("\n");
        summaryMessage.append(spendingLimitTextView.getText()).append("\n");

        builder.setTitle("Finance Summary")
                .setMessage(summaryMessage.toString())
                .setCancelable(false)
                .setPositiveButton("Okay", (dialog, which) -> resetForm())
                .show();
    }

    private void resetForm() {
        checkFood.setChecked(false);
        checkTransportation.setChecked(false);
        checkEntertainment.setChecked(false);
        paymentMethodGroup.clearCheck();
        satisfactionRatingBar.setRating(0);
        spendingLimitSeekBar.setProgress(0);
        spendingLimitTextView.setText("Monthly Spending Limit: BDT0");
        Toast.makeText(getApplicationContext(), "Form Reset!", Toast.LENGTH_SHORT).show();
    }
}
