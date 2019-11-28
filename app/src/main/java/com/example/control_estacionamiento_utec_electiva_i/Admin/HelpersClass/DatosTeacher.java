package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Iterator;

public final class DatosTeacher {
    private static ArrayList teacher = new ArrayList();
    private static ArrayList IdTeacher = new ArrayList();
    private static ArrayList carnetTeacher= new ArrayList();
    private static ArrayList FilterCarnetTeacher= new ArrayList();
    private static ArrayList filterteacher = new ArrayList();
    private static String teacherSelected = "";
    private static String teacherIdSelected = "";

   public static String getTeacherIdSelected(){
       return teacherIdSelected;
   }

   public static void setTeacherIdSelected(int id){
       teacherIdSelected = IdTeacher.get(id).toString();
   }

    public static void setDataTeacher(String teachers, String carnet, int id) {
        teacher.add(teachers);
        IdTeacher.add(id);
        if (carnet.equals(null)){
            carnetTeacher.add("No disponible");
        }else {
            carnetTeacher.add(carnet);
        }
    }
    public static ArrayList getFilterTeachers(String value) {
        setClearFilter();

        ArrayList<String> teacherFilter = new  ArrayList(teacher);
        for (Iterator<String> it = teacherFilter.iterator(); it.hasNext();) {
            if (!it.next().contains(value))
                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
        }
        for (String teachers : teacherFilter) {
            filterteacher.add(teachers);
            FilterCarnetTeacher.add(carnetTeacher.get(teacher.indexOf(teachers)).toString());
            Log.i("TEACHERCOUNTS", String.valueOf(filterteacher.size()));
        }

        return teacherFilter;
    }

    public static int getTotalTeacher(){
        return teacher.size();
    }

    public static void clearDataTeacher(){
        IdTeacher.clear();
        teacher.clear();
        carnetTeacher.clear();
    }

    public static ArrayList getFilterCarnetTeacher() {
        return FilterCarnetTeacher;
    }

    public static void setClearFilter() {
        filterteacher.clear();
        FilterCarnetTeacher.clear();
    }
    public static  ArrayList getAllTeacher() {
        return teacher;
    }


    public static ArrayList getAllCarnetsTeacher() {
        return carnetTeacher;
    }

    public static void setTeacherSelected (int value) {
        if (value == -1) {
            teacherSelected = "";
        } else if(filterteacher.size() !=0 ) {

            teacherSelected = filterteacher.get(value).toString();
            setClearFilter();

        } else {

            teacherSelected = teacher.get(value).toString();

        }
    }

    public static String getTeacherSelected() {
        return teacherSelected;
    }
}
