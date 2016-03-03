package com.example.mars.httpapp;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mars on 2/14/16.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    public String grouplist;
    public HashMap<String,String> user = new HashMap<String,String>();
    public ArrayList<HashMap<String, String>> usergroups = new ArrayList<HashMap<String, String>>();
    private RequestQueue mRequestQueue;

    private class User{
        String username;
        JSONArray usergroups;

        void User(){
            String username = "blank";
            int id;
            String email;
            String firstname;
            String lastname;
            Boolean isAdmin;
        }
    }

    public class StudyGroup{
        int id;
        String department;
        int classnumber;
        String time;
        String description;
    }

    protected User AppUser;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
