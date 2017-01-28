package com.example.ravi.login_php;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText etRName, etRUsername,etRpassword;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRName =(EditText) findViewById(R.id.etRName);
        etRUsername =(EditText) findViewById(R.id.etRPassword);
        etRpassword =(EditText) findViewById(R.id.etRPassword);

        btnRegister =(Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {



    }
}
