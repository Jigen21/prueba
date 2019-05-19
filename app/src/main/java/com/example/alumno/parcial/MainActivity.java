package com.example.alumno.parcial;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements Handler.Callback , MyOnItemListener , Serializable{

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private static final int THIRD_ACTIVITY_REQUEST_CODE = 0;

    List<Productos> productos = new ArrayList<Productos>();

    MyOnItemListener listener;
    MyAdapter adapter;
    Button agregar;
    Button convertir;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView rv = (RecyclerView)findViewById(R.id.lista);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(this.productos,this,this );
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
       // adapter.notifyDataSetChanged();

        agregar = (Button)findViewById(R.id.agregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),Main3Activity.class);
                //startActivity(intent);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });

        convertir = (Button)findViewById(R.id.convertir);
        convertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                try {
                    makJsonObject();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });






        Handler h= new Handler(this);
        //MyThread mt = new MyThread("http://192.168.2.167:8080/a/productos.xml",h);
        MyThread mt = new MyThread("http://192.168.0.20/Labov/productos.xml",h);
        mt.start();

       /* Productos p = new Productos("2","ads","ads","dsa");
        Productos p2 = new Productos("2","ads","ads","dsa");
        this.productos.add(p);
        this.productos.add(p2);



        RecyclerView rv = (RecyclerView)findViewById(R.id.lista);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        MyAdapter adapter = new MyAdapter(this.productos);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);

        */


    }

    @Override
    public boolean handleMessage(Message msg)
    {

        Log.d("LLEGUE","ASd");

        /*RecyclerView rv = (RecyclerView)findViewById(R.id.lista);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        MyAdapter adapter = new MyAdapter((List<Productos>)msg.obj,this );
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/


        this.productos=(List<Productos>)msg.obj;

        adapter.setProductos((List<Productos>)msg.obj);
        adapter.notifyDataSetChanged();


        return true;
    }

    @Override
    public void OnClickListener(int position,View v)
    {
            int i;
        if(v.getId()==R.id.sumar)
        {
            Log.d("ENTRE","G");
            i=Integer.valueOf(this.productos.get(position).getCantidad());
           // i=Integer.valueOf(adapter.getProductos().get(position).getCantidad());
            i=i+1;

           this.productos.get(position).setCantidad(String.valueOf(i));
            //this.adapter.getProductos().get(position).setCantidad(String.valueOf(i));
            adapter.notifyItemChanged(position);

            //this.productos.add(new Productos("asd","fds","gfdg","sdasad"));
            //adapter.notifyDataSetChanged();


        }

        if(v.getId()==R.id.restar)
        {
            i=Integer.valueOf(this.productos.get(position).getCantidad());
            i=i-1;
            if(i>=0)
            {

                this.productos.get(position).setCantidad(String.valueOf(i));
            }

            adapter.notifyItemChanged(position);




        }

        if(v.getId()==R.id.borrar)
        {



                this.productos.remove(position);
                adapter.notifyDataSetChanged();



        }

        if(v.getId()==R.id.detalles)
        {



           /* Productos a = this.productos.get(position);
            Intent intent = new Intent(getBaseContext(),Main2Activity.class);
            intent.putExtra("clave",a);
            startActivity(intent);*/

            Productos a = this.productos.get(position);
            Intent intent = new Intent(getBaseContext(),Main2Activity.class);
            intent.putExtra("clave",a);
            intent.putExtra("position",position);
            startActivityForResult(intent, THIRD_ACTIVITY_REQUEST_CODE);





        }

      //  Log.d("Click","click");
        //el error era este
       // Log.d("click",this.productos.get(position).getNombre());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                //String returnString = data.getStringExtra("keyName");
                Productos prueba = (Productos) data.getSerializableExtra("keyName");

                this.productos.add(prueba);
                adapter.notifyDataSetChanged();
                // Set text view with string
                //TextView textView = (TextView) findViewById(R.id.textView);
                //textView.setText(returnString);
                //Log.d("traido",returnString);
            }
        }

        if (requestCode == THIRD_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {


                Productos prueba = (Productos) data.getSerializableExtra("valor");
                int position = data.getIntExtra("position",0);

                this.productos.get(position).setNombre(prueba.getNombre());
                this.productos.get(position).setCantidad(prueba.getCantidad());
                this.productos.get(position).setPrecio(prueba.getPrecio());
                this.adapter.notifyItemChanged(position);


               // adapter.notifyItemChanged(i);

            }
        }




    }


    public void makJsonObject()
            throws JSONException {
        JSONObject obj = null;
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < this.productos.size(); i++) {
            obj = new JSONObject();
            try {
                obj.put("nombre", this.productos.get(i).getNombre());
                obj.put("cantidad", this.productos.get(i).getCantidad());
                obj.put("precio", this.productos.get(i).getPrecio());
                obj.put("id", this.productos.get(i).getId());


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(obj);
        }

        JSONObject finalobject = new JSONObject();
        finalobject.put("productos", jsonArray);
       // return finalobject;
        Log.d("ema",finalobject.toString());
    }



}
