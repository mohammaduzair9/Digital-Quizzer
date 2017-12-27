package com.quizer.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quizer.R;

public class ScoreActivity extends AppCompatActivity {

    private TextView tvScore;
    private TextView tvTotal;

    /* ACTION CALLED ON "ATTEMPT NEW QUIZ" BUTTON CLICK */
    public void attemptNew(View view){

        Intent intent = new Intent(ScoreActivity.this, StudentMainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvScore = (TextView) findViewById(R.id.tvScore);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        /* RETRIEVING SCORE AND MAX MARKS FROM INTENT */
        int userScore = getIntent().getIntExtra("score",0);
        int maxScore = getIntent().getIntExtra("total",0);

        tvScore.setText("" + userScore);
        tvTotal.setText("" + maxScore);
    }
}
