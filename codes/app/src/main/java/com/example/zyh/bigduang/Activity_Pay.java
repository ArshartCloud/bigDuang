package com.example.zyh.bigduang;

/**
 * Created by hopezhang on 16/6/5.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zyh.bigduang.Activity_Deal;
import com.example.zyh.bigduang.R;

/**
 *
 * @author baozi
 *
 * 倒计时的类 CountDownTimer
 *
 */
public class Activity_Pay extends Activity {

    private MyCountDownTimer mc;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        Button bfinish = (Button)findViewById(R.id.pay);
        bfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Pay.this, Activity_Deal.class);
                startActivity(intent);
            }
        });

        tv = (TextView) findViewById(R.id.textView8);
        mc = new MyCountDownTimer(300000, 1000);
        mc.start();
    }



    public void restart(View view) {
        // Toast.makeText(Activity_Pay.this, "等待支付", Toast.LENGTH_LONG).show();// toast有显示时间延迟
        mc.start();
    }

    /**
     * 继承 CountDownTimer 防范
     *
     * 重写 父类的方法 onTick() 、 onFinish()
     */

    class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture
         *      表示以毫秒为单位 倒计时的总数
         *
         *      例如 millisInFuture=1000 表示1秒
         *
         * @param countDownInterval
         *      表示 间隔 多少微秒 调用一次 onTick 方法
         *
         *      例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            tv.setText("done");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("Activity", millisUntilFinished + "");
            tv.setText("倒计时(" + millisUntilFinished / 1000 + ")...");
        }
    }
}