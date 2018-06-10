package com.example.bestf.marioclient;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bestf on 2018-05-04.
 */

public class UserListRequest extends StringRequest {
    final static private String URL = "http://xnatalx.cafe24.com/UserList.php";

    public UserListRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

    }
}