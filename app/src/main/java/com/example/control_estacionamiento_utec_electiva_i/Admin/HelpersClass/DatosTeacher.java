package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Iterator;

public final class DatosTeacher {
    private static ArrayList teacher = new ArrayList();
    private static ArrayList carnetTeacher= new ArrayList();
    private static ArrayList FilterCarnetTeacher= new ArrayList();
    private static String teacherSelected = "";


    static {
        teacher.add("Jorge Acevedo");
        teacher.add("Jorge Machado");
        teacher.add("Ingrid Gonzales");
        teacher.add("Jorge Aparicio");
        teacher.add("Marte cualquiera");

        carnetTeacher.add("20-3453-3453");
        carnetTeacher.add("45-3455-3454");
        carnetTeacher.add("30-3458-3455");
        carnetTeacher.add("70-3450-3456");
        carnetTeacher.add("85-3453-3457");

        for (int i = 0; i < 50; i++) {

            teacher.add("Docente " + i);
            carnetTeacher.add("2"+ i +"-3"+ i +"53-3"+ i +"53");
        }

    }

    public static ArrayList getFilterTeachers(String value) {
        ArrayList<String> teacherFilter = new  ArrayList(teacher);
        for (Iterator<String> it = teacherFilter.iterator(); it.hasNext();) {
            if (!it.next().contains(value))
                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
        }
        for (String teachers : teacherFilter) {
            FilterCarnetTeacher.add(carnetTeacher.get(teacher.indexOf(teachers)).toString());
        }

        return teacherFilter;
    }

    public static ArrayList getFilterCarnetTeacher() {
        return FilterCarnetTeacher;
    }

    public static void setInfoTeacher(String value) {
        teacher.add(value);
        carnetTeacher.add(value);
    }
    public static  ArrayList getAllTeacher() {
        return teacher;
    }


    public static ArrayList getAllCarnetsTeacher() {
        return carnetTeacher;
    }

    public static void setTeacherSelected (int value) {
        if (value == -1)
            teacherSelected = "";
         else
            teacherSelected = teacher.get(value).toString();
    }

    public static String getTeacherSelected() {
        return teacherSelected;
    }
}
