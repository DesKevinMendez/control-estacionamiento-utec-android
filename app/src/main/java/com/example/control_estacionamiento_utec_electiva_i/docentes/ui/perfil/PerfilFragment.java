package com.example.control_estacionamiento_utec_electiva_i.docentes.ui.perfil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestAdmin;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestDocente;
import com.example.control_estacionamiento_utec_electiva_i.Login.Login;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.example.control_estacionamiento_utec_electiva_i.docentes.DocenteHome;

public class PerfilFragment extends Fragment implements View.OnClickListener {

    User user;

    EditText claveEd, confirmarEd, actualClaveEd;
    Button cancelarPerfilBtn, confirmarPerfilBtn;
    TextView tvName, tvMail, tvPass;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        tvName = root.findViewById(R.id.tvNameDocenteP);
        tvMail = root.findViewById(R.id.tvCarnetDocenteP);
        tvPass = root.findViewById(R.id.tvClaveP);

        claveEd = root.findViewById(R.id.edClave);
        confirmarEd = root.findViewById(R.id.edConfirmar);
        actualClaveEd = root.findViewById(R.id.edClaveA);

        cancelarPerfilBtn = root.findViewById(R.id.btnCancelarPerfil);
        confirmarPerfilBtn = root.findViewById(R.id.btnConfirmarPerfil);

        tvName.setText(user.getNombres() + " " + user.getApellidos());
        tvMail.setText(user.getEmail());
        //tvPass.setText(user.getClave());

        final DrawerLayout drawer = root.findViewById(R.id.drawer_layout);

        cancelarPerfilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent home = new Intent(getContext(), DocenteHome.class);
                startActivity(home);
            }
        });

        confirmarPerfilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String clave = claveEd.getText().toString().trim();
                final String confirmar = confirmarEd.getText().toString().trim();
                final String actual = actualClaveEd.getText().toString().trim();

                if (actual.isEmpty()){
                    actualClaveEd.setError("Campo Requerido");
                    actualClaveEd.requestFocus();
                } else if (clave.isEmpty()){
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
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("Confirmar cambio");
                    alert.setMessage("Está acción no se puede deshacer");
                    alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HttpRequestAdmin httpRequestAdmin = new HttpRequestAdmin();
                            httpRequestAdmin.HTTPrequesteChangePassword(getActivity(), actual,
                                    clave, confirmar);

                            actualClaveEd.setText("");
                            claveEd.setText("");
                            confirmarEd.setText("");
                            actualClaveEd.requestFocus();

                        }
                    });
                    alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            actualClaveEd.setText("");
                            claveEd.setText("");
                            confirmarEd.setText("");
                            actualClaveEd.requestFocus();
                            Toast.makeText(getActivity(), "Cancelar", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alert.create().show();
                }
            }
        });

        return root;
    }

    @Override
    public void onClick(View v) {

    }
}