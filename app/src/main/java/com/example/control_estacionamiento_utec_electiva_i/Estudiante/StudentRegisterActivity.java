package com.example.control_estacionamiento_utec_electiva_i.Estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.R;

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
                        // TODO: continue with code...
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

    /**
     * Method to verify if @param editText is empty
     * @param editText
     * @return false if empty or true if not empty
     */
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
