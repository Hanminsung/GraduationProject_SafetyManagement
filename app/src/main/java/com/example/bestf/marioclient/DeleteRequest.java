package com.example.bestf.marioclient;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bestf on 2018-04-30.
 */

public class DeleteRequest extends StringRequest {

    final static private String URL = "http://xnatalx.cafe24.com/Delete.php";
    private Map<String, String> parameters;
    //특정한 유저아이디 받아서 URL 에 userID 애 유저아이디값을 파라미터로 매칭시키고 전송
    public DeleteRequest(String userID, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
