package com.example.control_estacionamiento_utec_electiva_i;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.docentes.Estacionamiento;
import com.example.control_estacionamiento_utec_electiva_i.docentes.PantallaInicio;

public class PopActivity extends Activity {

    private final int DURACION_SPLASH = 3000; // 3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);


        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad PantallaInicio de la aplicación
                Intent intent = new Intent(PopActivity.this, PantallaInicio.class);
                startActivity(intent);
                Toast.makeText(PopActivity.this, "Solicitud exitosa", Toast.LENGTH_SHORT).show();
                finish();
            };
        }, DURACION_SPLASH);
    }
}
