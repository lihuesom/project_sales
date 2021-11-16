package com.miempresa.proyectoventas.controladores;

import com.miempresa.proyectoventas.vistas.VistaLogin;
import com.miempresa.proyectoventas.vistas.VistaMenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ControladorMenuPrincipal {

    JFrame ventanaPadre;
    VistaMenuPrincipal ventanaMenuPrincipal;

    public ControladorMenuPrincipal(JFrame ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
        ventanaMenuPrincipal = new VistaMenuPrincipal();
        ventanaMenuPrincipal.setTitle("Ventana Principal");
        ImageIcon icono = new ImageIcon("src/main/java/com/miempresa/proyectoventas/vistas/Logo.png");
        ventanaMenuPrincipal.setIconImage(icono.getImage());
        this.ventanaPadre.setVisible(false);
        ventanaMenuPrincipal.setVisible(true);
        ventanaMenuPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaMenuPrincipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ventanaPadre.setVisible(true);
            }
        });

        JButton botonAgregar = ventanaMenuPrincipal.getBotonAgregar();
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ControladorAgregar(ventanaMenuPrincipal);
            }
        });

        JButton botonConsultar = ventanaMenuPrincipal.getBotonConsultar();
        botonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ControladorConsultar(ventanaMenuPrincipal);
            }
        });

        JButton botonEditar = ventanaMenuPrincipal.getBotonEditar();
        botonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ControladorEditar(ventanaMenuPrincipal);
            }
        });

        JButton botonEliminar = ventanaMenuPrincipal.getBotonEliminar();
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ControladorEliminar(ventanaMenuPrincipal);
            }
        });
    }

}
