package com.example.luckynumber;

import android.animation.ObjectAnimator;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.TextView;

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
            TextView textView = findViewById(R.id.textView);
            GradientDrawable background = (GradientDrawable) textView.getBackground();

            ObjectAnimator colorAnimator = ObjectAnimator.ofArgb(
                    background, "color", 0xFFFFFFFF, 0x67FF
            );
            colorAnimator.setDuration(1000);
            colorAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            colorAnimator.setRepeatMode(ObjectAnimator.REVERSE);
            colorAnimator.start();
            return insets;
        });
    }
}