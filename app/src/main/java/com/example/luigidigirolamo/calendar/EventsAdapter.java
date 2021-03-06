package com.example.luigidigirolamo.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by luigidigirolamo on 18/11/15.
 */
public class EventsAdapter extends ArrayAdapter<Event> {
    private List<Event> itemList;
    private Context context;

    public EventsAdapter(List<Event> itemList, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;
    }

    public int getCount() {
        if (itemList != null)
            return itemList.size();
        return 0;
    }

    public Event getItem(int position) {
        if (itemList != null)
            return itemList.get(position);
        return null;
    }

    public long getItemId(int position) {
        if (itemList != null)
            return itemList.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }
        Event c = itemList.get(position);
        if (c != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.title);
            TextView tt2 = (TextView) v.findViewById(R.id.startdate);
            TextView tt3 = (TextView) v.findViewById(R.id.enddate);

            if (tt1 != null) {
                tt1.setText(c.getTitle());
            }

            if (tt2 != null) {
                tt2.setText(c.getStartdate());
            }

            if (tt3 != null) {
                tt3.setText(c.getEnddate());
            }
        }
        return v;

    }

    public List<Event> getItemList() {
        return itemList;
    }

    public void setItemList(List<Event> itemList) {
        this.itemList = itemList;
    }

}
