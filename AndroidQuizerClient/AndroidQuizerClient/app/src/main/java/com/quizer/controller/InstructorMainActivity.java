package com.quizer.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.quizer.R;
import com.quizer.bo.QuizBo;
import com.quizer.model.Quiz;

import org.springframework.web.client.RestTemplate;

public class InstructorMainActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private Button btnSaveQuiz;

    QuizBo quizbo =new QuizBo();
    RestTemplate restTemplate = new RestTemplate();

    /* POSTING SAVE QUIZ REQUEST TO SERVER */
    private class HttpRequestMakeQuiz extends AsyncTask<Void, Void, Quiz> {
        @Override
        protected Quiz doInBackground(Void... params) {
            try {

                Quiz quiz = quizbo.addQuiz(etTitle.getText().toString(), etDescription.getText().toString());
                return quiz;

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Quiz quiz) {

            Intent intent = new Intent(InstructorMainActivity.this, InstructorSubActivity.class);
            intent.putExtra("quiz", quiz);
            startActivity(intent);
        }

    }

    /* ACTION CALLED ON "SUBMIT" BUTTON CLICK */
    public void makeQuiz(View view){

        if(!etTitle.getText().toString().equals("") && !etDescription.getText().toString().equals("")) {
            new HttpRequestMakeQuiz().execute();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_main);

        etTitle = (EditText)findViewById(R.id.etTitle);
        etDescription = (EditText)findViewById(R.id.etDescription);
        btnSaveQuiz = (Button)findViewById(R.id.btnSaveQuiz);

    }
}
