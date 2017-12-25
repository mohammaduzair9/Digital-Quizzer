package com.quizer.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.quizer.R;
import com.quizer.bo.UserBo;
import com.quizer.model.User;

import org.springframework.web.client.RestTemplate;

public class LoginActivity extends AppCompatActivity {

    private TextView tvApp;
    private TextView tvError;
    private Button btnLogin;
    private EditText etUser;
    private EditText etPass;

    UserBo userbo =new UserBo();
    RestTemplate restTemplate = new RestTemplate();

    /* THREAD FOR REQUESTING LOGIN VALIDATION FROM SERVER */
    private class HttpRequestLogin extends AsyncTask<Void, Void, User> {
        @Override
        protected User doInBackground(Void... params) {
            try {

                User user = userbo.getUser(etUser.getText().toString(), etPass.getText().toString());
                return user;

            } catch (Exception e) {
                Log.e("LoginActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(User user) {

            if(user != null){
                tvError.setText("Login Successful");

                if(user.getType().equals("instructor")){
                    Intent intent = new Intent(LoginActivity.this, InstructorMainActivity.class);
                    startActivity(intent);
                }
                else if(user.getType().equals("student")){
                    Intent intent = new Intent(LoginActivity.this, StudentMainActivity.class);
                    startActivity(intent);
                }
            }
            else{
                tvError.setText("Login Failed");
            }

        }

    }

    /* ACTION CALLED ON "LOGIN" BUTTON CLICK */
    public void validate(View view){

        new HttpRequestLogin().execute();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        tvError = (TextView) findViewById(R.id.tvApp);
        tvError = (TextView) findViewById(R.id.tvError);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

}
