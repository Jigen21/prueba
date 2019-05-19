package com.example.alumno.parcial;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 09/05/2019.
 */

public class XmlParser
{
    public static List<Productos> traerXml(String s) throws XmlPullParserException, IOException {

        List<Productos> productos = new ArrayList<Productos>();
        XmlPullParser parser = Xml.newPullParser();

        String nombre="";
        String precio="";
        String cantidad="";
        String id="";
        Productos p=null;


        parser.setInput(new StringReader(s));

        int event=parser.getEventType();

        while(event!=XmlPullParser.END_DOCUMENT)
        {
            switch(event)
            {
                case XmlPullParser.START_TAG:

                    if("producto".equals(parser.getName()))
                    {
                        p = new Productos();

                    }

                    if("id".equals(parser.getName()))
                    {
                        if(p!=null)
                        {
                            p.setId(parser.nextText());
                        }

                    }
                    if("nombre".equals(parser.getName()))
                    {
                        if(p!=null)
                        {
                            p.setNombre(parser.nextText());
                        }


                    }
                    if("precio".equals(parser.getName()))
                    {
                        if(p!=null)
                        {
                            p.setPrecio(parser.nextText());
                        }


                    }
                    if("cantidad".equals(parser.getName()))
                    {
                        if(p!=null)
                        {
                            p.setCantidad(parser.nextText());
                        }

                    }

                    break;

                case XmlPullParser.END_TAG:

                    if("producto".equals(parser.getName()))
                    {
                        productos.add(p);

                    }

                    break;

            }


            event=parser.next();


        }

        return productos;


    }

}
