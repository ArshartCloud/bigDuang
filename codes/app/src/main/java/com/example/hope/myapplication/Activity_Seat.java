package com.example.hope.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.zyh.bigduang.Activity_Deal;
import com.example.zyh.bigduang.Activity_Pay;
import com.example.zyh.bigduang.R;

public class Activity_Seat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);


        Button bgopay = (Button)findViewById(R.id.goto_pay);
        bgopay.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(Activity_Seat.this, Activity_Pay.class);
                                          startActivity(intent);
                                      }
                                  }
        );
    }

}
