package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

import java.util.ArrayList;

public final class DatosBuilding {

    private static ArrayList edificios = new ArrayList();
    private static ArrayList totalEstacionamiento = new ArrayList();
    private static ArrayList estacionamientoDisponible = new ArrayList();

    static {
          edificios.add("Benito Juarez");
          edificios.add("Francisoo Morazan");
          edificios.add("Tomas Jefferson");
          edificios.add("Villa fermina");
          edificios.add("Simon Bolivar");

          totalEstacionamiento.add("20");
          totalEstacionamiento.add("25");
          totalEstacionamiento.add("30");
          totalEstacionamiento.add("10");
          totalEstacionamiento.add("15");

          estacionamientoDisponible.add("10");
          estacionamientoDisponible.add("20");
          estacionamientoDisponible.add("10");
          estacionamientoDisponible.add("10");
          estacionamientoDisponible.add("14");

    }
    public static int getTotalEdificios() {
        return edificios.size();
    }

    public static void saveInfoEdificio(String edificio, int totalEstac, int disponibleEsta) {
        edificios.add(edificio);
        totalEstacionamiento.add(totalEstac);
        estacionamientoDisponible.add(disponibleEsta);
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

}
