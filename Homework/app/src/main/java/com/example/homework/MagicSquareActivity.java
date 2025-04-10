package com.example.anastasyarodnajalubimaja;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MagicSquareActivity extends Activity {

    private EditText[][] cells = new EditText[3][3];
    private TextView[] rowSums = new TextView[3];
    private TextView[] colSums = new TextView[3];
    private int[] targetRowSums = new int[3];
    private int[] targetColSums = new int[3];
    private int[][] solution = new int[3][3];
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_square);

        level = getIntent().getIntExtra("LEVEL", 5);

        initViews();

        generateSolvablePuzzle();

        Button checkBtn = findViewById(R.id.checkButton);
        checkBtn.setOnClickListener(v -> checkSolution());

        Button exitBtn = findViewById(R.id.exitButton);
        exitBtn.setOnClickListener(v -> finish());
    }

    private void initViews() {
        cells[0][0] = findViewById(R.id.cell11);
        cells[0][1] = findViewById(R.id.cell12);
        cells[0][2] = findViewById(R.id.cell13);
        cells[1][0] = findViewById(R.id.cell21);
        cells[1][1] = findViewById(R.id.cell22);
        cells[1][2] = findViewById(R.id.cell23);
        cells[2][0] = findViewById(R.id.cell31);
        cells[2][1] = findViewById(R.id.cell32);
        cells[2][2] = findViewById(R.id.cell33);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setFilters(new InputFilter[]{
                        new InputFilter.LengthFilter(1),
                        new InputFilterMinMax(0, 9)
                });
            }
        }

        rowSums[0] = findViewById(R.id.row1Sum);
        rowSums[1] = findViewById(R.id.row2Sum);
        rowSums[2] = findViewById(R.id.row3Sum);
        colSums[0] = findViewById(R.id.col1Sum);
        colSums[1] = findViewById(R.id.col2Sum);
        colSums[2] = findViewById(R.id.col3Sum);
    }

    private void generateSolvablePuzzle() {
        Random rand = new Random();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                solution[i][j] = rand.nextInt(10); // 0-9
            }
        }


        for (int i = 0; i < 3; i++) {
            targetRowSums[i] = solution[i][0] + solution[i][1] + solution[i][2];
            rowSums[i].setText(String.valueOf(targetRowSums[i]));
        }

        for (int j = 0; j < 3; j++) {
            targetColSums[j] = solution[0][j] + solution[1][j] + solution[2][j];
            colSums[j].setText(String.valueOf(targetColSums[j]));
        }


        int cellsToKeep = 9 - level;
        int count = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText("");
                cells[i][j].setEnabled(true);
            }
        }

        while (count < cellsToKeep) {
            int i = rand.nextInt(3);
            int j = rand.nextInt(3);

            if (cells[i][j].getText().toString().isEmpty()) {
                cells[i][j].setText(String.valueOf(solution[i][j]));
                cells[i][j].setEnabled(false);
                count++;
            }
        }
    }

    private void checkSolution() {
        try {
            int[][] values = new int[3][3];


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cells[i][j].isEnabled()) {
                        String text = cells[i][j].getText().toString();
                        if (text.isEmpty()) {
                            showResult("Please fill all empty cells!");
                            return;
                        }
                        values[i][j] = Integer.parseInt(text);
                        if (values[i][j] < 0 || values[i][j] > 9) {
                            showResult("Numbers must be 0-9!");
                            return;
                        }
                    } else {
                        values[i][j] = solution[i][j];
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                int sum = values[i][0] + values[i][1] + values[i][2];
                if (sum != targetRowSums[i]) {
                    showResult("Row " + (i+1) + " sum incorrect!");
                    return;
                }
            }

            for (int j = 0; j < 3; j++) {
                int sum = values[0][j] + values[1][j] + values[2][j];
                if (sum != targetColSums[j]) {
                    showResult("Column " + (j+1) + " sum incorrect!");
                    return;
                }
            }

            showResult("Perfect! All sums match!");
            Toast.makeText(this, "Congratulations! Level passed!", Toast.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            showResult("Please enter valid numbers (0-9)!");
        }
    }

    private void showResult(String message) {
        TextView resultText = findViewById(R.id.resultText);
        resultText.setText(message);
    }
}