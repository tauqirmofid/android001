package com.example.assignment001;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private boolean isColorChanged = false;
    private boolean isImageToggled = false;
    private boolean isTextClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView textView = findViewById(R.id.myTextView);
        ImageView imageView = findViewById(R.id.myImageView);
        Button button = findViewById(R.id.myButton);
        Button as2 = findViewById(R.id.as2Button);
        TextView toggleText = findViewById(R.id.toggleText);


        as2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start SecondActivity
                Intent intent = new Intent(MainActivity.this, FinanceTracker.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button Clicked!", Toast.LENGTH_SHORT).show();


                if (isColorChanged) {
                    button.setBackgroundColor(Color.BLUE);
                } else {
                    button.setBackgroundColor(Color.RED);
                }


                isColorChanged = !isColorChanged;
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Image Clicked!", Toast.LENGTH_SHORT).show();

                if (isImageToggled) {
                    imageView.setImageResource(R.drawable.tauqir);
                } else {
                    imageView.setImageResource(R.drawable.tauqir1);
                }
                isImageToggled = !isImageToggled;
            }
        });


        toggleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Text Clicked!", Toast.LENGTH_SHORT).show();
                if (isTextClicked) {
                    toggleText.setText("IT IS A Clickable TEXT");
                    toggleText.setTextColor(Color.BLUE);
                } else {
                    toggleText.setText("Text is clicked");
                    toggleText.setTextColor(Color.RED);
                }
                isTextClicked = !isTextClicked;
            }
        });
    }
}
