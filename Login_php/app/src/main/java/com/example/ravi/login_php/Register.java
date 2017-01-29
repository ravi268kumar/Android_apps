package com.example.ravi.login_php;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText etRName, etRUsername, etRpassword;
    Button btnRegister;

    com.android.volley.RequestQueue requestQueue;
    String insertUrl = "http://49.34.39.139/registration.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRName = (EditText) findViewById(R.id.etRName);
        etRUsername = (EditText) findViewById(R.id.etRPassword);
        etRpassword = (EditText) findViewById(R.id.etRPassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request =new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },new Response.ErrorListener(){


                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getPostParams() throws AuthFailureError {
                        Map<String, String> parameters =new HashMap<String, String>();
                        parameters.put("name",etRName.getText().toString());
                        parameters.put("username",etRUsername.getText().toString());
                        parameters.put("password",etRpassword.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });

    }

    @Override
    public void onClick(View view) {

    }


    private class RequestQueue {
    }
}
