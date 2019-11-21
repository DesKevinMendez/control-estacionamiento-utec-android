package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

public final class DatosVigilante {

    private static ArrayList vigilante = new ArrayList();
    private static ArrayList Idvigilante = new ArrayList();
    private static ArrayList carnetvigilante= new ArrayList();
    private static ArrayList FilterCarnetvigilante= new ArrayList();
    private static ArrayList filtervigilante = new ArrayList();
    private static String vigilanteSelected = "";

    public static void setDatavigilante(String vigilantes, String carnet, int id) {
        vigilante.add(vigilantes);
        Idvigilante.add(id);
        if (carnet.equals(null)){
            carnetvigilante.add("No disponible");
        }else {
            carnetvigilante.add(carnet);
        }
    }
    public static ArrayList getFiltervigilantes(String value) {
        setClearFilter();

        ArrayList<String> vigilanteFilter = new  ArrayList(vigilante);
        for (Iterator<String> it = vigilanteFilter.iterator(); it.hasNext();) {
            if (!it.next().contains(value))
                it.remove(); // NOTE: Iterator's remove method, not ArrayList's, is used.
        }
        for (String vigilantes : vigilanteFilter) {
            filtervigilante.add(vigilantes);
            FilterCarnetvigilante.add(carnetvigilante.get(vigilante.indexOf(vigilantes)).toString());
            Log.i("vigilanteCOUNTS", String.valueOf(filtervigilante.size()));
        }

        return vigilanteFilter;
    }

    public static int getTotalvigilante(){
        return vigilante.size();
    }

    public static void clearDatavigilante(){
        Idvigilante.clear();
        vigilante.clear();
        carnetvigilante.clear();
    }

    public static ArrayList getFilterCarnetvigilante() {
        return FilterCarnetvigilante;
    }

    public static void setClearFilter() {
        filtervigilante.clear();
        FilterCarnetvigilante.clear();
    }
    public static  ArrayList getAllvigilante() {
        return vigilante;
    }


    public static ArrayList getAllCarnetsvigilante() {
        return carnetvigilante;
    }

    public static void setvigilanteSelected (int value) {
        Log.i("vigilanteSELECTED",  String.valueOf(value) +" " + String.valueOf(filtervigilante.size()));
        if (value == -1) {
            vigilanteSelected = "";
        } else if(filtervigilante.size() !=0 ) {

            vigilanteSelected = filtervigilante.get(value).toString();
            setClearFilter();

        } else {

            vigilanteSelected = vigilante.get(value).toString();

        }
    }

    public static String getvigilanteSelected() {
        return vigilanteSelected;
    }
}
