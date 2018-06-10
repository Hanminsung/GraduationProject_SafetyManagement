package com.example.bestf.marioclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class C_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView idText = (TextView) findViewById(R.id.idText);
        TextView passwordText = (TextView) findViewById(R.id.passwordText);
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
        Button equipmentStateButton = (Button) findViewById(R.id.equipmentStateButton);
        Button userManagementButton = (Button) findViewById(R.id.userManagementButton);

        Intent intent = getIntent();
        final String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");
        String message = "환영합니다, " + userID + "님!";

        final String helmetID = intent.getStringExtra("helmetID");
        final String beltID = intent.getStringExtra("beltID");
        final String shoesID = intent.getStringExtra("shoesID");

        idText.setText(userID);
        passwordText.setText(userPassword);
        welcomeMessage.setText(message);

        //관리자 버튼 비활성화
        if(!userID.equals("admin"))
        {
            userManagementButton.setVisibility(View.GONE);
        }
        else
        {
            equipmentStateButton.setVisibility(View.GONE);
        }

        //내부 함수 실행해서 파싱 후 다음 엑티비티로 넘어가기 Intent 로 같이 보내줌
        userManagementButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //new BackgroundTask().execute();
                Intent userListIntent = new Intent(C_MainActivity.this, M_UserManagementActivity.class);
                C_MainActivity.this.startActivity(userListIntent);
            }
        });

        equipmentStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(C_MainActivity.this, W_EquipmentStateActivity.class);
                intent.putExtra("userID", userID);
                intent.putExtra("helmetID", helmetID);
                intent.putExtra("beltID", beltID);
                intent.putExtra("shoesID", shoesID);
                startActivity(intent);
            }
        });
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        //초기화 (특정 웹사이트 서버가 존재하는 php 파일로 초기화
        @Override
        protected void onPreExecute(){
            target = "http://xnatalx.cafe24.com/UserList.php";
        }

        //웹파싱
        @Override
        protected String doInBackground(Void...voids){
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                //버퍼드 리더로 하나씩 읽어옴
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
                //매 열마다 입력을 받음
                //매 열마다 temp 에 담겨서 stringBuilder 안에 넣어짐
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferReader.readLine()) != null)
                {
                    stringBuilder.append(temp + "\n");
                }
                bufferReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //해당 문자열 집합 반환
                return stringBuilder.toString().trim();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            //오류 발생시 null 값 받아감
            return null;
        }


        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }
        //모든 파싱 끝난 후 다음 activity 로 넘어감

        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(C_MainActivity.this, M_UserManagementActivity.class);
            intent.putExtra("userList", result);
            C_MainActivity.this.startActivity(intent);
        }
    }
}
