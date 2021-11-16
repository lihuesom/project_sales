package com.miempresa.proyectoventas.configuraciones;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


public class Configuracion {
    private static String archivoConfiguracionDb = "src/main/java/com/miempresa/proyectoventas/configuraciones/conexion_db.properties";
    public static HashMap<String, String> map = new HashMap();
    static{
        FileInputStream fis;
        try {
            fis = new FileInputStream(archivoConfiguracionDb);
            ResourceBundle resources = new PropertyResourceBundle(fis);
            Enumeration<String> keys = resources.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                map.put(key, resources.getString(key));            
            }
        } catch (Exception e) {
            System.out.println("No se pudo abrir el archivo de configurarción a la base de datos: "+ archivoConfiguracionDb);
            System.exit(1);       
        } 
    }
}