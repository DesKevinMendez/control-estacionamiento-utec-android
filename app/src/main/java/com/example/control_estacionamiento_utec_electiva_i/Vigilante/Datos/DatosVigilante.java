package com.example.control_estacionamiento_utec_electiva_i.Vigilante.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatosVigilante extends SQLiteOpenHelper {

    public DatosVigilante(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table usuarios(id_usuarios integer primary key autoincrement, carnet text, numero_placa text, nombres text, apellidos text, edificio_asignado, tipo_parqueo text)");
        Log.i("MENSAJE: ", "SE CREO LA TABLA USUARIOS");


        db.execSQL("create table horarios(id_horario integer primary key autoincrement, carnet text, hora_inicio text, hora_fin text)");
        Log.i("MENSAJE: ", "SE CREO LA TABLA HORARIOS");

        db.execSQL("create table vigilante(id_perfil integer primary key autoincrement, nombres text, apellidos text, carnet text, clave text)");
        Log.i("MENSAJE: ", "SE CREO LA TABLA VIGILANTE");

        db.execSQL("create table historial(id_historial integer primary key autoincrement, carnet text, notificaciones text)");
        Log.i("MENSAJE: ", "SE CREO LA TABLA HISTORIAL");

        db.execSQL("create table Parqueo(id_parqueo integer primary key autoincrement, nombre_parqueo text, estado integer)");
        Log.i("MENSAJE: ", "SE CREO LA TABLA PARQUEO");



        db.execSQL("insert into usuarios(carnet, numero_placa, nombres, apellidos, edificio_asignado, tipo_parqueo) values('17-2890-2014','167897-7','Juan Jose','Hernandez Ponce','Estacionamiento estudiante','estudiante')");
        db.execSQL("insert into usuarios(carnet, numero_placa, nombres, apellidos, edificio_asignado, tipo_parqueo) values('25-5044-2015','789654-1','Mario Antonio','Martinez Castro','BJ-Sotano','Docente')");
        db.execSQL("insert into usuarios(carnet, numero_placa, nombres, apellidos, edificio_asignado, tipo_parqueo) values('12-7645-2017','247567-5','Jose Carlos','Loucel','Estacionamiento Villa Fermina','Reservado')");

        db.execSQL("insert into horarios(carnet, hora_inicio, hora_fin) values('17-2890-2014','6:00 am','9:00 am')");
        db.execSQL("insert into horarios(carnet, hora_inicio, hora_fin) values('25-5044-2015','6:00 am','2:00 pm')");
        db.execSQL("insert into horarios(carnet, hora_inicio, hora_fin) values('12-7645-2017','6:00 am','9:00 pm')");


        db.execSQL("insert into vigilante(nombres, apellidos, carnet, clave) values('Luis Manuel','Mendez Lopez','30-8756-2010','root0001')");
        db.execSQL("insert into vigilante(nombres, apellidos, carnet, clave) values('Eduardo Javier','Valencia Ortiz','30-7643-2014','123456')");
        db.execSQL("insert into vigilante(nombres, apellidos, carnet, clave) values('Milton Josue','Ceren Gonzalez','30-4467-2012','password')");


        db.execSQL("insert into historial(carnet, notificaciones) values('17-2890-2014','El usuario no se presento en el parqueo')");
        db.execSQL("insert into historial(carnet, notificaciones) values('25-5044-2015','El usuario excedio el tiempo de parqueo')");
        db.execSQL("insert into historial(carnet, notificaciones) values('12-7645-2017','El usuario se estaciono indevidamente')");


        db.execSQL("insert into Parqueo(nombre_parqueo, estado) values('Polideportivo',1)");
        db.execSQL("insert into Parqueo(nombre_parqueo, estado) values('Benito Juarez',1)");
        db.execSQL("insert into Parqueo(nombre_parqueo, estado) values('Simon Bolivar',0)");
        db.execSQL("insert into Parqueo(nombre_parqueo, estado) values('Villa Fermina',0)");
        db.execSQL("insert into Parqueo(nombre_parqueo, estado) values('Edificio Comunicaciones',0)");

        Log.i("MENSAJE: ", "SE INSERTARON LOS DATOS");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
