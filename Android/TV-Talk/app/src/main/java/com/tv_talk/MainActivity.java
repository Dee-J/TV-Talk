package com.tv_talk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private ArrayList<String> Log_Message;
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
        // link adapter and scroll down to end line

        getWindow().setSoftInputMode((WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN));
        // hide keybord

        this.Log_Message = new ArrayList<String>();

        this.webView = (WebView)findViewById(R.id.webView);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.Log_Message.add("Connect to "+this.url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add(0, 1, 0, "Log");
        item = menu.add(0, 2, 0, "Setting");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch(item.getItemId()){
            case 1:
                intent = new Intent(getApplicationContext(), LogActivity.class);
                intent.putExtra("url", this.url.toString());
                intent.putStringArrayListExtra("Log", this.Log_Message);
                startActivity(intent);
                return true;
            case 2:
                intent = new Intent(getApplicationContext(), UrlActivity.class);
                intent.putExtra("url", this.url.toString());
                startActivityForResult(intent, 0);
                return true;
        }
        return false;
    }

    @Override   // Change URL
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 0) {  // requestCode = 0 -> Change URL
            if (resultCode == RESULT_OK) {
                String temp = data.getStringExtra("url").toString();
                if (temp == null)
                    return;

                this.Log_Message.add("URL Change: " + this.url + " -> " + temp);
                this.url = temp.toString();
                this.webView.loadUrl(this.url);
            }
        }
    }

    /*
    private void send_text(){
        if(message_text.length() == 0){
            return;
        }
        String str = message_text.getText().toString();
        message_list.add(str);
        message_adapter.notifyDataSetChanged();
        message_text.setText("");
    }
    */
    /*
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
    */
    /*
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
    */

//    private void readServerJson(){
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
    /*
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
    */ protected void change_channel(String str){
        this.setTitle(this.channel + str);
    }
}
