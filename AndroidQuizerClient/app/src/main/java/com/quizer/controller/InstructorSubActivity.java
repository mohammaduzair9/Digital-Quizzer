package com.quizer.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.quizer.R;
import com.quizer.bo.QuestionBo;
import com.quizer.model.Quiz;

import org.springframework.web.client.RestTemplate;

public class InstructorSubActivity extends AppCompatActivity {

    private TabHost tabs;
    TabHost.TabSpec tabMcq, tabTrueFalse, tabNumeric;

    private EditText etMarks;
    private EditText etQuesMcq;
    private EditText etQuesTF;
    private EditText etQuesNum;
    private EditText etAnsNum;
    private EditText etMcqA;
    private EditText etMcqB;
    private EditText etMcqC;
    private EditText etMcqD;
    private RadioGroup rdgrpMcq;
    private RadioGroup rdgrpTF;
    private RadioButton radMcqA;
    private RadioButton radMcqB;
    private RadioButton radMcqC;
    private RadioButton radMcqD;
    private RadioButton radTrue;
    private RadioButton radFalse;
    private Button btnFinalQuiz;
    private Button btnSubMcq;
    private Button btnSubTF;
    private Button btnSubNum;

    QuestionBo quesbo =new QuestionBo();
    RestTemplate restTemplate = new RestTemplate();

    Bundle bundle;
    Quiz quiz;

    private class HttpRequestMakeQuiz extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                quesbo.saveQuestions();

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void nothing) {

        }

    }

    //called on submit MCQ button click
    public void makeMCQ(View view){

        String quest = etQuesMcq.getText().toString();
        int selectedRadID = rdgrpMcq.getCheckedRadioButtonId();
        RadioButton rad = (RadioButton) findViewById(selectedRadID);
        String optA = etMcqA.getText().toString();
        String optB = etMcqB.getText().toString();
        String optC = etMcqC.getText().toString();
        String optD = etMcqD.getText().toString();
        int marks = Integer.parseInt(etMarks.getText().toString());
        String ans;
        if(selectedRadID == radMcqA.getId()){
            ans = optA;
        }
        else if(selectedRadID == radMcqB.getId()){
            ans = optB;
        }
        else if(selectedRadID == radMcqC.getId()){
            ans = optC;
        }
        else {
            ans = optD;
        }

        quesbo.addMCQ(quest, ans, marks, quiz, optA, optB, optC, optD);
        clearData();

    }

    //called on submit TrueFalse button click
    public void makeTrueFalse(View view){

        String quest = etQuesTF.getText().toString();
        int selectedRadID = rdgrpTF.getCheckedRadioButtonId();
        RadioButton rad = (RadioButton) findViewById(selectedRadID);
        String optT = "True";
        String optF = "False";
        int marks = Integer.parseInt(etMarks.getText().toString());
        String ans;
        if(selectedRadID == radTrue.getId()){
            ans = optT;
        }
        else {
            ans = optF;
        }

        quesbo.addTrueFalse(quest, ans, marks, quiz, optT, optF);
        clearData();
    }

    //called on submit Numeric button click
    public void makeNumeric(View view){

        String quest = etQuesNum.getText().toString();
        int marks = Integer.parseInt(etMarks.getText().toString());
        String ans = etAnsNum.getText().toString();

        quesbo.addNumeric(quest, ans, marks, quiz);
        clearData();
    }

    private void clearData(){
        etQuesMcq.setText("");
        etMarks.setText("");
        etMcqA.setText("");
        etMcqB.setText("");
        etMcqC.setText("");
        etMcqD.setText("");
        etQuesTF.setText("");
        etMarks.setText("");
        etQuesNum.setText("");
        etMarks.setText("");
        etAnsNum.setText("");
    }

    //called on make quiz button click
    public void makeQuiz(View view){

        new HttpRequestMakeQuiz().execute();

        Intent intent = new Intent(InstructorSubActivity.this, InstructorMainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sub);

        tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();

        tabMcq = tabs.newTabSpec("MCQ");
        tabMcq.setContent(R.id.MCQ);
        tabMcq.setIndicator("MCQ");
        tabs.addTab(tabMcq);

        tabTrueFalse = tabs.newTabSpec("TrueFalse");
        tabTrueFalse.setContent(R.id.TrueFalse);
        tabTrueFalse.setIndicator("TrueFalse");
        tabs.addTab(tabTrueFalse);

        tabNumeric = tabs.newTabSpec("Numeric");
        tabNumeric.setContent(R.id.Numeric);
        tabNumeric.setIndicator("Numeric");
        tabs.addTab(tabNumeric);

        etMarks = (EditText) findViewById(R.id.etMarks);
        etQuesMcq = (EditText) findViewById(R.id.etQuesMCQ);
        etQuesTF = (EditText) findViewById(R.id.etQuesTF);
        etQuesNum = (EditText) findViewById(R.id.etQuesNum);
        etAnsNum = (EditText) findViewById(R.id.etAnsNum);
        etMcqA = (EditText) findViewById(R.id.etMcqA);
        etMcqB = (EditText) findViewById(R.id.etMcqB);
        etMcqC = (EditText) findViewById(R.id.etMcqC);
        etMcqD = (EditText) findViewById(R.id.etMcqD);
        rdgrpMcq = (RadioGroup) findViewById(R.id.rdgrpMcq);
        radMcqA = (RadioButton) findViewById(R.id.radMcqA);
        radMcqB = (RadioButton) findViewById(R.id.radMcqB);
        radMcqC = (RadioButton) findViewById(R.id.radMcqC);
        radMcqD = (RadioButton) findViewById(R.id.radMcqD);
        rdgrpTF = (RadioGroup) findViewById(R.id.rdgrpTF);
        radTrue = (RadioButton) findViewById(R.id.radTrue);
        radFalse = (RadioButton) findViewById(R.id.radFalse);
        btnFinalQuiz = (Button) findViewById(R.id.btnFinalQuiz);
        btnSubMcq = (Button) findViewById(R.id.btnSubMcq);
        btnSubTF = (Button) findViewById(R.id.btnSubTF);
        btnSubNum = (Button) findViewById(R.id.btnSubNum);

        bundle = getIntent().getExtras();
        quiz = bundle.getParcelable("quiz");

    }
}
