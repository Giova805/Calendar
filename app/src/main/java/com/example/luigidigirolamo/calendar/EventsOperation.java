package com.example.luigidigirolamo.calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EventsOperation extends AsyncTask<Void, Void, JSONArray> {
    Activity activity;

    public EventsOperation(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected JSONArray doInBackground(Void... params) {
        URL url = null;
        UserInfos userInfos = UserInfos.getInstance();
        String response = "";
        String address = userInfos.getIpAddress();
        try {
            url = new URL(address+"users/"+userInfos.getUserName()+"/token/"+userInfos.getToken()+"/events");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
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
            List<Event> list = new ArrayList<Event>();
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    list.add(i, convertEvent(jsonArray.getJSONObject(i)));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            UserIndex.adapter.setItemList(list);
            UserIndex.adapter.notifyDataSetChanged();
        }
        else
            activity.startActivity(new Intent(activity, MainActivity.class));
    }

    private Event convertEvent(JSONObject obj) throws JSONException {
        String title = obj.getString("title");
        String startdate = obj.getString("start");
        String enddate = obj.getString("end");

        return new Event(title, startdate, enddate);
    }
}
