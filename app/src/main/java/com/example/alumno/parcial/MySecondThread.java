package com.example.alumno.parcial;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MySecondThread extends Thread
{
    String url;
    Handler h;

    public MySecondThread(String url, Handler h) {
        this.url = url;
        this.h = h;
    }

    public void run()
    {
        Message m = new Message();

        try {
            String s = MiConnec.traer(url);
            m.obj=s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Log.d("ASDASD",s);
        //    m.obj=XmlParser.traerXml(s);

        this.h.sendMessage(m);

    }
}
