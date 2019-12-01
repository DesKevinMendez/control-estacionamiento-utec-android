package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos;


import android.util.Log;

import com.example.control_estacionamiento_utec_electiva_i.Estudiante.EstudiantesDrawer;

import java.util.ArrayList;

public final class DatosVigilante  {
    private static ArrayList IdEdificios = new ArrayList();
    private static ArrayList edificios = new ArrayList();
    private static ArrayList estacionamientoDisponible = new ArrayList();
    private static ArrayList totalEstacionamiento = new ArrayList();
    private static ArrayList estacionamientoOcupado = new ArrayList();
    private static ArrayList estacionamientoReservado = new ArrayList();


    public static void setInfoEdificio(String edificio, int totalEstac, int disponibleEsta, int idEdificio, int ocupadoEsta,
                                       int reservadoEsta) {

        edificios.add(edificio);
        totalEstacionamiento.add(totalEstac);
        estacionamientoDisponible.add(disponibleEsta);
        IdEdificios.add(idEdificio);
        estacionamientoOcupado.add(ocupadoEsta);
        estacionamientoReservado.add(reservadoEsta);
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

    public static ArrayList dataEstaOcup() {
        return estacionamientoOcupado;
    }
    public static ArrayList dataEstaReser() {
        return estacionamientoReservado;
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////

    private  static String Nombre="", Apellido="", Placa="", EdificioAsignado="", HoraEntrada="", HoraSalida="";
    private static int Estado, IdUser=0, IdEdificio=0;

    public static void setUsuarioPlaca(String nombre, String apellido, String placa, Integer estado, String edificioAsignado,
                                       String horaEntrada, String horaSalida , int idUser, int idEdificio){
        Nombre=nombre;
        Apellido=apellido;
        Placa=placa;
        Estado=estado;
        EdificioAsignado= edificioAsignado;
        HoraEntrada=horaEntrada;
        HoraSalida=horaSalida;

        IdUser = idUser;
        IdEdificio=idEdificio;


    }

    public static String getNombreUser (){ return Nombre;}
    public static String getApellidoUser (){return Apellido;}
    public static String getPlacaUser (){return Placa;}
    public static Integer getEstadoUser (){return Estado;}
    public static String getEdificioAsignadoUser (){return EdificioAsignado;}
    public static String getHoraEntradaUser (){return HoraEntrada;}
    public static String getHoraSalidaUser (){return HoraSalida;}
    public static int getIdUserUser (){return IdUser;}
    public static int getIdEdificioUser (){return IdEdificio;}

///////////////////////////////////////////////////////////////////////////////////////////

    private static String nomEdificio;
    private static int numOcupado, numDispo;

    public static void llenarEdificio(String nombreE, int numeOcupados, int numeDispo){
            nomEdificio = nombreE;
            numOcupado = numeOcupados;
            numDispo = numeDispo;
    }

    public static String getNomEdificio(){return nomEdificio;}
    public static Integer getNumOcupado(){return numOcupado;}
    public static Integer getNumDispo(){return numDispo;}

////////////////////////////////////////////////////////////////////////////////////////////////

    private static ArrayList nomHistorial = new ArrayList();
    private static ArrayList ApellHistorial= new ArrayList();
    private static ArrayList placaHistorial= new ArrayList();
    private static ArrayList comHistorial= new ArrayList();

    public static void llenarHistorial(String nombreH, String apellidoH, String placaH, String comentarioH){
            nomHistorial.add(nombreH);
            ApellHistorial.add(apellidoH);
            placaHistorial.add(placaH);
            comHistorial.add(comentarioH);
    }

    public static ArrayList getNombreH(){return nomHistorial;}
    public static ArrayList getApellidoH(){return ApellHistorial;}
    public static ArrayList getPlacaH(){return  placaHistorial;}
    public static ArrayList getComentarioH(){return comHistorial;}


    ////////////////////////////////////////////////////////////////////////////////////

    private static ArrayList nombreR = new ArrayList();
    private static ArrayList apellidoR = new ArrayList();
    private static ArrayList placaR = new ArrayList();
    private static ArrayList edificioR = new ArrayList();
    private static ArrayList entradaR = new ArrayList();
    private static ArrayList salidaR = new ArrayList();
    public  static  void setInfoReservas(String nombre, String apellido, String placa, String edificio,
                                         String entrada, String salida){
        nombreR.add(nombre);
        apellidoR.add(apellido);
        placaR.add(placa);
        edificioR.add(edificio);
        entradaR.add(entrada);
        salidaR.add(salida);

    }

    public static ArrayList getNombreR(){return nombreR;}
    public static ArrayList getApellidoR(){return apellidoR;}
    public static ArrayList getPlacaR(){return placaR;}
    public static ArrayList getEdificioR(){return edificioR;}
    public static ArrayList getEntradaR(){return entradaR;}
    public static ArrayList getSalidaR(){return salidaR;}


}
