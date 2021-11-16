package com.miempresa.proyectoventas.modelos;

import com.miempresa.proyectoventas.configuraciones.Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public abstract class ModeloBase {
//    private static String dbHost = Configuracion.map.get("DATABASE_HOST");
//    private static String dbName = Configuracion.map.get("DATABASE_NAME");
//    private static String dbUser = Configuracion.map.get("DATABASE_USER");
//    private static String dbPassword = Configuracion.map.get("DATABASE_PASSWORD");
    private static String dbHost = "localhost";
    private static String dbName = "p_ventas";
    private static String dbUser = "root";
    private static String dbPassword = "i-TeWV5j856*NI";
    
    protected static Connection conn = null;

    public ModeloBase(){
        if(conn == null){
            try {
                
                conn = DriverManager.getConnection("jdbc:mysql://"+
                    dbHost+"/"+dbName+"?"+"user="+dbUser+"&password="+dbPassword
                );
            } catch (Exception ex){
                System.err.println("No se puede conectar a la base de datos");
                System.exit(1);
            }
            
        }        
    }
    
    protected static synchronized List<HashMap<String, String>> consultar(String sql){
        List resultados = new ArrayList();
        try{
            if(!sql.toUpperCase().startsWith("SELECT")){
                return null;
            }
            Statement sentenciaSql = conn.createStatement();
            ResultSet resultadoSql = sentenciaSql.executeQuery(sql);
            ResultSetMetaData resultadoMetadata = resultadoSql.getMetaData();
            while(resultadoSql.next()){
                HashMap<String, String> fila = new HashMap();
                for(int i=1; i<=resultadoMetadata.getColumnCount(); i++){
                    fila.put(resultadoMetadata.getColumnName(i), resultadoSql.getString(i));
                }
                resultados.add(fila);
            }
        }catch (Exception ex){  
        }
        return resultados;
    }
    
    protected static synchronized HashMap<String, String> consultarPrimerResultado(String sql){
        List<HashMap<String, String>> resultados = consultar(sql);
        if (resultados.size()>0) return resultados.get(0);
        return null;
    }
    
    protected static synchronized boolean actualizar(String sql){
        try{
            if(!sql.toUpperCase().startsWith("UPDATE")) return false;          
            Statement sentenciaSql = conn.createStatement();
            sentenciaSql.executeUpdate(sql);
            conn.commit();
            return true;
        }catch (Exception ex){    
            try {
                conn.rollback();
            } catch (SQLException ex1) { 
            }
            return false;  
        }
    }
    
    protected static synchronized boolean insertar(String sql){
        try{
            if(!sql.toUpperCase().startsWith("INSERT")) return false;          
            Statement sentenciaSql = conn.createStatement();
            sentenciaSql.executeUpdate(sql);
            conn.commit();
            return true;
        }catch (Exception ex){    
            try {
                conn.rollback();
            } catch (SQLException ex1) { 
            }
            return false;  
        }
    }
    
    protected static synchronized boolean borrar(String sql){
        try{
            if(!sql.toUpperCase().startsWith("DELETE")) return false;          
            Statement sentenciaSql = conn.createStatement();
            sentenciaSql.executeUpdate(sql);
            conn.commit();
            return true;
        }catch (Exception ex){    
            try {
                conn.rollback();
            } catch (SQLException ex1) { 
            }
            return false;  
        }
    }
        
}