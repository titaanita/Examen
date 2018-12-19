package com.example.xabie.examen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final CheckBox cboRecordar = (CheckBox) findViewById(R.id.cboRecuerda);
        final EditText edNombre = (EditText) findViewById(R.id.edName);
        final EditText edContrasena = (EditText) findViewById(R.id.edPass);
        final String nologeadocontra = getString(R.string.nologeadocontra);
        final String nologeadonombre = getString(R.string.nologeadonombre);
        edNombre.requestFocus();
        final SharedPreferences prefs;
        prefs = getSharedPreferences("FitxPreferences",MODE_PRIVATE);
        if (prefs != null){
            edNombre.setText(prefs.getString("Nombre", ""));
            edContrasena.setText(prefs.getString("Contrasena", ""));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edNombre.getText().toString();
                if(!edNombre.getText().toString().isEmpty()) {
                    if (!edContrasena.getText().toString().isEmpty()) {
                        if (cboRecordar.isChecked() == true) {
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("Nombre", edNombre.getText().toString());
                            editor.putString("Contrasena", edContrasena.getText().toString());
                        } else {
                            edNombre.setText("");
                            edContrasena.setText("");
                        }

                        Intent i = new Intent(MainActivity.this, activityDos.class);
                        i.putExtra("Saludo", nombre);
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, nologeadocontra, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, nologeadonombre, Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
