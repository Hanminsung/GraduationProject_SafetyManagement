package com.example.bestf.marioclient;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bestf on 2018-05-04.
 */

public class EquipStateRequest extends StringRequest {
    final static private String URL = "http://xnatalx.cafe24.com/EquipState.php";
    private Map<String, String> parameters;

    public EquipStateRequest(String userID, String helmetOn, String beltOn, String shoesOn, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("helmetOn", helmetOn);
        parameters.put("beltOn", beltOn);
        parameters.put("shoesOn", shoesOn);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}