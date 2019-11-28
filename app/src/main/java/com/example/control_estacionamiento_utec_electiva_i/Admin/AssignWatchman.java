package com.example.control_estacionamiento_utec_electiva_i.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosSchedule;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosTeacher;
import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DatosVigilante;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedBuilding;
import com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking.SelectedTeacher;
import com.example.control_estacionamiento_utec_electiva_i.HTTP.HttpRequestAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Models.User;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals.BASE_URL;


public class AssignWatchman extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    public AssignWatchman() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(5).setChecked(true);
    }

    Button btnAsignarVigilante, btnSeleccionarEdificio, btnDenegarAW, btnAceptarAW;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assign_watchman_admin, container, false);

        btnAsignarVigilante = view.findViewById(R.id.btnAsignarVigilante);
        btnSeleccionarEdificio = view.findViewById(R.id.btnSeleccionarEdificio);
        btnDenegarAW = view.findViewById(R.id.btnDenegarAW);
        btnAceptarAW = view.findViewById(R.id.btnAceptarAW);

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null ) {
            if (!DatosBuilding.getBuildingSelected().isEmpty()) {
                btnSeleccionarEdificio.
                        setText(DatosBuilding.getBuildingSelected());
            }
            if (!DatosVigilante.getvigilanteSelected().isEmpty()) {
                btnAsignarVigilante.
                        setText(DatosVigilante.getvigilanteSelected());
            }

        }


        btnAsignarVigilante.setOnClickListener(this);
        btnSeleccionarEdificio.setOnClickListener(this);
        btnAceptarAW.setOnClickListener(this);
        btnDenegarAW.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {

        HttpRequestAdmin httpRequestAdmin = new HttpRequestAdmin();
        switch (view.getId()){
            case R.id.btnAsignarVigilante:

                // TO DO pendiente la parte de vigilantes

                if (DatosVigilante.getTotalvigilante() == 0 ){

                    httpRequestAdmin.HTTPrequestWatchMan(getActivity(),
                            "actionOfAssignWatchman", "AssignWatchman");

                } else {
                    changeFragments(new SelectedTeacher(),
                            "actionOfAssignWatchman", "AssignWatchman");
                }

                break;

            case R.id.btnAceptarAW:

                if (btnAsignarVigilante.getText()
                        .toString().equals(getString(R.string.selectedWatchman))) {

                    Toast.makeText(getActivity(), "Debes de seleccionar un vigilante", Toast.LENGTH_SHORT).show();

                } else if (btnSeleccionarEdificio.getText()
                        .toString().equals(getString(R.string.selectedBuilding))) {

                    Toast.makeText(getActivity(), "Debes de seleccionar un parqueo", Toast.LENGTH_SHORT).show();

                } else {
                    Log.i("Id vigilante", DatosVigilante.getVigilanteIDSelected());
                    Log.i("Id Edificio", DatosBuilding.getBuildingIdSelected());

                    HTTPrequestAssignWathMan(getActivity(), DatosVigilante.getVigilanteIDSelected()
                            ,DatosBuilding.getBuildingIdSelected());
                    /*httpRequestAdmin.HTTPrequestAssignWathMan(getActivity(), DatosVigilante.getVigilanteIDSelected()
                    ,DatosBuilding.getBuildingIdSelected());*/
                    //changeFragments(new InicioAdmin());
                }
                break;
            case R.id.btnSeleccionarEdificio:

                if (DatosBuilding.getTotalEdificios() == 0){

                    httpRequestAdmin.HTTPrequestBuilding(getActivity(),
                            "actionOfAssignWatchman", "AssignWatchman");

                } else {
                    changeFragments(new SelectedBuilding(),
                            "actionOfAssignWatchman", "AssignWatchman");
                }

                break;

            case R.id.btnDenegarAW:

                changeFragments(new InicioAdmin());

                break;
        }

    }
    ProgressDialog progressDialog;
    public void HTTPrequestAssignWathMan(final Context context, String vigilante_id, String edificio_id) {

        progressDialog = new ProgressDialog(context, R.style.AlertDialogStyle);
        progressDialog.setMessage("Asignando vigilante...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        progressDialog.show();

        String url = BASE_URL+"users/asignar-vigilante-edificio?api_token="+User.getApi_token();
        Map<String, String> params = new HashMap();
        params.put("user_id", String.valueOf(vigilante_id));
        params.put("edificio_id", String.valueOf(edificio_id));

        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                changeFragments(new InicioAdmin());
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

            }
        }){
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }
        };

        queue.add(request);

    }

    public void changeFragments(Fragment fragment){
        // Establece teacherSelected y a buildingSelected como ""
        DatosTeacher.setTeacherSelected(-1);
        DatosBuilding.setBuildingSelected(-1);

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

    }

    public void changeFragments(Fragment fragment, String putStringName, String putStringDescription) {
        // Pasar datos de un fragment a otro
        Bundle datosAEnviar = new Bundle();
        datosAEnviar.putString(putStringName, putStringDescription);
        fragment.setArguments(datosAEnviar);

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.nav_host_fragment, fragment).commit();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
