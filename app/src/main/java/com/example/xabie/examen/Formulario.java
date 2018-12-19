package com.example.xabie.examen;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Formulario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);

    }

    public void crear(View view){
        EditText edComentario = (EditText) findViewById(R.id.edComentario);
        Date fechaAhora = Calendar.getInstance().getTime();
        //Dar de alta usuario
        AndroidHelper admin = new AndroidHelper(this, "administracion", null, 1 );
        SQLiteDatabase db = admin.getWritableDatabase();
        //Guardar los valores que leemos de los EditText en un registro
        //Para ello creamos el registro
        ContentValues registro = new ContentValues();
        registro.put("fecha", fechaAhora.toString());
        registro.put("comentario", edComentario.getText().toString());

        //Insertar en la base de datos
        db.insert("usuario", null, registro);

        //Cerrar la base de datos
        db.close();

        //Poner los campos a vacio para insertar el siguiente usuario
        edComentario.setText("");

        Toast.makeText(this,"usuario insertado en la base de datos", Toast.LENGTH_LONG).show();
    }
}
