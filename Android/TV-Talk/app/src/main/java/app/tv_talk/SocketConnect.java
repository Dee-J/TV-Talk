package app.tv_talk;

import java.net.Socket;
import java.util.ArrayList;

public class SocketConnect {
    private String ip;
    private int port;
    private Socket conn;
    SocketConnect()
    {
        ip = null;
        port = 8000;
        conn = null;
    }
    SocketConnect(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
        conn = null;
    }

    public boolean Connect()
    {
        boolean result = true;
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
            conn = new Socket(ip, port);
            result = true;
        }
        catch (Exception e) {
            result = false;
        }

        return result;
    }
    public ArrayList<String> Passing()
    {
        ArrayList<String> arrList = new ArrayList<String>();

        return arrList;
    }
    public boolean SendMessageServer(String type, String text)
    {
        boolean result = true;

        return result;
    }
}
