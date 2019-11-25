package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

public final class DatosBuilding {

    private static ArrayList edificios = new ArrayList();
    private static ArrayList IdEdificios = new ArrayList();
    private static ArrayList totalEstacionamiento = new ArrayList();
    private static ArrayList estacionamientoDisponible = new ArrayList();

    private static ArrayList filterEdificios = new ArrayList();
    private static ArrayList filterTotalEstacionamiento = new ArrayList();
    private static ArrayList filterEstacionamientoDisponible = new ArrayList();

    private static String buildingSelected = "";

    private static String buildingIdSelected = "";

    public static String getBuildingIdSelected() {
        return buildingIdSelected;
    }

    public static void setBuildingIdSelected(int vigilanteIDSelected) {
        DatosBuilding.buildingIdSelected = IdEdificios.get(vigilanteIDSelected).toString();
    }

    public static int getTotalEdificios() {
        return edificios.size();
    }

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


    public static ArrayList getFilterBuilding(String value) {
        setClearFilter();

        ArrayList<String> buildingFilter = new  ArrayList(edificios);
        for (Iterator<String> it = buildingFilter.iterator(); it.hasNext();) {
            if (!it.next().contains(value))
                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
        }

        for (String edificio : buildingFilter) {
            filterEdificios.add(edificio);
            filterTotalEstacionamiento.add(totalEstacionamiento.get(edificios.indexOf(edificio)).toString());
            filterEstacionamientoDisponible.add(estacionamientoDisponible.get(edificios.indexOf(edificio)).toString());
        }

        return buildingFilter;
    }

    public static ArrayList getFilterTotalEstacionamiento() {
        return filterTotalEstacionamiento;
    }

    public static ArrayList getFilterEstacionamientoDisponible() {
        return filterEstacionamientoDisponible;
    }

    public static void setClearFilter() {
        filterEdificios.clear();
        filterTotalEstacionamiento.clear();
        filterEstacionamientoDisponible.clear();
        IdEdificios.clear();
    }
    public static void setBuildingSelected(int value) {
        if (value == -1) {
            buildingSelected = "";
        } else if(filterEdificios.size() != 0 ) {

            buildingSelected = filterEdificios.get(value).toString();
            setClearFilter();

        } else {

            buildingSelected = edificios.get(value).toString();
        }
    }
    public static String getBuildingSelected() {
        return buildingSelected;
    }
}
