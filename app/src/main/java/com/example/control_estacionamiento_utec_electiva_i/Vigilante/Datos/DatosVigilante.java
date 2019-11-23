package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos;


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





}
