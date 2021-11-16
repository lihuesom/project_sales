package com.miempresa.proyectoventas.controladores;

import com.miempresa.proyectoventas.modelos.ModeloProductos;
import com.miempresa.proyectoventas.vistas.VistaEliminar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class ControladorEliminar {
        
    JFrame ventanaPadre;
    VistaEliminar ventanaEliminar;

    public ControladorEliminar(JFrame ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
        ventanaEliminar = new VistaEliminar();
        ventanaEliminar.setTitle("Ventana Eliminar");
        ImageIcon icono = new ImageIcon("src/main/java/com/miempresa/proyectoventas/vistas/Logo.png");
        ventanaEliminar.setIconImage(icono.getImage());
        this.ventanaPadre.setVisible(false);
        ventanaEliminar.setVisible(true);
        ventanaEliminar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaEliminar.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                ventanaPadre.setVisible(true);
            }
        });
        JButton botonEliminar = ventanaEliminar.getjButtonDelete();
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoProducto = ventanaEliminar.getDeleteProducto().getText();
                if ("".equals(codigoProducto)){
                    ventanaEliminar.getMsgErrorDelete().setText("El campo Código es obligatorio");
                    return;
                }
               
                ventanaEliminar.getMsgErrorDelete().setText("");
                ModeloProductos modeloProductos = new ModeloProductos();
                String respuesta = modeloProductos.eliminarProducto(codigoProducto);
                ventanaEliminar.getMsgErrorDelete().setText(respuesta);
                if (!respuesta.equals("")){
                    return;
                }
                ventanaEliminar.getDeleteProducto().setText("");
            }
        });
        
    }
}
