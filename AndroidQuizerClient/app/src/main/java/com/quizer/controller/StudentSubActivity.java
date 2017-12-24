package com.quizer.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.quizer.R;
import com.quizer.bo.QuestionBo;
import com.quizer.model.MCQ;
import com.quizer.model.Question;
import com.quizer.model.TrueFalse;

import java.util.List;

public class StudentSubActivity extends AppCompatActivity {

    private ConstraintLayout clMcq;
    private ConstraintLayout clTF;
    private ConstraintLayout clNum;
    private RadioGroup rdgrpMcqAns;
    private RadioGroup rdgrpTFAns;
    private RadioButton radAnsA;
    private RadioButton radAnsB;
    private RadioButton radAnsC;
    private RadioButton radAnsD;
    private RadioButton radAnsTrue;
    private RadioButton radAnsFalse;
    private TextView tvQuesNum;
    private TextView tvMarks;
    private TextView tvQues;
    private EditText etAns;
    private Button btnNext;

    Bundle bundle;
    List<Question> quesList;
    Question quest = null;
    QuestionBo quesbo =new QuestionBo();
    int userScore = 0;
    int maxScore = 0;

    private class HttpRequestQuestion extends AsyncTask<Integer, Void, List<Question>> {

        @Override
        protected List<Question> doInBackground(Integer... params) {
            try {

                quesList = quesbo.getQuestions(params[0]);
                return quesList;

            } catch (Exception e) {
                Log.e("AttemptQuizActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Question> questions) {

            quesList = questions;
            nextQuestion(new View(StudentSubActivity.this));

        }
    }

    //called on next button click
    public void nextQuestion(View view){

        //getting the question number
        int quesNum = 1;

        if(quest != null)
            quesNum = quesList.indexOf(quest) + 2;

        //marking the question
        if(quesNum>1)
            markQuestion();

        //if no more questions then end the quiz
        if(quesNum == quesList.size()+1)
            endQuiz();

        else{
            if(quesNum == quesList.size())
                btnNext.setText("Finish");
            else
                btnNext.setText("Next");

            quest = quesList.get(quesNum-1);
            tvQuesNum.setText("Question " + quesNum);
            tvQues.setText(quest.getQuestion());
            tvMarks.setText("Max Marks ( " + quest.getMarks() + " )" );


            if(quest instanceof TrueFalse)
                showTrueFalse(quest);

            else if (quest instanceof MCQ)
                showMcq(quest);

            else
                showNumeric(quest);

        }
    }

    public void markQuestion(){

        String correctAnswer = quest.getAnswer();
        String userAns = "";

        if(quest instanceof TrueFalse){
            if(radAnsTrue.isSelected())
                userAns = radAnsTrue.getText().toString();
            else if(radAnsFalse.isSelected())
                userAns = radAnsFalse.getText().toString();
        }
        else if(quest instanceof MCQ){
            if(radAnsA.isSelected())
                userAns = radAnsA.getText().toString();
            else if(radAnsB.isSelected())
                userAns = radAnsB.getText().toString();
            else if(radAnsC.isSelected())
                userAns = radAnsC.getText().toString();
            else if(radAnsD.isSelected())
                userAns = radAnsD.getText().toString();
        }
        else{
            userAns = etAns.getText().toString();
        }

        maxScore += quest.getMarks();
        if(userAns.equals(correctAnswer))
            userScore += quest.getMarks();

    }

    private void showMcq(Question quest) {

        clMcq.setVisibility(View.VISIBLE);
        clTF.setVisibility(View.INVISIBLE);
        clNum.setVisibility(View.INVISIBLE);

        radAnsA.setText(((MCQ)quest).getOptionA());
        radAnsB.setText(((MCQ)quest).getOptionB());
        radAnsC.setText(((MCQ)quest).getOptionC());
        radAnsD.setText(((MCQ)quest).getOptionD());
    }

    private void showTrueFalse(Question quest) {

        clMcq.setVisibility(View.INVISIBLE);
        clTF.setVisibility(View.VISIBLE);
        clNum.setVisibility(View.INVISIBLE);

        radAnsTrue.setText("True");
        radAnsFalse.setText("False");

    }

    private void showNumeric(Question quest) {

        clMcq.setVisibility(View.INVISIBLE);
        clTF.setVisibility(View.INVISIBLE);
        clNum.setVisibility(View.VISIBLE);
    }

    public void endQuiz(){

        Intent intent = new Intent(StudentSubActivity.this, ScoreActivity.class);
        intent.putExtra("score", userScore);
        intent.putExtra("total", maxScore);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sub);

        clMcq = (ConstraintLayout) findViewById(R.id.constraintLayoutMcq);
        clNum = (ConstraintLayout) findViewById(R.id.constraintLayoutNum);
        clTF = (ConstraintLayout) findViewById(R.id.constraintLayoutTF);
        rdgrpMcqAns = (RadioGroup) findViewById(R.id.rdgrpMcqAns);
        rdgrpTFAns = (RadioGroup) findViewById(R.id.rdgrpTFAns);
        radAnsA = (RadioButton) findViewById(R.id.radAnsA);
        radAnsB = (RadioButton) findViewById(R.id.radAnsB);
        radAnsC = (RadioButton) findViewById(R.id.radAnsC);
        radAnsD = (RadioButton) findViewById(R.id.radAnsD);
        radAnsTrue = (RadioButton) findViewById(R.id.radAnsTrue);
        radAnsFalse = (RadioButton) findViewById(R.id.radAnsFalse);
        tvQuesNum = (TextView) findViewById(R.id.tvQuesNum);
        tvMarks = (TextView) findViewById(R.id.tvMarks);
        tvQues = (TextView) findViewById(R.id.tvQues);
        etAns = (EditText) findViewById(R.id.etAns);
        btnNext = (Button) findViewById(R.id.btnNext);


        int quizID = getIntent().getIntExtra("quizID",0);

        new HttpRequestQuestion().execute(quizID);

    }
}
