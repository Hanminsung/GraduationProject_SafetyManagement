package com.example.bestf.marioclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class C_LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final TextView registerButton = (TextView) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(C_LoginActivity.this, C_RegisterActivity.class);
                C_LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();
                //Internet 접속 후 response 건너오면 그 response 받아 처리하는 부분.
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            //JSON Object 만들어서 response 저장하기
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String userID = jsonResponse.getString("userID");
                                String userPassword = jsonResponse.getString("userPassword");
                                String helmetID = jsonResponse.getString("helmetID");
                                String beltID = jsonResponse.getString("beltID");
                                String shoesID = jsonResponse.getString("shoesID");

                                Intent intent = new Intent(C_LoginActivity.this, C_MainActivity.class);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPassword", userPassword);
                                intent.putExtra("helmetID", helmetID);
                                intent.putExtra("beltID", beltID);
                                intent.putExtra("shoesID", shoesID);
                                C_LoginActivity.this.startActivity(intent);

                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(C_LoginActivity.this);
                                builder.setMessage("로그인에 실패했습니다").setNegativeButton("다시시도", null).create().show();
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };

                C_LoginRequest loginRequest = new C_LoginRequest(userID, userPassword, responseListener);
                //Volley 이용해서 인터넷으로 보냄.
                RequestQueue queue = Volley.newRequestQueue(C_LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
