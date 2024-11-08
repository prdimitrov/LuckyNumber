package com.example.luckynumber;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Button btn = findViewById(R.id.button);
            EditText editText = findViewById(R.id.editText);
            TextView txt = findViewById(R.id.textView);
            GradientDrawable background = (GradientDrawable) txt.getBackground();

            ObjectAnimator colorAnimator = ObjectAnimator.ofArgb(
                    background, "color", 0xFFFFFFFF, 0x67FF
            );
            colorAnimator.setDuration(1000);
            colorAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            colorAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            colorAnimator.start();

            btn.setOnClickListener(v1 -> {
                    String username = editText.getText().toString();
                    if (username.isBlank()) {
                        Toast.makeText(this, "Please, enter a valid name!", Toast.LENGTH_SHORT).show();
                    } else {
                        //Explicit Intent
                        Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                        //Pass the name and the random number to SecondActivity
                        i.putExtra("name", username);
                        i.putExtra("randomNumber", generateRandomNumber());
                        //Going on to the second activity
                        startActivity(i);
                    }

            });

            return insets;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_home) {
            Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.action_search) {
            Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.action_test) {
            Toast.makeText(this, "Test selected", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        int upperLimit = 1000;
        //Generate number from 1 to 1000 (upperLimit)!
        int randomNumberGenerated = random.nextInt(upperLimit);
        return randomNumberGenerated;
    }
}