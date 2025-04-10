package com.example.anastasyarodnajalubimaja;
import com.example.anastasyarodnajalubimaja.MagicSquareHomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView greetingText = findViewById(R.id.greetingText);
        greetingText.setText("Dzien dobry");

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MagicSquareHomeActivity.class);
            startActivity(intent);
        });
    }
}
