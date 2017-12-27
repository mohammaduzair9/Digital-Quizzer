package com.quizer.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.quizer.R;
import com.quizer.bo.QuizBo;
import com.quizer.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class StudentMainActivity extends AppCompatActivity {

    private TextView tvQuizDesc;
    private Button btnSelectQuiz;
    private Spinner spQuiz;

    QuizBo quizbo =new QuizBo();
    List<Quiz> quizList;

    /* THREAD FOR REQUESTING QUIZ LIST FROM SERVER */
    private class HttpRequestQuiz extends AsyncTask<Void, Void, List<Quiz>> {

        @Override
        protected List<Quiz> doInBackground(Void... params) {
            try {

                List<Quiz> quizes = quizbo.getQuizes();
                return quizes;

            } catch (Exception e) {
                Log.e("SelectQuizActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Quiz> quizes) {
            quizList = quizes;

            /* DISPLAYING RETRIEVED QUIZES IN SPINNER */
            spQuiz = (Spinner) findViewById(R.id.spQuiz);
            List<String> list = new ArrayList<String>();

            for(int i=0; i<quizes.size(); i++){
                list.add(quizes.get(i).getTitle());
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(StudentMainActivity.this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spQuiz.setAdapter(dataAdapter);

            spQuiz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    tvQuizDesc.setText(quizList.get(position).getDescription());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
        }

    }

    /* ACTION CALLED ON "SUBMIT" BUTTON CLICK */
    public void startQuiz(View view){

        int quizPos = spQuiz.getSelectedItemPosition();
        int quizid = quizList.get(quizPos).getId();

        Intent intent = new Intent(StudentMainActivity.this, StudentSubActivity.class);
        intent.putExtra("quizID", quizid);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        tvQuizDesc = (TextView) findViewById(R.id.tvQuizDesc);
        btnSelectQuiz = (Button) findViewById(R.id.btnSelectQuiz);

        new HttpRequestQuiz().execute();

    }
}
