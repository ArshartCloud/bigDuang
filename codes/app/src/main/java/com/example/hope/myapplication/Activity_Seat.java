package com.example.hope.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zyh.bigduang.Activity_Deal;
import com.example.zyh.bigduang.Activity_Pay;
import com.example.zyh.bigduang.R;

public class Activity_Seat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat);
        RelativeLayout llayout = (RelativeLayout)findViewById(R.id.mylinear);

        int column = 6;
        int row = 6;

        GridLayout layout = new GridLayout(this);
        layout.setColumnCount(column);

        for (int m = 1; m <= column; m++) {
            for (int n = 1; n <= row; n++) {
                CheckBox mycb = new CheckBox(this);
                mycb.setText("");
                mycb.setPadding(20, 20, 20, 20);

                layout.addView(mycb);
            }
        }

        Button bgopay = new Button(this);
        bgopay.setText("付款");
        bgopay.setBackgroundColor(Color.parseColor("#FF0000"));

        bgopay.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(Activity_Seat.this, Activity_Pay.class);
                                          startActivity(intent);
                                      }
                                  }
        );
        layout.setId(R.id.my_gridview);
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        layout.setLayoutParams(lp);

        RelativeLayout.LayoutParams param2=new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        param2.addRule(RelativeLayout.BELOW, R.id.my_gridview);
        bgopay.setLayoutParams(param2);
        llayout.addView(layout);
        llayout.addView(bgopay);
    }

}
