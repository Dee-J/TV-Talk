package com.tv_talk;
/*
    메뉴바에서 Settings 누르면 POPUP창이 뜸
    현재 접속중인 서버의 URL이 보임
    변경하고 Change 누르면 바뀌고, Cancel누르면 안바뀜.

    Intent로 MainActivity에서 URL 받아옴.
    Change일때 resultOK 코드를 보내면서, 변경된 URL도 전송해줌 ㅋ
 */
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
