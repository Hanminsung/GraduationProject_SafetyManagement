package com.example.bestf.marioclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class C_RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText companyText = (EditText) findViewById(R.id.companyText);
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText ageText = (EditText) findViewById(R.id.ageText);
        final EditText equip1 = (EditText) findViewById(R.id.helmetID);
        final EditText equip2 = (EditText) findViewById(R.id.beltID);
        final EditText equip3 = (EditText) findViewById(R.id.shoesID);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String companyName = companyText.getText().toString();
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userName = nameText.getText().toString();
                int userAge = Integer.parseInt(ageText.getText().toString());
                int helmetID = Integer.parseInt(equip1.getText().toString());
                int beltID = Integer.parseInt(equip2.getText().toString());
                int shoesID = Integer.parseInt(equip3.getText().toString());


                String helmetOn = "X";
                String beltOn = "X";
                String shoesOn = "X";

                //Listener 에 특정 요청하고 원하는 결과 값 다룰 수 있게끔.
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        //JSON response 를 만들어서 특정 response를 실행 했을 때 결과가 담길 수 있도록 한다
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                //회원가입 성공시 알림창 C_RegisterActivity 여기다 띄움.
                                AlertDialog.Builder builder = new AlertDialog.Builder(C_RegisterActivity.this);
                                builder.setMessage("회원 등록에 성공했습니다").setPositiveButton("확인", null).create().show();

                                finish();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(C_RegisterActivity.this);
                                builder.setMessage("회원 등록에 실패했습니다").setNegativeButton("다시시도", null).create().show();
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                //이것들 전부 매개변수로 넘겨줌.
                C_RegisterRequest registerRequest = new C_RegisterRequest(userID, userPassword, userName, companyName, userAge, helmetOn, beltOn, shoesOn, helmetID, beltID, shoesID, responseListener);
                //request queue 에 넣고 Volley 라이브러리 통해서 인터넷 접속하는 방식.
                RequestQueue queue = Volley.newRequestQueue(C_RegisterActivity.this);
                queue.add(registerRequest);

                //버튼 클릭시 registerRequest 실행이 되어서 response 를 받게 되고 해당 response 받았을 때 정상적인 정보이면 회원 등록 성공 했다는 message 생성
                //그리고 registerActivity 에서 loginActivity 로 intent 통해 넘어감
            }
        });
    }
}