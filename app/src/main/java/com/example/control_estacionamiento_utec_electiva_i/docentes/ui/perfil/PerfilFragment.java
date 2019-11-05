package com.example.control_estacionamiento_utec_electiva_i.docentes.ui.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.docentes.PopConfirm;
import com.example.control_estacionamiento_utec_electiva_i.docentes.ui.home.HomeFragment;

public class PerfilFragment extends Fragment implements View.OnClickListener {

    EditText claveEd, confirmarEd;
    Button cancelarPerfilBtn, confirmarPerfilBtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        claveEd = root.findViewById(R.id.edClave);
        confirmarEd = root.findViewById(R.id.edConfirmar);

        cancelarPerfilBtn = root.findViewById(R.id.btnCancelarPerfil);
        confirmarPerfilBtn = root.findViewById(R.id.btnConfirmarPerfil);


        final DrawerLayout drawer = root.findViewById(R.id.drawer_layout);

        cancelarPerfilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getFragmentManager().popBackStack();
                HomeFragment fragment = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.nav_host_fragment, fragment).commit();
            }
        });

        confirmarPerfilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String clave = claveEd.getText().toString().trim();
                String confirmar = confirmarEd.getText().toString().trim();

                if (clave.isEmpty()){
                    claveEd.setError("Campo Requerido");
                    claveEd.requestFocus();
                } else if (confirmar.isEmpty()){
                    confirmarEd.setError("Campo Requerido");
                    confirmarEd.requestFocus();
                } else if (!clave.equals(confirmar)){
                    claveEd.setText("");
                    confirmarEd.setText("");
                    Toast.makeText(getContext(), "Las claves no son iguales", Toast.LENGTH_SHORT).show();
                    claveEd.requestFocus();
                } else{
                    // Inicia el popup
                    Intent i = new Intent(getContext(), PopConfirm.class);
                    startActivity(i);
                }
            }
        });

        return root;
    }

    @Override
    public void onClick(View v) {

    }
}