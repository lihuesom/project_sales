package com.miempresa.proyectoventas.controladores;

import com.miempresa.proyectoventas.modelos.ModeloProductos;
import com.miempresa.proyectoventas.vistas.VistaAgregar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ControladorAgregar {

    JFrame ventanaPadre;
    VistaAgregar ventanaAgregar;

    public ControladorAgregar(JFrame ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
        ventanaAgregar = new VistaAgregar();
        ventanaAgregar.setTitle("Ventana Agregar");
        ImageIcon icono = new ImageIcon("src/main/java/com/miempresa/proyectoventas/vistas/Logo.png");
        ventanaAgregar.setIconImage(icono.getImage());
        this.ventanaPadre.setVisible(false);
        ventanaAgregar.setVisible(true);
        ventanaAgregar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaAgregar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventanaPadre.setVisible(true);
            }
        });
        JButton botonGuardar = ventanaAgregar.getBotonGuardarProducto();
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoProducto = ventanaAgregar.getCodigoProducto().getText();
                String nombreProducto = ventanaAgregar.getNombreProducto().getText();
                String descripcionProducto = ventanaAgregar.getDescripcionProducto().getText();
                String precioProducto = ventanaAgregar.getPrecioProducto().getText();
                if ("".equals(codigoProducto)){
                    ventanaAgregar.getMsgError().setText("El campo Código es obligatorio");
                    return;
                }
                if ("".equals(nombreProducto)){
                    ventanaAgregar.getMsgError().setText("El campo Nombre del producto es obligatorio");
                    return;
                }
                if ("".equals(descripcionProducto)){
                    ventanaAgregar.getMsgError().setText("El campo Descripción del producto es obligatorio");
                    return;
                }
                if ("".equals(precioProducto)){
                    ventanaAgregar.getMsgError().setText("El campo Precio del producto es obligatorio");
                    return;
                }
                Float precioFloat;
                try {
                    precioFloat = Float.valueOf(precioProducto);
                }catch (Exception ex){
                    ventanaAgregar.getMsgError().setText("El precio debe ser de tipo numérico");
                    return;
                }
                ventanaAgregar.getMsgError().setText("");
                ModeloProductos modeloProductos = new ModeloProductos();
                String respuesta = modeloProductos.insertarProducto(codigoProducto,nombreProducto,descripcionProducto,precioFloat);
                ventanaAgregar.getMsgError().setText(respuesta);
                if (!respuesta.equals("")){
                    return;
                }
                ventanaAgregar.getCodigoProducto().setText("");
                ventanaAgregar.getNombreProducto().setText("");
                ventanaAgregar.getDescripcionProducto().setText("");
                ventanaAgregar.getPrecioProducto().setText("");
            }
        });
        ventanaAgregar.getRootPane().setDefaultButton(botonGuardar);
    }
}
