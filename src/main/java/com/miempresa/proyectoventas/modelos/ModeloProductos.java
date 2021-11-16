package com.miempresa.proyectoventas.modelos;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class ModeloProductos extends ModeloBase{

    public ModeloProductos(){
        super();
    }
    
    public String insertarProducto(String codigoProducto,String nombreProducto,String descripcionProducto,Float precioProducto){
        String queryValidadKey = "SELECT cod_producto FROM t_productos WHERE cod_producto = '"
                +codigoProducto+"'";
        HashMap r = consultarPrimerResultado(queryValidadKey);
        if (r != null){
            return "Ya existe un producto con ese código";
        }
        String query = "INSERT INTO t_productos (cod_producto,"
                + "nombre_producto,descripcion_producto,precio_producto) "
                + "VALUES ("
                + "'"+codigoProducto+"','"+nombreProducto+"','"+descripcionProducto+"',"
                + precioProducto+ ")";
        System.out.println(query);
        if (!insertar(query)){
            return "Producto guardado";
        }
        return "NO se pudo guardar el producto";
    }
    
    public String eliminarProducto(String codigoProducto){
      String query ="DELETE FROM t_productos WHERE (cod_producto = '"+codigoProducto+"');";
      
      Boolean operacion = borrar(query);
      System.out.println(query);
//      System.out.println("operacion = "+ operacion);
      if(operacion != true){
        return "Producto eliminado";
      }
        return "el producto no fue eliminado";
    }
    
    public String editarProducto(String codigoProducto,String nombreProducto,String descripcionProducto,Float precioProducto){
      String query ="UPDATE t_productos SET nombre_producto ='"+nombreProducto+"',"+
        "descripcion_producto= '"+descripcionProducto +"',"+
        "precio_producto='"+precioProducto+"' WHERE cod_producto= '"+codigoProducto+"';";
      
      Boolean operacion = actualizar(query);
      System.out.println(query);
        
      if(operacion != true){
            return "Producto actualizado";
      }
        return "Error en la actualizacion";
      
    }
    
    public List consultaProducto(String codigoProducto){
      String query ="SELECT nombre_producto, descripcion_producto, precio_producto FROM p_ventas.t_productos WHERE cod_producto ='"+codigoProducto +"';";
      
        List<HashMap<String, String>> operacion = consultar(query);
        System.out.println(query);
        System.out.println(operacion);
        
        return operacion;
    }
}
