package com.example.control_estacionamiento_utec_electiva_i.Admin.ViewAssignParking;


import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass.DataSchedule;
import com.example.control_estacionamiento_utec_electiva_i.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectedScheduleForAssignParking extends Fragment implements View.OnClickListener {


    public SelectedScheduleForAssignParking() {
        // Required empty public constructor
    }


    EditText hora_entrada_lunes, hora_salida_lunes, hora_entrada_martes, hora_salida_martes,
        hora_entrada_miercoles, hora_salida_miercoles, hora_entrada_jueves, hora_salida_jueves,
        hora_entrada_viernes, hora_salida_viernes, hora_entrada_sabado, hora_salida_sabado,
        hora_entrada_domingo, hora_salida_domingo;
    Button btnAceptarHorariosAssignParking;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected_schedule_for_assign_parking, container, false);
        hora_entrada_lunes = view.findViewById(R.id.hora_entrada_lunes);
        hora_salida_lunes = view.findViewById(R.id.hora_salida_lunes);
        hora_entrada_martes = view.findViewById(R.id.hora_entrada_martes);
        hora_salida_martes = view.findViewById(R.id.hora_salida_martes);
        hora_entrada_miercoles = view.findViewById(R.id.hora_entrada_miercoles);
        hora_salida_miercoles = view.findViewById(R.id.hora_salida_miercoles);
        hora_entrada_jueves = view.findViewById(R.id.hora_entrada_jueves);
        hora_salida_jueves = view.findViewById(R.id.hora_salida_jueves);
        hora_entrada_viernes = view.findViewById(R.id.hora_entrada_viernes);
        hora_salida_viernes = view.findViewById(R.id.hora_salida_viernes);
        hora_entrada_sabado = view.findViewById(R.id.hora_entrada_sabado);
        hora_salida_sabado = view.findViewById(R.id.hora_salida_sabado);
        hora_entrada_domingo = view.findViewById(R.id.hora_entrada_domingo);
        hora_salida_domingo = view.findViewById(R.id.hora_salida_domingo);

        btnAceptarHorariosAssignParking = view.findViewById(R.id.btnAceptarHorariosAssignParking);

        hora_entrada_lunes.setOnClickListener(this);
        hora_salida_lunes.setOnClickListener(this);
        hora_entrada_martes.setOnClickListener(this);
        hora_salida_martes.setOnClickListener(this);
        hora_entrada_miercoles.setOnClickListener(this);
        hora_salida_miercoles.setOnClickListener(this);
        hora_entrada_jueves.setOnClickListener(this);
        hora_salida_jueves.setOnClickListener(this);
        hora_entrada_viernes.setOnClickListener(this);
        hora_salida_viernes.setOnClickListener(this);
        hora_entrada_sabado.setOnClickListener(this);
        hora_salida_sabado.setOnClickListener(this);
        hora_entrada_domingo.setOnClickListener(this);
        hora_salida_domingo.setOnClickListener(this);
        btnAceptarHorariosAssignParking.setOnClickListener(this);

        if (!dataSchedule.getHora_entrada_lunes()[0].isEmpty()){
            hora_entrada_lunes.setText(dataSchedule.getHora_entrada_lunes()[0]);
        }

        if (!dataSchedule.getHora_salida_lunes()[0].isEmpty()){

            hora_salida_lunes.setText(dataSchedule.getHora_salida_lunes()[0]);
        }
        

        if (!dataSchedule.getHora_entrada_martes()[0].isEmpty()){
            hora_entrada_martes.setText(dataSchedule.getHora_entrada_martes()[0]);
        }

        if (!dataSchedule.getHora_salida_martes()[0].isEmpty()){

            hora_salida_martes.setText(dataSchedule.getHora_salida_martes()[0]);
        }


        if (!dataSchedule.getHora_entrada_miercoles()[0].isEmpty()){
            hora_entrada_miercoles.setText(dataSchedule.getHora_entrada_miercoles()[0]);
        }

        if (!dataSchedule.getHora_salida_miercoles()[0].isEmpty()){

            hora_salida_miercoles.setText(dataSchedule.getHora_salida_miercoles()[0]);
        }

        if (!dataSchedule.getHora_entrada_jueves()[0].isEmpty()){
            hora_entrada_jueves.setText(dataSchedule.getHora_entrada_jueves()[0]);
        }

        if (!dataSchedule.getHora_salida_jueves()[0].isEmpty()){

            hora_salida_jueves.setText(dataSchedule.getHora_salida_jueves()[0]);
        }

        if (!dataSchedule.getHora_entrada_viernes()[0].isEmpty()){
            hora_entrada_viernes.setText(dataSchedule.getHora_entrada_viernes()[0]);
        }

        if (!dataSchedule.getHora_salida_viernes()[0].isEmpty()){

            hora_salida_viernes.setText(dataSchedule.getHora_salida_viernes()[0]);
        }


        if (!dataSchedule.getHora_entrada_sabado()[0].isEmpty()){
            hora_entrada_sabado.setText(dataSchedule.getHora_entrada_sabado()[0]);
        }

        if (!dataSchedule.getHora_salida_sabado()[0].isEmpty()){

            hora_salida_sabado.setText(dataSchedule.getHora_salida_sabado()[0]);
        }


        if (!dataSchedule.getHora_entrada_domingo()[0].isEmpty()){
            hora_entrada_domingo.setText(dataSchedule.getHora_entrada_domingo()[0]);
        }

        if (!dataSchedule.getHora_salida_domingo()[0].isEmpty()){

            hora_salida_domingo.setText(dataSchedule.getHora_salida_domingo()[0]);
        }
        
        return view;
    }

    public void showClockPickerDialog(final int inputClock) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), R.style.CustomDatePickerDialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String hora;
                        String minutos;
                        if (hourOfDay < 10){
                            hora = "0"+hourOfDay;
                        } else {
                            hora = String.valueOf(hourOfDay);
                        }

                        if (minutes< 10){
                            minutos = "0"+minutes;
                        }else {
                            minutos = String.valueOf(minutes);
                        }

                        String horaEstablecida = hora+":"+minutos;
                        switch (inputClock){
                            case R.id.hora_entrada_lunes:
                                hora_entrada_lunes.setText(horaEstablecida);
                                break;
                            case R.id.hora_salida_lunes:
                                hora_salida_lunes.setText(horaEstablecida);
                                break;
                            case R.id.hora_entrada_martes:
                                hora_entrada_martes.setText(horaEstablecida);
                                break;

                            case R.id.hora_salida_martes:
                                hora_salida_martes.setText(horaEstablecida);
                                break;

                            case R.id.hora_entrada_miercoles:
                                hora_entrada_miercoles.setText(horaEstablecida);
                                break;

                            case R.id.hora_salida_miercoles:
                                hora_salida_miercoles.setText(horaEstablecida);
                                break;

                            case R.id.hora_entrada_jueves:
                                hora_entrada_jueves.setText(horaEstablecida);
                                break;

                            case R.id.hora_salida_jueves:
                                hora_salida_jueves.setText(horaEstablecida);
                                break;

                            case R.id.hora_entrada_viernes:
                                hora_entrada_viernes.setText(horaEstablecida);
                                break;

                            case R.id.hora_salida_viernes:
                                hora_salida_viernes.setText(horaEstablecida);
                                break;

                            case R.id.hora_entrada_sabado:
                                hora_entrada_sabado.setText(horaEstablecida);
                                break;

                            case R.id.hora_salida_sabado:
                                hora_salida_sabado.setText(horaEstablecida);
                                break;

                            case R.id.hora_entrada_domingo:
                                hora_entrada_domingo.setText(horaEstablecida);
                                break;

                            case R.id.hora_salida_domingo:
                                hora_salida_domingo.setText(horaEstablecida);
                                break;
                        }
                    }
                }, 0, 0, false);
        timePickerDialog.show();
    }

    DataSchedule dataSchedule;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.hora_entrada_lunes:
                showClockPickerDialog(R.id.hora_entrada_lunes);
                break;

            case R.id.hora_salida_lunes:
                showClockPickerDialog(R.id.hora_salida_lunes);
                break;
                
            case R.id.hora_entrada_martes:
                showClockPickerDialog(R.id.hora_entrada_martes);
                break;

            case R.id.hora_salida_martes:
                showClockPickerDialog(R.id.hora_salida_martes);
                break;

            case R.id.hora_entrada_miercoles:
                showClockPickerDialog(R.id.hora_entrada_miercoles);
                break;

            case R.id.hora_salida_miercoles:
                showClockPickerDialog(R.id.hora_salida_miercoles);
                break;

            case R.id.hora_entrada_jueves:
                showClockPickerDialog(R.id.hora_entrada_jueves);
                break;

            case R.id.hora_salida_jueves:
                showClockPickerDialog(R.id.hora_salida_jueves);
                break;

            case R.id.hora_entrada_viernes:
                showClockPickerDialog(R.id.hora_entrada_viernes);
                break;

            case R.id.hora_salida_viernes:
                showClockPickerDialog(R.id.hora_salida_viernes);
                break;

            case R.id.hora_entrada_sabado:
                showClockPickerDialog(R.id.hora_entrada_sabado);
                break;

            case R.id.hora_salida_sabado:
                showClockPickerDialog(R.id.hora_salida_sabado);
                break;

            case R.id.hora_entrada_domingo:
                showClockPickerDialog(R.id.hora_entrada_domingo);
                break;

            case R.id.hora_salida_domingo:
                showClockPickerDialog(R.id.hora_salida_domingo);
                break;
            case R.id.btnAceptarHorariosAssignParking:
                String errors = "";
                String entrada_lunes = hora_entrada_lunes.getText().toString().trim();
                String salida_lunes = hora_salida_lunes.getText().toString().trim();
                String entrada_martes = hora_entrada_martes.getText().toString().trim();
                String salida_martes = hora_salida_martes.getText().toString().trim();
                String entrada_miercoles = hora_entrada_miercoles.getText().toString().trim();
                String salida_miercoles = hora_salida_miercoles.getText().toString().trim();
                String entrada_jueves = hora_entrada_jueves.getText().toString().trim();
                String salida_jueves = hora_salida_jueves.getText().toString().trim();
                String entrada_viernes = hora_entrada_viernes.getText().toString().trim();
                String salida_viernes = hora_salida_viernes.getText().toString().trim();
                String entrada_sabado = hora_entrada_sabado.getText().toString().trim();
                String salida_sabado = hora_salida_sabado.getText().toString().trim();
                String entrada_domingo = hora_entrada_domingo.getText().toString().trim();
                String salida_domingo = hora_salida_domingo.getText().toString().trim();

                if (!entrada_lunes.isEmpty() && salida_lunes.isEmpty()
                        || entrada_lunes.isEmpty() && !salida_lunes.isEmpty()){
                           
                    Toast.makeText(getContext(), "Ingrese horarios para lunes", Toast.LENGTH_SHORT).show();
                    errors = "Existe error";
                } else {
                    if (!entrada_lunes.isEmpty() && !salida_lunes.isEmpty()){
                        String[] lunesEntrada = entrada_lunes.split(":");
                        String[] lunesSalida = salida_lunes.split(":");

                        if (entrada_lunes.equals(salida_lunes)){
                            Toast.makeText(getActivity(), "Horario inválido", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else if(Integer.valueOf(lunesEntrada[0]) > Integer.valueOf(lunesSalida[0]) ||
                                lunesEntrada[0].equals(lunesSalida[0]) && Integer.valueOf(lunesEntrada[1]) > Integer.valueOf(lunesSalida[1])){
                            Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else {
                            dataSchedule.setDataHorarioLunes(entrada_lunes, salida_lunes);
                            errors = "";
                        }
                    }
                }

                
                if (!entrada_martes.isEmpty() && salida_martes.isEmpty()
                        || entrada_martes.isEmpty() && !salida_martes.isEmpty()){
                    Toast.makeText(getContext(), "Ingrese horarios para martes", Toast.LENGTH_SHORT).show();
                    errors = "Existe error";
                } else {
                    if (!entrada_martes.isEmpty() && !salida_martes.isEmpty()){
                        String[] martesEntrada = entrada_martes.split(":");
                        String[] martesSalida = salida_martes.split(":");

                        if (entrada_martes.equals(salida_martes)){
                            Toast.makeText(getActivity(), "Horario inválido", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else if(Integer.valueOf(martesEntrada[0]) > Integer.valueOf(martesSalida[0]) ||
                                martesEntrada[0].equals(martesSalida[0]) && Integer.valueOf(martesEntrada[1]) > Integer.valueOf(martesSalida[1])){
                            Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else {

                            dataSchedule.setDataHorarioMartes(entrada_martes, salida_martes);
                            errors = "";
                        }
                    }
                }

                
                if (!entrada_miercoles.isEmpty() && salida_miercoles.isEmpty()
                        || entrada_miercoles.isEmpty() && !salida_miercoles.isEmpty()){
                    Toast.makeText(getContext(), "Ingrese horarios para miércoles", Toast.LENGTH_SHORT).show();
                    errors = "Existe error";
                } else {
                    if (!entrada_miercoles.isEmpty() && !salida_miercoles.isEmpty()){
                        String[] miercolesEntrada = entrada_miercoles.split(":");
                        String[] miercolesSalida = salida_miercoles.split(":");

                        if (entrada_miercoles.equals(salida_miercoles)){
                            Toast.makeText(getActivity(), "Horario inválido", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else if(Integer.valueOf(miercolesEntrada[0]) > Integer.valueOf(miercolesSalida[0]) ||
                                miercolesEntrada[0].equals(miercolesSalida[0]) && Integer.valueOf(miercolesEntrada[1]) > Integer.valueOf(miercolesSalida[1])){
                            Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else {

                            dataSchedule.setDataHorarioMiercoles(entrada_miercoles, salida_miercoles);
                            errors = "";

                        }
                    }
                }

                
                if (!entrada_jueves.isEmpty() && salida_jueves.isEmpty()
                        || entrada_jueves.isEmpty() && !salida_jueves.isEmpty()){
                    Toast.makeText(getContext(), "Ingrese horarios para jueves", Toast.LENGTH_SHORT).show();
                    errors = "Existe error";
                } else {
                    if (!entrada_jueves.isEmpty() && !salida_jueves.isEmpty()){
                        String[] juevesEntrada = entrada_jueves.split(":");
                        String[] juevesSalida = salida_jueves.split(":");

                        if (entrada_jueves.equals(salida_jueves)){
                            Toast.makeText(getActivity(), "Horario inválido", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else if(Integer.valueOf(juevesEntrada[0]) > Integer.valueOf(juevesSalida[0]) ||
                                juevesEntrada[0].equals(juevesSalida[0]) && Integer.valueOf(juevesEntrada[1]) > Integer.valueOf(juevesSalida[1])){
                            Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else {

                            dataSchedule.setDataHorarioJueves(entrada_jueves, salida_jueves);
                            errors = "";
                        }
                    }
                }

                
                if (!entrada_viernes.isEmpty() && salida_viernes.isEmpty()
                        || entrada_viernes.isEmpty() && !salida_viernes.isEmpty()){
                    Toast.makeText(getContext(), "Ingrese horarios para viernes", Toast.LENGTH_SHORT).show();
                    errors = "Existe error";
                } else {
                    if (!entrada_viernes.isEmpty() && !salida_viernes.isEmpty()){
                        String[] viernesEntrada = entrada_viernes.split(":");
                        String[] viernesSalida = salida_viernes.split(":");

                        if (entrada_viernes.equals(salida_viernes)){
                            Toast.makeText(getActivity(), "Horario inválido", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else if(Integer.valueOf(viernesEntrada[0]) > Integer.valueOf(viernesSalida[0]) ||
                                viernesEntrada[0].equals(viernesSalida[0]) && Integer.valueOf(viernesEntrada[1]) > Integer.valueOf(viernesSalida[1])){
                            Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else {

                            dataSchedule.setDataHorarioViernes(entrada_viernes, salida_viernes);
                            errors = "";
                        }
                    }
                }

                
                if (!entrada_sabado.isEmpty() && salida_sabado.isEmpty()
                        || entrada_sabado.isEmpty() && !salida_sabado.isEmpty()){
                    Toast.makeText(getContext(), "Ingrese horarios para sábado", Toast.LENGTH_SHORT).show();
                    errors = "Existe error";
                } else {
                    if (!entrada_sabado.isEmpty() && !salida_sabado.isEmpty()){
                        String[] sabadoEntrada = entrada_sabado.split(":");
                        String[] sabadoSalida = salida_sabado.split(":");

                        if (entrada_sabado.equals(salida_sabado)){
                            Toast.makeText(getActivity(), "Horario inválido", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else if(Integer.valueOf(sabadoEntrada[0]) > Integer.valueOf(sabadoSalida[0]) ||
                                sabadoEntrada[0].equals(sabadoSalida[0]) && Integer.valueOf(sabadoEntrada[1]) > Integer.valueOf(sabadoSalida[1])){
                            Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else {

                            dataSchedule.setDataHorarioSabado(entrada_sabado, salida_sabado);
                            errors = "";
                        }
                    }
                }

                
                if (!entrada_domingo.isEmpty() && salida_domingo.isEmpty()
                        || entrada_domingo.isEmpty() && !salida_domingo.isEmpty()){
                    Toast.makeText(getContext(), "Ingrese horarios para domingo", Toast.LENGTH_SHORT).show();
                    errors = "Existe error";
                } else {
                    if (!entrada_domingo.isEmpty() && !salida_domingo.isEmpty()){
                        String[] domingoEntrada = entrada_domingo.split(":");
                        String[] domingoSalida = salida_domingo.split(":");

                        if (entrada_domingo.equals(salida_domingo)){
                            Toast.makeText(getActivity(), "Horario inválido", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else if(Integer.valueOf(domingoEntrada[0]) > Integer.valueOf(domingoSalida[0]) ||
                                domingoEntrada[0].equals(domingoSalida[0]) && Integer.valueOf(domingoEntrada[1]) > Integer.valueOf(domingoSalida[1])){
                            Toast.makeText(getActivity(), "Hora entrada debe de ser menor que la de salida", Toast.LENGTH_SHORT).show();
                            errors = "Existe error";
                        } else {

                            dataSchedule.setDataHorarioDomingo(entrada_domingo, salida_domingo);
                            errors = "";
                        }
                    }
                }


                if (errors.isEmpty()){
                    dataSchedule.setHorariosSeleccionados("");
                    getActivity().onBackPressed();
                }

                break;
                default:
                    break;

        }
    }
}
