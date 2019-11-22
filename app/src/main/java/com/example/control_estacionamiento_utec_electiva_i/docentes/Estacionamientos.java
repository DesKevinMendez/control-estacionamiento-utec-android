package com.example.control_estacionamiento_utec_electiva_i.docentes;

import java.util.ArrayList;

public final class Estacionamientos {

    private static ArrayList nombreEdificio = new ArrayList();
    private static ArrayList totalEstacionamiento = new ArrayList();
    private static ArrayList estacionamientosDisponibles = new ArrayList();

    public static void setInfoEdificios(String nombreEd, int totalEs,
                                 int estacionamientoD) {
        nombreEdificio.add(nombreEd);
        totalEstacionamiento.add(totalEs);
        estacionamientosDisponibles.add(estacionamientoD);
    }

    public static String getEdificioDisponible(int i){
         return estacionamientosDisponibles.get(i).toString();
    }

    public static ArrayList getNombreEdificio(){ return nombreEdificio; }

    public static int getTotalEstacionamiento() { return totalEstacionamiento.size(); }

    public static ArrayList getEstacionamientosDisponibles() { return estacionamientosDisponibles; }
}
