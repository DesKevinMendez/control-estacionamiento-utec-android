package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos;


import android.util.Log;

import java.util.ArrayList;

public final class DatosVigilante  {
    private static ArrayList IdEdificios = new ArrayList();
    private static ArrayList edificios = new ArrayList();
    private static ArrayList estacionamientoDisponible = new ArrayList();
    private static ArrayList totalEstacionamiento = new ArrayList();

    public static void setInfoEdificio(String edificio, int totalEstac, int disponibleEsta, int idEdificio) {

        edificios.add(edificio);
        totalEstacionamiento.add(totalEstac);
        estacionamientoDisponible.add(disponibleEsta);
        IdEdificios.add(idEdificio);
    }


    public static ArrayList dataBuilding() {
        return edificios;
    }

    public static ArrayList dataEstaDispo() {
        return estacionamientoDisponible;
    }

    public static ArrayList dataTotalEsta() {
        return totalEstacionamiento;
    }

    public static String getEdificioDisponible(int i){
        return estacionamientoDisponible.get(i).toString();
    }

    private  static String Nombre="", Apellido="", Placa="", EdificioAsignado="", HoraEntrada="", HoraSalida="";
    private static int IdUser=0, IdEdificio=0;

    public static void setUsuarioPlaca(String nombre, String apellido, String placa,String edificioAsignado,String horaEntrada, String horaSalida , int idUser, int idEdificio){
        Nombre=nombre;
        Apellido=apellido;
        Placa=placa;
        EdificioAsignado= edificioAsignado;
        HoraEntrada=horaEntrada;
        HoraSalida=horaSalida;

        IdUser = idUser;
        IdEdificio=idEdificio;


    }



    public static String getNombreUser (){ return Nombre;}
    public static String getApellidoUser (){return Apellido;}
    public static String getPlacaUser (){return Placa;}
    public static String getEdificioAsignadoUser (){return EdificioAsignado;}
    public static String getHoraEntradaUser (){return HoraEntrada;}
    public static String getHoraSalidaUser (){return HoraSalida;}
    public static int getIdUserUser (){return IdUser;}
    public static int getIdEdificioUser (){return IdEdificio;}

}
