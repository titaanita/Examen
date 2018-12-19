package com.example.xabie.examen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class activityDos extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 50;
    private boolean flashLightStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);
        final String logeado = getString(R.string.logeado);
        TextView txtSaludo = (TextView) findViewById(R.id.txtSaludo);
        ImageButton btnLinterna = (ImageButton) findViewById(R.id.btnLinterna);
        ImageButton btnBatman = (ImageButton) findViewById(R.id.btnBatman);
        ImageButton btnMapa = (ImageButton) findViewById(R.id.btnMapa);
        ImageButton btnVolver = (ImageButton) findViewById(R.id.btnVolver);
        final boolean hasCameraFlash = getPackageManager().
                hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        Toast.makeText(activityDos.this, logeado, Toast.LENGTH_LONG).show();

        //Recoger el valor del campo Extra
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null && !bundle.isEmpty()) {
            String s = bundle.getString("Saludo");
            txtSaludo.setText("Hola " +s);
        }

        btnLinterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraFlash) {
                    if (flashLightStatus)
                        flashLightOff();
                    else
                        flashLightOn();
                } else {
                    Toast.makeText(activityDos.this, "No hay flash en el dispositivo",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:42.8248494,-1.6602665");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        btnBatman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://es.wikipedia.org/wiki/Batman";
                Intent e = new Intent(Intent.ACTION_VIEW);
                e.setData(Uri.parse(url));
                startActivity(e);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(activityDos.this, Formulario.class));
                finish();
            }
        });

    }

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            flashLightStatus = true;
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 400 milliseconds
            v.vibrate(400);

        } catch (CameraAccessException e) {
        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            flashLightStatus = false;
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 400 milliseconds
            v.vibrate(400);
        } catch (CameraAccessException e) {
        }
    }
}
