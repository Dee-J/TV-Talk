package com.tv_talk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText message_text;
    private ArrayList<String> message_list;
    private ArrayAdapter<String> message_adapter;

    private AQuery aq;  // aquery

    private ServerConnect sc;
    private SocketConnect soc;
    private String ip = "192.168.200.124";
    private int port = 8000;
    // server info
    private String url = "http://192.168.200.124:8000/";
    // json 받아오는 url
    private String channel = "현재 채널 : ";
    // 앱 타이틀부에 현재 접속중인 채널명을 붙일것
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message_text = (EditText)findViewById(R.id.editText);
        message_list = new ArrayList<String>();
        message_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, message_list);

        aq = new AQuery(this);
        //Connect();
        readServerJson();
        change_channel("");
        // initial
        message_adapter.notifyDataSetChanged();// update

        aq.id(R.id.listView).adapter(message_adapter);
        aq.id(R.id.listView).setSelection(message_adapter.getCount() - 1);
        // link adapter and scroll down to end line

        aq.id(R.id.sendButton).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_text();
            }
        });
        // method만 넣었더니 Android Studio 1.5에서 안먹히길래 리스너 오버라이드

        message_adapter.notifyDataSetChanged();// update

        getWindow().setSoftInputMode((WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN));
        // hide keybord
    }

    private void send_text(){
        if(message_text.length() == 0){
            return;
        }
        String str = message_text.getText().toString();
        message_list.add(str);
        message_adapter.notifyDataSetChanged();
        message_text.setText("");
    }

    private void send_json(String str){
        JSONObject obj = sc.SendMessageServer("Type", str);
        aq.post(this.url, obj, JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if(object != null){
                    Log.i("JSON", "보내기 성공");
                }
                else{
                    Log.i("JSON", "보내기 실패");
                }
            }
        });
    }

    private void Connect(){
        boolean result;
        soc = new SocketConnect(this.url);
        result = this.soc.Connect();
        if(result == true){
            Log.i("Socket", "접속 성공");
        }
        else{
            Log.i("Socket", "접속 실패");
        }
    }

    private void readServerJson(){
        /*
        // read 예제
        JSONConnect jcon = new JSONConnect();
        String[] str = jcon.textJSONPassing();
        if(str == null)
            return;
        try {
            for (int i = 0; i != str.length; i++) {
                if(str[i] == null)
                    break;
                message_list.add(str[i]);
            }
            Log.i("JSON", "Read Success");
        }
        catch (NullPointerException e){
            Log.i("JSON", "Read Fail");
        }
        */
        aq.ajax(this.url, JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if(object != null){
                    Log.i("JSON", "JSON 받아오기 성공");
                    JSONConnect jsonconn = new JSONConnect();
                    String[] str = jsonconn.DecodeJSONObj(object);
                    if(str == null)
                        return;
                    try{
                        for(int i=0; i != str.length; i++){
                            if(str[i] == null)
                                break;
                            message_list.add(str[i]);
                        }
                        Log.i("JSON", "받아오기 & 해독 성공");
                    }
                    catch (NullPointerException e) {
                        Log.i("JSON", "받아오기는 성공인데 해독 실패");
                    }
                }
                else{
                    Log.i("JSON", "JSON 받아오기 실패");
                    Log.i("JSON", url.toString());
                }
            }
        });
    }

    private void change_channel(String str){
        this.setTitle(this.channel + str);
    }
}
