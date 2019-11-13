package com.example.control_estacionamiento_utec_electiva_i.docentes;

public class Estacionamientos {

    private String nombreEdificio;
    private String estadoEdificio;

    public Estacionamientos(String nombreEdificio, String estadoEdificio) {
        this.nombreEdificio = nombreEdificio;
        this.estadoEdificio = estadoEdificio;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public String getEstadoEdificio() {
        return estadoEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public void setEstadoEdificio(String estadoEdificio) {
        this.estadoEdificio = estadoEdificio;
    }
}
