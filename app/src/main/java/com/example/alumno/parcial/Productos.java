package com.example.alumno.parcial;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by alumno on 09/05/2019.
 */
public class Productos implements Serializable
{
    private String id;
    private String nombre;
    private String precio;
    private String cantidad;

    public Productos() {
    }

    public Productos(String id, String nombre, String precio, String cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getPrecio()
    {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(String cantidad)
    {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                ", cantidad='" + cantidad + '\'' +
                '}';
    }

    public String toJSON(){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("id",getId());
            jsonObject.put("nombre", getNombre());
            jsonObject.put("precio", getPrecio());
            jsonObject.put("cantidad", getCantidad());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }
}
