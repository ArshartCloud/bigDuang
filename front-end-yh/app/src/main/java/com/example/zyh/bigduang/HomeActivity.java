package com.example.zyh.bigduang;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        ListView mlistView = (ListView)findViewById(R.id.listview) ;
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, this.getItem(), R.layout.l ,
                new String[] {"itemTitle", "itemPhoto", "itemSummary", "itemAuthor", "itemPublishtime"},
                new int[] {R.id.title, R.id.photograph, R.id.summary, R.id.author, R.id.publishtime});
        mlistView.setAdapter(simpleAdapter);
    }

    public ArrayList<HashMap<String, Object>> getItem() {
        ArrayList<HashMap<String, Object>> item = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < ListViewItemData.getItemNum(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemTitle", ListViewItemData.getTitle(i));

        }

    }

}
