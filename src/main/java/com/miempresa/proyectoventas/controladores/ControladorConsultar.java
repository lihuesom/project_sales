package com.miempresa.proyectoventas.controladores;

import com.miempresa.proyectoventas.modelos.ModeloProductos;
import com.miempresa.proyectoventas.vistas.VistaConsultar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class ControladorConsultar {
    
    JFrame ventanaPadre;
    VistaConsultar ventanaConsultar;

    public ControladorConsultar(JFrame ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
        ventanaConsultar = new VistaConsultar();
        ventanaConsultar.setTitle("Ventana Consultar");
        ImageIcon icono = new ImageIcon("src/main/java/com/miempresa/proyectoventas/vistas/Logo.png");
        ventanaConsultar.setIconImage(icono.getImage());
        this.ventanaPadre.setVisible(false);
        ventanaConsultar.setVisible(true);
        ventanaConsultar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaConsultar.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                ventanaPadre.setVisible(true);
            }
        });
        JButton botonConsultar = ventanaConsultar.getjButtonConsultar();
        botonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoProducto = ventanaConsultar.getCodConsultar().getText();
                if ("".equals(codigoProducto)){
                    ventanaConsultar.getMsgErrorConsulta().setText("El campo Código es obligatorio");
                    return;
                }
                ventanaConsultar.getMsgErrorConsulta().setText("");
                ModeloProductos modeloProductos = new ModeloProductos();
                List respuesta = modeloProductos.consultaProducto(codigoProducto);

                if (respuesta.equals("")){
                    ventanaConsultar.getMsgErrorConsulta().setText("El producto no se encuentra registrado");
                }
                if (!respuesta.equals("")){
                    HashMap  disrespuesta = (HashMap ) respuesta.get(0);
                    String nombreProducto = (String) disrespuesta.get("nombre_producto");
                    String desProducto = (String) disrespuesta.get("descripcion_producto");
                    String precioProducto = (String) disrespuesta.get("precio_producto");
                    ventanaConsultar.getConsultaNom().setText(nombreProducto);
                    ventanaConsultar.getConsultaDesc().setText(desProducto);
                    ventanaConsultar.getConsultaPrecio().setText(precioProducto);
                }
                ventanaConsultar.getCodConsultar().setText("");
            }
        });
        ventanaConsultar.getRootPane().setDefaultButton(botonConsultar);
    }
}