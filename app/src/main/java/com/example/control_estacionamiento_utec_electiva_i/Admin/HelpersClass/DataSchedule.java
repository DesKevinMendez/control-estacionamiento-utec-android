package com.example.control_estacionamiento_utec_electiva_i.Admin.HelpersClass;

public class DataSchedule {

    public static String[] hora_entrada_lunes = {""};
    public static String[] hora_salida_lunes = {""};
    public static String[] hora_entrada_martes = {""};
    public static String[] hora_salida_martes = {""};
    public static String[] hora_entrada_miercoles = {""};
    public static String[] hora_salida_miercoles = {""};
    public static String[] hora_entrada_jueves = {""};
    public static String[] hora_salida_jueves = {""};
    public static String[] hora_entrada_viernes = {""};
    public static String[] hora_salida_viernes = {""};
    public static String[] hora_entrada_sabado = {""};
    public static String[] hora_salida_sabado = {""};
    public static String[] hora_entrada_domingo = {""};
    public static String[] hora_salida_domingo = {""};
    

    public static String[] getHora_entrada_lunes() {
        return hora_entrada_lunes;
    }

    public static String[] getHora_salida_lunes() {
        return hora_salida_lunes;
    }

    public static String[] getHora_entrada_martes() {
        return hora_entrada_martes;
    }

    public static String[] getHora_salida_martes() {
        return hora_salida_martes;
    }

    public static String[] getHora_entrada_miercoles() {
        return hora_entrada_miercoles;
    }

    public static String[] getHora_salida_miercoles() {
        return hora_salida_miercoles;
    }

    public static String[] getHora_entrada_jueves() {
        return hora_entrada_jueves;
    }

    public static String[] getHora_salida_jueves() {
        return hora_salida_jueves;
    }

    public static String[] getHora_entrada_viernes() {
        return hora_entrada_viernes;
    }

    public static String[] getHora_salida_viernes() {
        return hora_salida_viernes;
    }

    public static String[] getHora_entrada_sabado() {
        return hora_entrada_sabado;
    }

    public static String[] getHora_salida_sabado() {
        return hora_salida_sabado;
    }

    public static String[] getHora_entrada_domingo() {
        return hora_entrada_domingo;
    }

    public static String[] getHora_salida_domingo() {
        return hora_salida_domingo;
    }

    public static void setClearAllSchedules(){
        hora_entrada_lunes[0] = "";
        hora_salida_lunes[0] = "";

        hora_entrada_martes[0] = "";
        hora_salida_martes[0] = "";

        hora_entrada_miercoles[0] = "";
        hora_salida_miercoles[0] = "";

        hora_entrada_jueves[0] = "";
        hora_salida_jueves[0] = "";

        hora_entrada_viernes[0] = "";
        hora_salida_viernes[0] = "";

        hora_entrada_sabado[0] = "";
        hora_salida_sabado[0] = "";

        hora_entrada_domingo[0] = "";
        hora_salida_domingo[0] = "";
    }

    public static void setDataHorarioLunes(String entrada, String salida){
        hora_entrada_lunes[0] = entrada;
        hora_salida_lunes[0] = salida;
    }

    public static void setDataHorarioMartes(String entrada, String salida){
        hora_entrada_martes[0] = entrada;
        hora_salida_martes[0] = salida;
    }

    public static void setDataHorarioMiercoles(String entrada, String salida){
        hora_entrada_miercoles[0] = entrada;
        hora_salida_miercoles[0] = salida;
    }

    public static void setDataHorarioJueves(String entrada, String salida){
        hora_entrada_jueves[0] = entrada;
        hora_salida_jueves[0] = salida;
    }

    public static void setDataHorarioViernes(String entrada, String salida){
        hora_entrada_viernes[0] = entrada;
        hora_salida_viernes[0] = salida;
    }


    public static void setDataHorarioSabado(String entrada, String salida){
        hora_entrada_sabado[0] = entrada;
        hora_salida_sabado[0] = salida;
    }


    public static void setDataHorarioDomingo(String entrada, String salida){
        hora_entrada_domingo[0] = entrada;
        hora_salida_domingo[0] = salida;
    }

}
