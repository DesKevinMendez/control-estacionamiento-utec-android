package com.example.control_estacionamiento_utec_electiva_i.docentes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Login.Login;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;


public class PopConfirm extends Activity {

    Button btnConfirmar, btnCancelar;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_confirm);

        btnCancelar = findViewById(R.id.btnCancelarPop);
        btnConfirmar = findViewById(R.id.btnConfirmarPop);

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

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopConfirm.this, "Acción Cancelada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Establece la sesion de usuario con falso, y limpia la data del usuario
                user.setLoggedUser(false);
                
                user.setDataUser(0, null, null, null, null,
                        null, 0, 0, null);

                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                Toast.makeText(PopConfirm.this, "Se guardó clave nueva", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
