package com.example.control_estacionamiento_utec_electiva_i.Estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.control_estacionamiento_utec_electiva_i.Admin.InicioAdmin;
import com.example.control_estacionamiento_utec_electiva_i.Login.Login;
import com.example.control_estacionamiento_utec_electiva_i.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.example.control_estacionamiento_utec_electiva_i.Interfaces.Globals.BASE_URL;

public class StudentRegisterActivity extends AppCompatActivity {

    EditText edtName
            ,edtSurname
            ,edtMail
            ,edtCarnet
            ,edtPlaca
            ,edtPass
            ,edtConfirmPass;

    Button btnCancel
            ,btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        setTitle("Registro de estudiante");

        edtName = findViewById(R.id.edtNameRegister);
        edtSurname = findViewById(R.id.edtSurnameRegister);
        edtMail = findViewById(R.id.edtMailRegister);
        edtCarnet = findViewById(R.id.edtCarneRegister);
        edtPlaca = findViewById(R.id.edtPlacaRegister);
        edtPass = findViewById(R.id.edtPassRegister);
        edtConfirmPass = findViewById(R.id.edtPassConfirmRegister);

        btnCancel = findViewById(R.id.btnCancelarRegisterStudent);
        btnConfirm = findViewById(R.id.btnRegisterStudent);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmptyFields(edtName)
                        && isEmptyFields(edtSurname)
                        && isEmptyFields(edtMail)
                        && isEmptyFields(edtCarnet)
                        && isEmptyFields(edtPlaca)
                        && isEmptyFields(edtPass)
                        && isEmptyFields(edtConfirmPass)) {

                    // Strings all EditText
                    String name = edtName.getText().toString().trim();
                    String surname = edtSurname.getText().toString().trim();
                    String mail = edtMail.getText().toString().trim();
                    String carnet = edtCarnet.getText().toString().trim();
                    String placa = edtPlaca.getText().toString().trim();
                    String pass = edtPass.getText().toString().trim();
                    String confirm = edtConfirmPass.getText().toString().trim();

                    // validate that the passwords match
                    if (!pass.equals(confirm)) {
                        Toast.makeText(StudentRegisterActivity.this,
                                "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        edtPass.setText("");
                        edtConfirmPass.setText("");
                        edtPass.requestFocus();
                    } else {
                        String regex = "^[0-9]{2}(?:-[0-9]{4})(?:-[0-9]{4})*$";
                        boolean isRegulated = Pattern.matches(regex, carnet);
                        if (!isRegulated) {
                            Toast.makeText(StudentRegisterActivity.this,
                                    "Formato de carnet inválido", Toast.LENGTH_SHORT).show();
                            edtCarnet.requestFocus();
                        } else {
                            HTTPRegisterStudent(name, surname, mail, carnet, placa, pass, confirm);

                        }
                    }
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    ProgressDialog progressDialog;

    public void HTTPRegisterStudent(final String name, final String surname,
                                    final String mail, final String carnet, final String placa,
                                    final String pass, final String confirm) {
        progressDialog = new ProgressDialog(this, R.style.AlertDialogStyle);
        progressDialog.setMessage("Registrándose...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = BASE_URL+"register?rol_id=4&api_token=DNumbm6MXjORx7sW6eZRgVgtmX9YJDkroT9Nk3aYTSgVMaRDW7Jmx88OSKROYuA0NkIT3IsJ11xm6zaA";

        Map<String, String> params = new HashMap();
        params.put("nombres", name);
        params.put("apellidos", surname);
        params.put("email", mail);
        params.put("carnet", carnet);
        params.put("num_placa", placa);
        params.put("password", pass);
        params.put("password_confirmation", confirm);

        JSONObject parameters = new JSONObject(params);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("RESPONSE", response.toString());
                Toast.makeText(getApplicationContext(), "Has sido registrado; favor, inicia sesión", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Intent login = new Intent(StudentRegisterActivity.this, Login.class);
                startActivity(login);
                finish();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ERROR", error.toString());
                progressDialog.dismiss();

                NetworkResponse networkResponse = error.networkResponse;
                Log.i("ERROR", networkResponse.data.toString());

                if (networkResponse != null && networkResponse.statusCode == 422) {
                    Toast.makeText(StudentRegisterActivity.this, "Datos inválidos", Toast.LENGTH_SHORT).show();
                }
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(),
                            "Tiempo de conexión excedido",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {

                    Toast.makeText(getApplicationContext(), "Sin conexión a internet", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "Error de parcing", Toast.LENGTH_SHORT).show();
                }

            }
        });

        queue.add(request);

    }

    public boolean isEmptyFields(EditText editText) {
        String stringText = editText.getText().toString().trim();
        if (stringText.isEmpty()) {
            editText.setError("Campo Requerido");
            editText.requestFocus();
            return false;
        } else
            return true;
    }
}
