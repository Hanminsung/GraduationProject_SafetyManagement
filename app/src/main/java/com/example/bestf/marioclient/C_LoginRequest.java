package com.example.bestf.marioclient;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bestf on 2018-03-27.
 */

public class C_LoginRequest extends StringRequest {
    final static private String URL = "http://xnatalx.cafe24.com/Login.php";
    private Map<String, String> parameters;

    public C_LoginRequest(String userID, String userPassword, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();

        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
