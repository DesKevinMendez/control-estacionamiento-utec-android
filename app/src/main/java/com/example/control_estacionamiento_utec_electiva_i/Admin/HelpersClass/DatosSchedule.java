package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

public final class DatosSchedule {

    public static String horaEntrada = "";
    public static String horaSalida = "";


    public static String getHoraEntrada() {
        return horaEntrada;
    }

    public static String getHoraSalida() {
        return horaSalida;
    }

    public static void setHoraEntrada(String hora) {
        String[] horaEntrad = hora.split(":");
        String Entrada = "";

        if (Integer.valueOf(horaEntrad[0])< 10){
            Entrada = "0"+horaEntrad[0]+":"+horaEntrad[1];

        }else {
            Entrada = hora;
        }
        horaEntrada = Entrada;
    }

    public static void setHoraSalida(String hora) {
        String[] horaSalidas = hora.split(":");
        String Salida = "";

        if (Integer.valueOf(horaSalidas[0])< 10){
            Salida = "0"+horaSalidas[0]+":"+horaSalidas[1];

        }else {
            Salida = hora;
        }

        horaSalida = Salida;
    }
}
