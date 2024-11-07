package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumberTxt;
    Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            welcomeTxt = findViewById(R.id.textView2);
            luckyNumberTxt = findViewById(R.id.lucky_number_txt);
            shareButton = findViewById(R.id.share_button);

            //Receive the data from MainActivity
            Intent i = getIntent();
            //Take the name: "name" value, from the MainActivity!
            String username = i.getStringExtra("name");
            //Get generated random number from MainActivity
            int randomNumber = i.getIntExtra("randomNumber", 0);
            luckyNumberTxt.setText("" + randomNumber);

            shareButton.setOnClickListener(v1 -> {
                shareData(username, randomNumber);
            });

            return insets;
        });
    }

    private void shareData(String username, int randomNumber) {
        //Implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        //Additional Information
        i.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: " + randomNumber);

        startActivity(Intent.createChooser(i, "Choose a platform"));
    }

//    private int generateRandomNumber() {
//        Random random = new Random();
//        int upperLimit = 1000;
//        //This will generate numbers from 1 to 1000 (upperLimit)!
//        int randomNumberGenerated = random.nextInt(upperLimit);
//        return randomNumberGenerated;
//    }
}