package com.example.hope.myapplication;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.example.zyh.bigduang.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends Activity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        List<ChooseItem> list = new ArrayList<ChooseItem>(); // 获取数据


        ChooseItem test = new ChooseItem();
        test.setChooseItemId(0);
        test.setChooseItemEC("英语3D");
        test.setChooseItemPrice(41.3);
        test.setChooseItemTime("14:00");

        ChooseItem test1 = new ChooseItem();
        test1.setChooseItemId(1);
        test1.setChooseItemEC("英语3D");
        test1.setChooseItemPrice(41.3);
        test1.setChooseItemTime("14:30");

        ChooseItem test2 = new ChooseItem();
        test2.setChooseItemId(2);
        test2.setChooseItemEC("英语3D");
        test2.setChooseItemPrice(41.3);
        test2.setChooseItemTime("14:45");


        list.add(test);
        list.add(test1);
        list.add(test2);


        ChooseAdapter adapter = new ChooseAdapter(this, list);
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);


        //final RelativeLayout layout = (RelativeLayout)findViewById(R.id.addlayout);
        ViewGroup group = (ViewGroup) findViewById(R.id.addlayout);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(R.drawable.bird);
        group.addView(imageView);

        ImageView imageView1 = new ImageView(this);
        imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView1.setImageResource(R.drawable.bird);
        group.addView(imageView1);

        ImageView imageView2 = new ImageView(this);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageView2.setImageResource(R.drawable.bird);
        group.addView(imageView2);

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
