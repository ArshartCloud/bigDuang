package com.example.hope.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.zyh.bigduang.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_Cinemainfo extends Activity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinemainfo);

        // 场次信息
        List<ChooseItem> list = new ArrayList<ChooseItem>(); // 获取数据
        for (int i = 0; i < 12; i++) {
            ChooseItem test = new ChooseItem();
            test.setChooseItemId(0);
            test.setChooseItemEC("英语3D");
            test.setChooseItemPrice(41.3);
            test.setChooseItemTime("14:00");
            list.add(test);
        }
        ChooseAdapter adapter = new ChooseAdapter(this, list);
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(Activity_Cinemainfo.this, Activity_Seat.class);
                startActivity(intent);
            }
        });


        //final RelativeLayout layout = (RelativeLayout)findViewById(R.id.addlayout);
        // 电影信息
        ViewGroup group = (ViewGroup) findViewById(R.id.addlayout);
        for (int i = 0; i < 12; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setImageResource(R.drawable.bird);
            group.addView(imageView);
        }

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
