package com.example.alumno.parcial;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements Serializable , Handler.Callback {

    List<formulas> f = new ArrayList<>();
    formulas formulita=null;

    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       // List<formulas> f = new ArrayList<>();
      //  formulas formulita=null;
        //o get intent
      /*  Intent i = new Intent();
        i.getStringExtra("clave");
        Log.d("jkl",i);*/
       // String id = getIntent().getStringExtra("clave");
        Productos a = (Productos)getIntent().getSerializableExtra("clave");
        TextView t = (TextView)findViewById(R.id.textView);
        TextView t2 = (TextView)findViewById(R.id.textView2);
        TextView t3 = (TextView)findViewById(R.id.textView3);
        TextView t4 = (TextView)findViewById(R.id.textView4);
        t.setText(a.getNombre());
        t2.setText(a.getCantidad());
        t3.setText(a.getId());
        t4.setText(a.getPrecio());

        Log.d("json",a.toJSON());

        Productos b = null;

        //ESCRIBIENDOLO EN JSOn
        try {
            File file = new File( getFilesDir() + "/probando" + ".json");
            Log.d("asd",file.getPath());
            Writer writer = new BufferedWriter(new FileWriter(file));
            writer.write(a.toJSON());
            writer.close();

        } catch (Exception e) {
            Log.e("File Exception: ", e.getMessage());
        }

        //LEYENDOLO EN JSON
        try
        {
            b = new Productos();
            JSONObject jsonObject = new JSONObject(dos());
            String id = jsonObject.getString("id");
            String nombre = jsonObject.getString("nombre");
            String precio = jsonObject.getString("precio");
            String cantidad = jsonObject.getString("cantidad");
            b.setNombre(nombre);
            b.setCantidad(cantidad);
            b.setPrecio(precio);
            b.setId(id);
            Log.d("bueno",b.toString());
           // JSONArray probando = jsonObject.getJSONArray("formules");

         /*   for(int i = 0;i<probando.length();i++)
            {
                JSONObject formula = probando.getJSONObject(i);
                String id = formula.getString("id");
                String nombre = formula.getString("nombre");
                String precio = formula.getString("precio");
                String cantidad = formula.getString("cantidad");
                Log.d("bueno",nombre);

            }*/
          //  Log.d("esa",f.toString());
        }
        catch (Exception e)
        {
            Log.e("File Exception: ", e.getMessage());
        }


        try
        {
            //MiConnec mc = new MiConnec();
           // String s=MiConnec.traer("http://192.168.0.20/Labov/yourfilename.json");
            //Log.d("Silolei","s");
           // Log.d("dale",s);
            Handler h = new Handler(this);
            MySecondThread mt = new MySecondThread("http://192.168.0.20/Labov/yourfilename.json",h);
            mt.start();



        }
        catch(Exception e)
        {
            Log.d("No se pudo leer","asd");
        }




        //Leyendolo del interno
/*
        try
        {
            formulita = new formulas();
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray formulass = jsonObject.getJSONArray("formules");
            for(int i = 0;i<formulass.length();i++)
            {
                JSONObject formula = formulass.getJSONObject(i);
                String formule = formula.getString("formule");
                String url = formula.getString("url");
                formulita.setFormule(formule);
                formulita.setQty(url);
                f.add(formulita);
                //Log.d("dios",formule);
                //Log.d("esados",formulita.toString());



            }
            Log.d("esa",f.toString());
        }
        catch (Exception e)
        {
            Log.e("File Exception: ", e.getMessage());
        }



*/

            editText = (EditText) findViewById(R.id.editTextt);
            editText2 = (EditText) findViewById(R.id.editText22);
            editText3 = (EditText) findViewById(R.id.editText33);
            editText4 = (EditText) findViewById(R.id.editText44);
            editText.setText(a.getId());
            editText2.setText(a.getNombre());
            editText3.setText(a.getPrecio());
            editText4.setText(a.getCantidad());

            Button modificar = (Button)findViewById(R.id.modificar);
            modificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String id = editText.getText().toString();
                    String nombre = editText2.getText().toString();
                    String precio = editText3.getText().toString();
                    String cantidad = editText4.getText().toString();

                    Productos p = new Productos(id,nombre,precio,cantidad);

                    int position = getIntent().getIntExtra("position",0);

                    Intent resultIntent = new Intent();

                    //resultIntent.putExtra("keyName", "String data");
                    resultIntent.putExtra("valor", p);
                    resultIntent.putExtra("position",position);
                    setResult(Activity.RESULT_FIRST_USER, resultIntent);
                    finish();
                }
            });


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("yourfilename.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public String dos() {
        String json = null;
        try {

            InputStream is =  openFileInput("probando.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public boolean handleMessage(Message msg)
    {
        Log.d("leido",msg.obj.toString());

        try
        {
            formulita = new formulas();
            JSONObject jsonObject = new JSONObject(msg.obj.toString());
            JSONArray formulass = jsonObject.getJSONArray("formules");
            for(int i = 0;i<formulass.length();i++)
            {
                JSONObject formula = formulass.getJSONObject(i);
                String formule = formula.getString("formule");
                String url = formula.getString("url");
                formulita.setFormule(formule);
                formulita.setQty(url);
                f.add(formulita);
                //Log.d("dios",formule);
                //Log.d("esados",formulita.toString());

            }
            Log.d("esa",f.toString());

        }catch(Exception e)
        {
            Log.d("error","g");
        }


        return true;
    }
}
