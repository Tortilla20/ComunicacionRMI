/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.ChatJDialog;
import view.MainJFrame;

/**
 *
 * @author idurfer
 */

// Controlador principal
public class FrontController {
    
    private MainJFrame vista;

    public FrontController(MainJFrame vista) {
        this.vista = vista;
        vista.addEntrarButtonListener(configurarBotones());
    }
    
    private ActionListener configurarBotones() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = vista.getNombreTextField();
                if(nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Introduce tu nombre");
                    return;
                }
                ChatJDialog chatJDialog = new ChatJDialog(vista, true);
                ChatController chatController = new ChatController(chatJDialog, nombre);
                chatJDialog.setVisible(true);
            }
        };
        return al;
    }
}