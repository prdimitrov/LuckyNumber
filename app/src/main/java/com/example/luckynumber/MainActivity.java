package com.example.luckynumber;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
                        //Passing the name to second activity
                        i.putExtra("name", username);
                        //Going on to the second activity
                        startActivity(i);
                    }

            });

            return insets;
        });
    }
}