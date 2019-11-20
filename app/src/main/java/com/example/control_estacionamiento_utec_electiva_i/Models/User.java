package com.example.control_estacionamiento_utec_electiva_i.Models;


public final class User {
    private static int id;
    private static String email = "";
    private static String num_placa = "";
    private static String nombres = "";
    private static String apellidos = "";
    private static int reserva_id;
    private static String api_token = "";
    private static int rol_id;
    private static int estado;
    private static String name_role;

    public static String getName_role() {
        return name_role;
    }

    private static Boolean loggedUser = false;

    public static Boolean getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(Boolean loggedUser) {
        User.loggedUser = loggedUser;
    }

    public static void setDataUser(int ids, String emails, String num_placas, String nombre, String apellido,
                                   String api_tokens, int rol_ids, int estados, String name_roles) {
        id = ids;
        email = emails;
        num_placa = num_placas;
        nombres = nombre;
        apellidos = apellido;
        api_token = api_tokens;
        rol_id = rol_ids;
        estado = estados;
        name_role = name_roles;
    }

    public static int getId() {
        return id;
    }

    public static String getEmail() {
        return email;
    }

    public static String getNum_placa() {
        return num_placa;
    }

    public static String getNombres() {
        return nombres;
    }

    public static String getApellidos() {
        return apellidos;
    }

    public static int getReserva_id() {
        return reserva_id;
    }

    public static String getApi_token() {
        return api_token;
    }

    public static int getRol_id() {
        return rol_id;
    }

    public static int getEstado() {
        return estado;
    }
}
