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

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText message_text;
    private ArrayList<String> message_list;
    private ArrayAdapter<String> message_adapter;

    private AQuery aq;  // aquery

    private SocketConnect sc;
    private String ip = "192.168.153.128";
    private int port = 8000;

    private String url = "http://www.google.com/uds/GnewsSearch?q=Obama&v=1.0";
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
        Connect();
        readServerJson();
        change_channel("");

        aq.id(R.id.listView).adapter(message_adapter);
        aq.id(R.id.listView).setSelection(message_adapter.getCount() - 1);
        aq.id(R.id.sendButton).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_text();
            }
        });
        message_adapter.notifyDataSetChanged();

        //hide keybord
        getWindow().setSoftInputMode((WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN));
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
        aq.post(url, obj, JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if(object != null){
                    Log.i("POST JSON", "보내기 성공");
                }
                else{
                    Log.i("POST JSON", "보내기 실패");
                }
            }
        });
    }
    
    private void Connect(){
        sc = new SocketConnect(this.ip, this.port);
        boolean result = this.sc.Connect();
        if(result == true){
            Log.i("Connect", "접속 성공");
        }
        else{
            Log.i("Connect", "접속 실패");
        }
    }

    private void readServerJson(){
        aq.ajax(this.url, JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                if(object != null){
                    Log.i("JSON", "JSON 받아오기 성공");
                    JSONConnect jsonconn = new JSONConnect();
                    String str = jsonconn.Parssing(object);
                    if(str != null)
                        message_list.add(str);
                }
                else{
                    Log.i("JSON", "JSON 받아오기 실패");
                }
            }
        });
    }

    private void change_channel(String str){
        this.setTitle(this.channel + str);
    }
}
