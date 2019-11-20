package com.example.control_estacionamiento_utec_electiva_i.docentes.ui.cerrar_sesion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.control_estacionamiento_utec_electiva_i.Login.Login;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;


public class CerrarSesionFragment extends Fragment {

    private CerrarSesionViewModel slideshowViewModel;
    User user;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cerrar_sesion_docente, container, false);
        // Establece la sesion de usuario con falso, y limpia la data del usuario
        user.setLoggedUser(false);
        
        user.setDataUser(0, null, null, null, null,
                null, 0, 0, null);
                
        Intent i = new Intent(getContext(), Login.class);
        startActivity(i);
        this.getActivity().finish();

        return root;
    }
}