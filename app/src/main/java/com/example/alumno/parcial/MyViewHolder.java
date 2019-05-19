package com.example.alumno.parcial;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by alumno on 09/05/2019.
 */
public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener
{

    TextView nombre;
    TextView cantidad;
    TextView precio;
    MyOnItemListener listener;
    int position;
    ImageView sumar;
    ImageView restar;
    ImageView borrar;
    ImageView detalles;




    public MyViewHolder(View itemView,MyOnItemListener listener)
    {
        super(itemView);
        nombre=(TextView)itemView.findViewById(R.id.nombre);
        cantidad=(TextView)itemView.findViewById(R.id.cantidad);
        precio=(TextView)itemView.findViewById(R.id.precio);
        sumar=(ImageView)itemView.findViewById(R.id.sumar);
        restar=(ImageView)itemView.findViewById(R.id.restar);
        borrar=(ImageView)itemView.findViewById(R.id.borrar);
        detalles=(ImageView)itemView.findViewById(R.id.detalles);
        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        borrar.setOnClickListener(this);
        detalles.setOnClickListener(this);
        this.listener=listener;

        itemView.setOnClickListener(this);
       // listener.OnClickListener(position);

    }

    public void setPosition(int position)
    {
        this.position=position;
    }


    @Override
    public void onClick(View v)
    {
        this.listener.OnClickListener(position,v);
       // this.listener.

    }
}
