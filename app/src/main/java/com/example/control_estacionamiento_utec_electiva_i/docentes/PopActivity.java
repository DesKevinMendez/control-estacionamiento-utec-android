package com.example.control_estacionamiento_utec_electiva_i.docentes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;

public class PopActivity extends Activity {

    private final int DURACION_POP = 3000; // 3 segundos

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

        Bundle datosEdificios = getIntent().getExtras();
        final String edificio = datosEdificios.getString("edificio");

        new Handler().postDelayed(new Runnable(){
            public void run(){
                // Cuando pasen los 3 segundos, pasamos a la actividad PantallaInicio de la aplicación
                Intent intent = new Intent(PopActivity.this, PantallaInicio.class);
                intent.putExtra("estado", "validado");
                intent.putExtra("edificio", edificio);
                startActivity(intent);
                Toast.makeText(PopActivity.this, "Solicitud exitosa", Toast.LENGTH_SHORT).show();
                finish();
            };
        }, DURACION_POP);
    }
}
