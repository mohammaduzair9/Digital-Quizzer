package com.quizer.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.quizer.R;

public class ScoreActivity extends AppCompatActivity {

    private TextView tvScore;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvScore = (TextView) findViewById(R.id.tvScore);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        int userScore = getIntent().getIntExtra("score",0);
        int maxScore = getIntent().getIntExtra("total",0);

        tvScore.setText("" + userScore);
        tvTotal.setText("" + maxScore);
    }
}
