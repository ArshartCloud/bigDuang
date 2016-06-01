package com.example.hope.myapplication;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends Activity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose1);
        List<Movietheatre> list = new ArrayList<Movietheatre>(); // 获取数据


        Movietheatre test = new Movietheatre();
        test.setTheatreDistance(2.33);
        test.setTheatreId(0);
        test.setTheatreMark(4.4);
        test.setTheatrePrice(32.0);
        test.setTheatreName("金逸影城");

        Movietheatre test1 = new Movietheatre();
        test1.setTheatreDistance(2.33);
        test1.setTheatreId(1);
        test1.setTheatreMark(4.4);
        test1.setTheatrePrice(32.0);
        test1.setTheatreName("飞扬影城");

        Movietheatre test2 = new Movietheatre();
        test2.setTheatreDistance(2.33);
        test2.setTheatreId(2);
        test2.setTheatreMark(4.4);
        test2.setTheatrePrice(32.0);
        test2.setTheatreName("映联万和");

        list.add(test);
        list.add(test1);
        list.add(test2);


        TheatreAdapter adapter = new TheatreAdapter(this, list);
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
