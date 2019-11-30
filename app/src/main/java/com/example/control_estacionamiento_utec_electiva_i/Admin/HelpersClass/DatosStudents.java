package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import java.util.ArrayList;

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
   public static int getCountOfStudents(){
        return Studentname.size();
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
}
