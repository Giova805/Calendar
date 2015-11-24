package com.example.luigidigirolamo.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by giovanni on 24/11/2015.
 */
public class EventsCreate  extends AsyncTask<Void, Void, JSONArray> {
    Activity activity;

    public EventsCreate(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected JSONArray doInBackground(Void... params) {
        URL url = null;
        UserInfos userInfos = UserInfos.getInstance();
        String response = "";
        String address = userInfos.getIpAddress();
        JSONObject eventInfo = new JSONObject();
        try {
            url = new URL(address+"users/"+userInfos.getUserName()+"/token/"+userInfos.getToken()+"/events");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            //eventInfo.put("name", "eventName");

            urlConnection.connect();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer stringBuffer = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            String str = stringBuffer.toString();
            JSONArray jsonEvents = new JSONArray(str);
            in.close();
            return jsonEvents;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        if(jsonArray!=null) {
            activity.startActivity(new Intent(activity, UserIndex.class));
        }
    }

    private Event convertEvent(JSONObject obj) throws JSONException {
        String title = obj.getString("title");
        String startdate = obj.getString("start");
        String enddate = obj.getString("end");
        String id = obj.getString("id");

        return new Event(title, startdate, enddate, id);
    }
}
