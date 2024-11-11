package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumberTxt;
    Button shareButton;
    RadioGroup radioGroup;
    RadioButton yesButton, noButton;
    String luckyPerson;

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
            radioGroup = findViewById(R.id.radio_group);
            yesButton = findViewById(R.id.yes_radio_button);
            noButton = findViewById(R.id.no_radio_button);

            //Receive the data from MainActivity
            Intent i = getIntent();
            //Take the name: "name" value, from the MainActivity!
            String username = i.getStringExtra("name");
            //Get generated random number from MainActivity
            int randomNumber = i.getIntExtra("randomNumber", 0);
            luckyNumberTxt.setText("" + randomNumber);

            shareButton.setOnClickListener(v1 -> {
                // Check if any RadioButton is selected currently
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    // No RadioButton is selected
                    Toast.makeText(this, "Please select if you feel lucky or not!", Toast.LENGTH_SHORT).show();
                } else {
                    // A RadioButton is selected, proceed with the sharing
                    shareData(username, randomNumber);
                }
            });

            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                if (checkedId == R.id.yes_radio_button) {
                    Toast.makeText(this, "You do feel lucky!", Toast.LENGTH_SHORT).show();
                    luckyPerson = "And " + username + " feels lucky today! :)";
                } else if (checkedId == R.id.no_radio_button) {
                    Toast.makeText(this, "You don't feel lucky!", Toast.LENGTH_SHORT).show();
                    luckyPerson = "But " + username + " doesn't feel lucky today! :(";
                }
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
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number is: " + randomNumber + "\n" + luckyPerson);

        startActivity(Intent.createChooser(i, "Choose a platform"));
    }
}