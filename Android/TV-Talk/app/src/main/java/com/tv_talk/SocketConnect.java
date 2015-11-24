package com.tv_talk;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONObject;

import java.util.ArrayList;

public class SocketConnect {
    private String url;
    private Socket m_socket;
    SocketConnect() {
        url = "";
    }
    SocketConnect(String url) {
        this.url = url;
    }

    public boolean Connect() {
        boolean result = true;
        try {
            m_socket = IO.socket(this.url);
            m_socket.connect();
            if(m_socket.connected()) {
                result = true;
                m_socket.disconnect();
            }
            else
                result = false;
        }
        catch (Exception e) {
            result = false;
        }
        return result;
    }
    public ArrayList<String> Passing() {
        ArrayList<String> arrList = new ArrayList<String>();

        return arrList;
    }
    public JSONObject SendMessageServer(String type, String text) {
        Object[] obj1 = new Object[1];
        obj1[0] = (Object)text.toString();

        JSONObject obj = new JSONConnect().convertJSONObj(type, obj1);
        return obj;
    }
}