package com.example.alumno.parcial;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity
{

    Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        Button enviar = (Button)findViewById(R.id.enviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getBaseContext(),MainActivity.class);
               // startActivity(intent);

                EditText editText = (EditText) findViewById(R.id.editText);
                EditText editText2 = (EditText) findViewById(R.id.editText2);
                EditText editText3 = (EditText) findViewById(R.id.editText3);
                EditText editText4 = (EditText) findViewById(R.id.editText4);
                String id = editText.getText().toString();
                String nombre = editText2.getText().toString();
                String precio = editText3.getText().toString();
                String cantidad = editText4.getText().toString();

                Productos p = new Productos(id,nombre,precio,cantidad);




                Intent resultIntent = new Intent();

                //resultIntent.putExtra("keyName", "String data");
                resultIntent.putExtra("keyName", p);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });



    }
}
