package app.tv_talk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.view.WindowManager;
import java.util.ArrayList;

import com.androidquery.AQuery;

public class MainActivity extends AppCompatActivity {
    private AQuery aq;
    private EditText message_text;
    ArrayList<String> message_list;
    ArrayAdapter<String> message_adapter;

    FileIO fio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message_text = (EditText)findViewById(R.id.InputMessage);

        aq = new AQuery(this);
        message_list = new ArrayList<String>();
        message_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  message_list);
        fileCheck();

        aq.id(R.id.ChatList).adapter(message_adapter);
        aq.id(R.id.ChatList).setSelection(message_adapter.getCount() - 1);
        aq.id(R.id.SendButton).clicked(this, "SendText");
        message_adapter.notifyDataSetChanged();

        // hidden keybord
        getWindow().setSoftInputMode((WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN));
    }

    private void fileCheck()
    {
        fio = new FileIO("Chat.txt");
        if(fio.CreateFile())
            message_list.add("create file success");
        else
            message_list.add("Read Message");

        ArrayList<String> temp = fio.ReadFile();
        int len = temp.size();
        for(int i=0; i != len; i++)
            message_list.add(temp.get(i));
    }

    public void SendText(View view)
    {
        if(message_text.getText().toString().compareTo("") == 0)
            return;
        String str = message_text.getText().toString();
        message_list.add(str);
        message_adapter.notifyDataSetChanged();
        message_text.setText("");

        fio.WriteFile(str);
    }

    public void ServerConnect()
    {
        boolean result = true;

        if(result == true){
            message_list.add("접속 성공");
            message_adapter.notifyDataSetChanged();
        }
        else{
            message_list.add("접속 실패");
            message_adapter.notifyDataSetChanged();
        }
    }
}
