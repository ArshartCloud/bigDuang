package com.example.controller;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by lenovo on 2016/6/4 0004.
 */
public class LoginController implements ILogin {
    private  String name;
    private String password;


    public void Login(String name, String password) {
        this.name = name;
        this.password = password;
        new Thread(networkTask).start();
    }
   public void SignUp(String name, String password) {

   }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
//            mEmailView.setError(val);
        }
    };

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            String baseURL = "http://115.28.84.73:8080/BigDuang/login";

            String TAG = "http";
            Log.i(TAG, "POST request");
            String retSrc = "null";
            try {
//                String charset = HTTP.UTF_8;

                HttpPost request = new HttpPost(baseURL);
// 先封装一个 JSON 对象
                JSONObject param = new JSONObject();
                param.put("name", name);
                param.put("password", password);
//                mEmailView.setText(param.toString());
// 绑定到请求 Entry
                StringEntity se = new StringEntity(param.toString(),"UTF-8");
                request.setEntity(se);

//                Log.i(TAG, "attemptLogin: before");
// 发送请求
                HttpResponse httpResponse = new DefaultHttpClient().execute(request);
//                Log.i(TAG, "attemptLogin: send");
// 得到应答的字符串，这也是一个 JSON 格式保存的数据
                retSrc = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
                Log.i("response", retSrc);
// 生成 JSON 对象
//        JSONObject result = new JSONObject( retSrc);
//        String token = result.get("token");
            }
            catch (Exception e)
            {
//            e.printStackTrace();
                Log.i(TAG, e.toString());
            }

            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", retSrc);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };


}
