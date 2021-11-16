package com.miempresa.proyectoventas.controladores;

import com.miempresa.proyectoventas.modelos.ModeloProductos;
import com.miempresa.proyectoventas.vistas.VistaEditar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ControladorEditar {
    
    JFrame ventanaPadre;
    VistaEditar ventanaEditar;

    public ControladorEditar(JFrame ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
        ventanaEditar = new VistaEditar();
        ventanaEditar.setTitle("Ventana Editar");
        ImageIcon icono = new ImageIcon("src/main/java/com/miempresa/proyectoventas/vistas/Logo.png");
        ventanaEditar.setIconImage(icono.getImage());
        this.ventanaPadre.setVisible(false);
        ventanaEditar.setVisible(true);
        ventanaEditar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaEditar.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                ventanaPadre.setVisible(true);
            }
        });
        JButton botonEditar = ventanaEditar.getjButtonEditar();
        botonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigoProducto = ventanaEditar.getCodProducto().getText();
                String nombreProducto = ventanaEditar.getNomProducto().getText();
                String descripcionProducto = ventanaEditar.getDescripProducto().getText();
                String precioProducto = ventanaEditar.getPrecioProducto().getText();
                if ("".equals(codigoProducto)){
                    ventanaEditar.getMsgErrorEditar().setText("El campo Código es obligatorio");
                    return;
                }
                if ("".equals(nombreProducto)){
                    ventanaEditar.getMsgErrorEditar().setText("El campo Nombre del producto es obligatorio");
                    return;
                }
                if ("".equals(descripcionProducto)){
                    ventanaEditar.getMsgErrorEditar().setText("El campo Descripción del producto es obligatorio");
                    return;
                }
                if ("".equals(precioProducto)){
                    ventanaEditar.getMsgErrorEditar().setText("El campo Precio del producto es obligatorio");
                    return;
                }
                Float precioFloat;
                try {
                    precioFloat = Float.valueOf(precioProducto);
                }catch (Exception ex){
                    ventanaEditar.getMsgErrorEditar().setText("El precio debe ser de tipo numérico");
                    return;
                }
                ventanaEditar.getMsgErrorEditar().setText("");
                ModeloProductos modeloProductos = new ModeloProductos();
                String respuesta = modeloProductos.editarProducto(codigoProducto,nombreProducto,descripcionProducto,precioFloat);
                ventanaEditar.getMsgErrorEditar().setText(respuesta);
                if (!respuesta.equals("")){
                    return;
                }
                ventanaEditar.getCodProducto().setText("");
                ventanaEditar.getNomProducto().setText("");
                ventanaEditar.getDescripProducto().setText("");
                ventanaEditar.getPrecioProducto().setText("");
            }
        });
        
    }
}
