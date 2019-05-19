package com.example.alumno.parcial;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by alumno on 09/05/2019.
 */

public class MyThread extends Thread
{

    String url;
    Handler h;

    public MyThread(String url, Handler h) {
        this.url = url;
        this.h = h;
    }

    public void run()
    {
        Message m = new Message();
        try {
            String s = MiConnec.traer(url);
            Log.d("ASDASD",s);
            m.obj=XmlParser.traerXml(s);

            Log.d("PARSERR",m.obj.toString());
        } catch (IOException | XmlPullParserException e)
        {
            e.printStackTrace();
        }
        this.h.sendMessage(m);

    }

}
