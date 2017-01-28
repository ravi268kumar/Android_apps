package com.example.ravi.login_php;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.*;
import com.kosalgeek.asynctask.AsyncResponse;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AsyncResponse, View.OnClickListener {

    EditText etUsername, etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername =(EditText) findViewById(R.id.etUsername);
        etPassword =(EditText) findViewById(R.id.etPassword);
        btnLogin =(Button) findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(this);


    }

    @Override
    public void processFinish(String result) {

        Log.d("Login"," result "+result);

        if(result.equals("success")){

            Intent intent_success = new Intent(this,Profile.class);
            startActivity(intent_success);

            //Toast.makeText(this, "Login successfully", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Login Failed "+result, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {

        HashMap postData = new HashMap();
        postData.put("mobile", "android");
        postData.put("txtUsername", etUsername.getText().toString());
        postData.put("txtPassword", etPassword.getText().toString());

        PostResponseAsyncTask task =new PostResponseAsyncTask(this, postData);
        task.execute("http://49.34.39.139/login.php");

    }
}
