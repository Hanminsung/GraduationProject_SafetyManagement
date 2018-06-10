package com.example.bestf.marioclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class M_UserManagementActivity extends AppCompatActivity {

    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;
    private List<User> saveList;
    private DataSetSync myAsyncTask;
    Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        listView = (ListView) findViewById(R.id.userListView);
        userList = new ArrayList<User>();
        saveList = new ArrayList<User>();
        adapter = new UserListAdapter(getApplicationContext(), userList, this, saveList); //어뎁터 만들고
        listView.setAdapter(adapter); //어뎁터 세팅

        EditText search = (EditText)findViewById(R.id.search);

        myAsyncTask = new DataSetSync();
        myAsyncTask.execute();


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //실제 화면에 있는것과 DB애 았는것 달라서 위에 list 만듬
                searchUser(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /*
        mHandler = new Handler(){
            public void handleMessage(Message msg){
                userList.clear();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            //jsonObject 파싱 우리가 만들었던 오브젝트 response
                            JSONArray jsonArray = jsonResponse.getJSONArray("response");
                            //사용자 관리위한 수
                            int count = 0;
                            String userID, userPassword, userName, companyName, helmetOn, beltOn, shoesOn;
                            while(count < jsonArray.length())
                            {
                                JSONObject object = jsonArray.getJSONObject(count);
                                userID = object.getString("userID");
                                userPassword = object.getString("userPassword");
                                userName = object.getString("userName");
                                companyName = object.getString("companyName");
                                helmetOn = object.getString("helmetOn");
                                beltOn = object.getString("beltOn");
                                shoesOn = object.getString("shoesOn");

                                User user = new User(userID, userPassword, userName, companyName, helmetOn, beltOn, shoesOn);
                                if(!userID.equals("admin"))
                                {
                                    userList.add(user);
                                    saveList.add(user);
                                }
                                count++;
                            }

                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                UserListRequest userListRequest = new UserListRequest(responseListener);
                //Volley 이용해서 인터넷으로 보냄.
                RequestQueue queue = Volley.newRequestQueue(M_UserManagementActivity.this);
                queue.add(userListRequest);

                adapter.notifyDataSetChanged();

                mHandler.sendEmptyMessageDelayed(0,1000);
            }
        };
        */
        /*
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonResponse = new JSONObject(response);
                                    //jsonObject 파싱 우리가 만들었던 오브젝트 response
                                    JSONArray jsonArray = jsonResponse.getJSONArray("response");
                                    //사용자 관리위한 수
                                    int count = 0;
                                    String userID, userPassword, userName, companyName, helmetOn, beltOn, shoesOn;
                                    while(count < jsonArray.length())
                                    {
                                        JSONObject object = jsonArray.getJSONObject(count);
                                        userID = object.getString("userID");
                                        userPassword = object.getString("userPassword");
                                        userName = object.getString("userName");
                                        companyName = object.getString("companyName");
                                        helmetOn = object.getString("helmetOn");
                                        beltOn = object.getString("beltOn");
                                        shoesOn = object.getString("shoesOn");

                                        User user = new User(userID, userPassword, userName, companyName, helmetOn, beltOn, shoesOn);
                                        if(!userID.equals("admin"))
                                        {
                                            userList.add(user);
                                            saveList.add(user);
                                        }
                                        count++;
                                    }

                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        };
                        UserListRequest userListRequest = new UserListRequest(responseListener);
                        //Volley 이용해서 인터넷으로 보냄.
                        RequestQueue queue = Volley.newRequestQueue(M_UserManagementActivity.this);
                        queue.add(userListRequest);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        t.start();
        */
    }

    public void searchUser(String search){
        userList.clear();
        for(int i = 0; i<saveList.size(); i++){
            if(saveList.get(i).getCompanyName().contains(search))
            {
                userList.add(saveList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    public class DataSetSync extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... unused) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonResponse = new JSONObject(response);
                        //jsonObject 파싱 우리가 만들었던 오브젝트 response
                        JSONArray jsonArray = jsonResponse.getJSONArray("response");
                        //사용자 관리위한 수
                        int count = 0;
                        String userID, userPassword, userName, companyName, helmetOn, beltOn, shoesOn;
                        while(count < jsonArray.length())
                        {
                            JSONObject object = jsonArray.getJSONObject(count);
                            userID = object.getString("userID");
                            userPassword = object.getString("userPassword");
                            userName = object.getString("userName");
                            companyName = object.getString("companyName");
                            helmetOn = object.getString("helmetOn");
                            beltOn = object.getString("beltOn");
                            shoesOn = object.getString("shoesOn");

                            User user = new User(userID, userPassword, userName, companyName, helmetOn, beltOn, shoesOn);
                            if(!userID.equals("admin"))
                            {
                                userList.add(user);
                                saveList.add(user);
                            }
                            count++;
                        }

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };

            UserListRequest userListRequest = new UserListRequest(responseListener);
            //Volley 이용해서 인터넷으로 보냄.
            RequestQueue queue = Volley.newRequestQueue(M_UserManagementActivity.this);
            queue.add(userListRequest);
            return(null);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }
}
