package com.example.anastasyarodnajalubimaja;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MagicSquareHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_square_home);

        EditText levelInput = findViewById(R.id.levelInput);
        Button startGameButton = findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(v -> {
            int level = Integer.parseInt(levelInput.getText().toString());

            if (level > 9 || level < 1) {
                Toast.makeText(this, "You should choose number between 1 and 9.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MagicSquareActivity.class);
                intent.putExtra("LEVEL", level);
                startActivity(intent);
            }
        });
    }
}
