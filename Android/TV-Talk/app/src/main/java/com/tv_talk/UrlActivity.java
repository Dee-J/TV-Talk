package com.tv_talk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.androidquery.AQuery;

public class UrlActivity extends Activity {
    private EditText text;
    private String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// no title
        setContentView(R.layout.change_url);// pop up

        Intent intent = getIntent();
        this.temp = intent.getStringExtra("url").toString();

        Button buttonOK = (Button)findViewById(R.id.OK);
        Button buttonNOK = (Button)findViewById(R.id.NOK);

        text = (EditText)findViewById(R.id.changeURL);
        text.setText(this.temp.toString());

        // change button
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOK = new Intent(getApplicationContext(), MainActivity.class);
                intentOK.putExtra("url", text.getText().toString());
                setResult(RESULT_OK, intentOK);
                finish();
            }
        });

        // cancel button
        buttonNOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCancel = new Intent(getApplicationContext(), MainActivity.class);
                intentCancel.putExtra("url", text.getText().toString());
                setResult(RESULT_CANCELED, intentCancel);
                finish();
            }
        });
    }


}
