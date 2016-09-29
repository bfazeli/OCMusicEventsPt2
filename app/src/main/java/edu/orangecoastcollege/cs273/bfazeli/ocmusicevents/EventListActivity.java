package edu.orangecoastcollege.cs273.bfazeli.ocmusicevents;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EventListActivity extends ListActivity {

    private ListView eventListView;
    private Context context = this;
    private ArrayList<MusicEvent> allMusicEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the adapter (which binds the ListView with the data in MusicEvent.java)
        // Since the data is in an array, we use an ArrayAdapter:

        //setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MusicEvent.titles));

        try {
            allMusicEvents = JSONLoader.loadJSONFromAsset(context);
        }
        catch (IOException ex) {
            Log.e("OC Music events", "Error loading JSON data." + ex.getMessage());
        }

        setListAdapter(new MusicEventAdapter(context, R.layout.music_event_list_item_layout, allMusicEvents));
        //setListAdapter(new MusicEventAdapter(context, R.layout.m, allMusicEvents));
        //setContentView(R.layout.activity_event_list);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id) {
        // 1) Get the position, get the title, get the details
        MusicEvent clickedEvent = allMusicEvents.get(pos);
        String title = clickedEvent.getTitle();
        String date = clickedEvent.getDate();
        String day = clickedEvent.getDay();
        String time = clickedEvent.getTime();
        String location = clickedEvent.getLocation();
        String address1 = clickedEvent.getAddress1();
        String address2 = clickedEvent.getAddress2();

        // 2) Make a new Intent
        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);

        // 3) Put the Strings into the Intent
        detailsIntent.putExtra("Title", title);
        detailsIntent.putExtra("Date", date);
        detailsIntent.putExtra("Day", day);
        detailsIntent.putExtra("Time", time);
        detailsIntent.putExtra("Location", location);
        detailsIntent.putExtra("Address1", address1);
        detailsIntent.putExtra("Address2", address2);

        // 4) Start activity
        startActivity(detailsIntent);
    }
}
