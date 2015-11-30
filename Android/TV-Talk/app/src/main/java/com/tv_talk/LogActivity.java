package com.tv_talk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.androidquery.AQuery;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity {
    private ArrayList<String> message_list;
    private ArrayAdapter<String> message_adapter;
    private AQuery aq;  // aquery
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loglayout);

        message_list = new ArrayList<String>();
        message_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, message_list);

        Intent intent = getIntent();
        this.url = intent.getStringExtra("url");// current URL
        this.message_list.addAll(intent.getStringArrayListExtra("Log"));
        // LOG list
        // get URL

        aq = new AQuery(this);
        aq.id(R.id.urlTextView).text(this.url); // print URL

        aq.id(R.id.LogList).adapter(message_adapter);
        aq.id(R.id.LogList).setSelection(message_adapter.getCount() - 1);
        message_adapter.notifyDataSetChanged();// update

        getWindow().setSoftInputMode((WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN));
        this.setTitle("Log");
        /*
        aq.id(R.id.sendButton).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_text();
            }
        });
        // method만 넣었더니 Android Studio 1.5에서 안먹히길래 리스너 오버라이드
        */
    }
}
