package com.example.luigidigirolamo.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserIndex extends AppCompatActivity {
    public static ListView listView;
    public static EventsAdapter adapter;
    UserInfos uI = UserInfos.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_index);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new EventsAdapter(new ArrayList<Event>(), this);
        ListView lView = (ListView) findViewById(R.id.listView);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //show information event
            }
        });
        lView.setAdapter(adapter);

        new EventsOperation(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                return true;
            case R.id.newevent:
                newEvent();
                return true;
            case R.id.calendars:
                calendars();
                return true;
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout() {
        new LogoutOperation(this).execute();
    }
    public void refresh() {
        new LoginOperation(this).execute(uI.getUserName(), uI.getPassword(), uI.getIpAddress());
    }
    public void newEvent() {
        startActivity(new Intent(this, NewEventActivity.class));
    }
    public void calendars() {

    }
}
