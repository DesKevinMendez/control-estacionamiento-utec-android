package com.example.control_estacionamiento_utec_electiva_i.Admin.InfoSetViews;

public final class ReserveParkingDataSend {

    public static String fechaReserva = "";
    public static String InfoVisitante = "";

    public static String getFechaReserva() {
        return fechaReserva;
    }

    public static String getInfoVisitante() {
        return InfoVisitante;
    }

    public static void setFechaReserva(String fechaReserva) {
        ReserveParkingDataSend.fechaReserva = fechaReserva;
    }

    public static void setInfoVisitante(String infoVisitante) {
        InfoVisitante = infoVisitante;
    }
}
