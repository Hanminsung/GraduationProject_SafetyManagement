package com.example.bestf.marioclient;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by bestf on 2018-04-30.
 */

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private List<User> userList;
    private Activity parentActivity;
    private List<User> saveList;

    public UserListAdapter(Context context, List<User> userList, Activity parentActivity, List<User> saveList){
        this.context = context;
        this.userList = userList;
        this.parentActivity = parentActivity;
        this.saveList = saveList;
    }

    @Override
    public int getCount() {
        return userList.size();
        //현재 유저수
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
        //유저리스트의 특정 사용자 리턴
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.user, null);
        final TextView userID = (TextView)v.findViewById(R.id.userID);
        //TextView userPassword = (TextView)v.findViewById(R.id.userPassword);
        //TextView userName = (TextView)v.findViewById(R.id.userName);
        TextView companyName = (TextView)v.findViewById(R.id.companyName);
        TextView helmetState = (TextView)v.findViewById(R.id.helmetState);
        TextView beltState = (TextView)v.findViewById(R.id.beltState);
        TextView shoesState = (TextView)v.findViewById(R.id.shoesState);

        //유저리스트의 특정 유저의 정보 가져오기
        userID.setText(userList.get(i).getUserID());
        //userPassword.setText(userList.get(i).getUserPassword());
        //userName.setText(userList.get(i).getUserName());
        companyName.setText(userList.get(i).getCompanyName());

        helmetState.setText(userList.get(i).getHelmetState());
        beltState.setText(userList.get(i).getBeltState());
        shoesState.setText(userList.get(i).getShoesState());

        v.setTag(userList.get(i).getUserID());

        Button deleteButton = (Button) v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //버튼 클릭시 response 리스너 만들기
                //결과에 대한 값 받을 수 있는 리스너
                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    //특정 website로부터 response 전달 되면
                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                userList.remove(i);

                                //saveLost
                                for(int i=0; i<saveList.size();i++){
                                    if(saveList.get(i).getUserID().equals(userID.getText().toString())){
                                        saveList.remove(i);
                                        break;
                                    }
                                }

                                notifyDataSetChanged();//data변경 어뎁터 알리기
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                DeleteRequest deleteRequest = new DeleteRequest(userID.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(parentActivity);
                queue.add(deleteRequest);
            }
        });
        return v;
        //하나의 사용자 보여줌
    }
}
