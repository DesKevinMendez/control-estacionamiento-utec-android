package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

public final class DatosTeacher {
    private static ArrayList teacher = new ArrayList();
    private static ArrayList carnetTeacher= new ArrayList();

    static {
        teacher.add("Jorge Acevedo");
        teacher.add("Jorge Machado");
        teacher.add("Ingrid Gonzales");
        teacher.add("Jorge Aparicio");
        teacher.add("Marte cualquiera");

        carnetTeacher.add("20-3453-3453");
        carnetTeacher.add("25-3453-3454");
        carnetTeacher.add("30-3453-3455");
        carnetTeacher.add("10-3453-3456");
        carnetTeacher.add("15-3453-3457");

        for (int i = 0; i < 50; i++) {

            teacher.add("Docente " + i);
            carnetTeacher.add("2"+ i +"-3"+ i +"53-3"+ i +"53");
        }

    }

    public static  ArrayList getAllTeacher() {
        return teacher;
    }

    public static ArrayList getAllCarnetsTeacher() {
        return carnetTeacher;
    }
}
