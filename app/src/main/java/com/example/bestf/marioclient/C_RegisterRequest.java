package com.example.bestf.marioclient;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bestf on 2018-03-27.
 */

public class C_RegisterRequest extends StringRequest {
    final static private String URL = "http://xnatalx.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public C_RegisterRequest(String userID, String userPassword, String userName, String companyName, int userAge, String helmetOn, String beltOn, String shoesOn, int helmetID, int beltID, int shoesID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();

        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userName", userName);
        parameters.put("companyName", companyName);
        parameters.put("userAge", userAge + ""); //String 형태변환
        parameters.put("helmetOn", helmetOn);
        parameters.put("beltOn", beltOn);
        parameters.put("shoesOn", shoesOn);
        parameters.put("helmetID", helmetID + "");
        parameters.put("beltID", beltID + "");
        parameters.put("shoesID", shoesID + "");
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
