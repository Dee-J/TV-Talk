package com.tv_talk;

import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class ServerConnect {
    private String ip;
    private int port;
    private Socket conn;
    ServerConnect() {
        ip = null;
        port = 8000;
        conn = null;
    }
    ServerConnect(String ip, int port) {
        this.ip = ip;
        this.port = port;
        conn = null;
    }

    public boolean Connect() {
        boolean result = true;
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
            conn = new Socket(ip, port);
            result = conn.isConnected();
            if(result == true)
                conn.close();
        }
        catch (IOException e) {
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