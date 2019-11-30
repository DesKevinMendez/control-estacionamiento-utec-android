package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import java.util.ArrayList;
import java.util.Iterator;

public final class DatosStudents {

   public static ArrayList Studentname = new ArrayList();
   public static ArrayList StudentPlaca = new ArrayList();
   public static ArrayList StudentCarnet = new ArrayList();
   public static ArrayList StudentBuilding = new ArrayList();
   public static ArrayList StudentID = new ArrayList();

   
   public static ArrayList FilterStudentname = new ArrayList();
   public static ArrayList FilterStudentPlaca = new ArrayList();
   public static ArrayList FilterStudentCarnet = new ArrayList();
   public static ArrayList FilterStudentBuilding = new ArrayList();
   public static ArrayList FilterStudentID = new ArrayList();


    public static void setDataStudents(String nombre, String placa, String carnet,
                                       String edificio, int id){
      Studentname.add(nombre);
      StudentPlaca.add(placa);
      StudentCarnet.add(carnet);
      StudentBuilding.add(edificio);
      StudentID.add(id);

   }

   public static void setClearAllStudentsData(){
       Studentname.clear();
       StudentPlaca.clear();
       StudentCarnet.clear();
       StudentBuilding.clear();
       StudentID.clear();

   }

    public static void setClearAllFilterStudentsData(){
        FilterStudentname.clear();
        FilterStudentPlaca.clear();
        FilterStudentCarnet.clear();
        FilterStudentBuilding.clear();
        FilterStudentID.clear();

    }
   public static int getCountOfStudents(){
        return Studentname.size();
   }
    public static ArrayList getFilterStudents(String value) {
        setClearAllFilterStudentsData();

        ArrayList<String> studentsFilter = new  ArrayList(Studentname);
        for (Iterator<String> it = studentsFilter.iterator(); it.hasNext();) {
            if (!it.next().contains(value))
                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
        }


        for (String edificio : studentsFilter) {
            FilterStudentname.add(edificio);
            FilterStudentCarnet.add(StudentCarnet.get(Studentname.indexOf(edificio)).toString());
            FilterStudentPlaca.add(StudentPlaca.get(Studentname.indexOf(edificio)).toString());
            FilterStudentBuilding.add(StudentBuilding.get(Studentname.indexOf(edificio)).toString());
            FilterStudentID.add(StudentID.get(Studentname.indexOf(edificio)).toString());
        }

        return studentsFilter;
    }
    public static ArrayList getStudentname() {
        return Studentname;
    }

    public static ArrayList getStudentPlaca() {
        return StudentPlaca;
    }

    public static ArrayList getStudentCarnet() {
        return StudentCarnet;
    }

    public static ArrayList getStudentBuilding() {
        return StudentBuilding;
    }

    public static ArrayList getStudentID() {
        return StudentID;
    }

    public static ArrayList getFilterStudentname() {
        return FilterStudentname;
    }

    public static ArrayList getFilterStudentPlaca() {
        return FilterStudentPlaca;
    }

    public static ArrayList getFilterStudentCarnet() {
        return FilterStudentCarnet;
    }

    public static ArrayList getFilterStudentBuilding() {
        return FilterStudentBuilding;
    }

    public static ArrayList getFilterStudentID() {
        return FilterStudentID;
    }
}
