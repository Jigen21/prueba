package com.example.alumno.parcial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alumno on 09/05/2019.
 */

public class MiConnec
{
    public static String traer(String s) throws IOException {

        URL url = new URL(s);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int res = connection.getResponseCode();

        if(res==200)
        {
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] aux = new byte[1024];
            int leng;

            while((leng=is.read(aux))!=-1)
            {
                baos.write(aux,0,leng);

            }

            is.close();

            String respuesta = new String(baos.toByteArray());


            return respuesta;

        }

            return s;

    }



}
