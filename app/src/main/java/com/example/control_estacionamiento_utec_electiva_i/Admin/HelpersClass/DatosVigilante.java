package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

public final class DatosVigilante {

    private static ArrayList vigilante = new ArrayList();
    private static ArrayList Idvigilante = new ArrayList();
    private static ArrayList filtervigilante= new ArrayList();
    private static ArrayList FilterEstacionaminetoAsignado= new ArrayList();
    private static ArrayList FilterIdVigilante = new ArrayList();
    private static ArrayList filterAliasEstacionaminetoAsignado = new ArrayList();
    private static ArrayList EstacionaminetoAsignado = new ArrayList();
    private static ArrayList AliasEstacionaminetoAsignado = new ArrayList();
    private static String vigilanteSelected = "";

    private static String vigilanteIDSelected = "";

    public static String getVigilanteIDSelected() {
        return vigilanteIDSelected;
    }

    public static void setVigilanteIDSelected(String filtro, int vigilanteIDSelected) {
        if (filtro.isEmpty()){

            DatosVigilante.vigilanteIDSelected = Idvigilante.get(vigilanteIDSelected).toString();

        }else {

            DatosVigilante.vigilanteIDSelected = FilterIdVigilante.get(vigilanteIDSelected).toString();

        }
    }


    public static void setDatavigilante(String vigilantes, String carnet, int id) {
        vigilante.add(vigilantes);
        Idvigilante.add(id);

    }

    public static  void setDataEstacionemientoAsignadoVigilante(String edificio, String alias){
        EstacionaminetoAsignado.add(edificio);
        AliasEstacionaminetoAsignado.add(alias);
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
            FilterEstacionaminetoAsignado.add(EstacionaminetoAsignado.get(vigilante.indexOf(vigilantes)).toString());
            filterAliasEstacionaminetoAsignado.add(AliasEstacionaminetoAsignado.get(vigilante.indexOf(vigilantes)).toString());
            FilterIdVigilante.add(Idvigilante.get(vigilante.indexOf(vigilantes)).toString());
        }

        return vigilanteFilter;
    }

    public static ArrayList getFilterEstacionaminetoAsignado(){
        return FilterEstacionaminetoAsignado;
    }

    public static ArrayList getFilterAliasEstacionaminetoAsignado(){
        return filterAliasEstacionaminetoAsignado;
    }

    public static int getTotalvigilante(){
        return vigilante.size();
    }

    public static void clearDatavigilante(){
        Idvigilante.clear();
        vigilante.clear();
        EstacionaminetoAsignado.clear();
        AliasEstacionaminetoAsignado.clear();
    }

    public static void setClearFilter() {
        filtervigilante.clear();
        FilterIdVigilante.clear();
        FilterEstacionaminetoAsignado.clear();
        filterAliasEstacionaminetoAsignado.clear();
    }
    public static  ArrayList getAllvigilante() {
        return vigilante;
    }

    public static  ArrayList getEstacionaminetoAsignado() {
        return EstacionaminetoAsignado;
    }

    public static  ArrayList getAliasEstacionaminetoAsignado() {
        return AliasEstacionaminetoAsignado;
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
