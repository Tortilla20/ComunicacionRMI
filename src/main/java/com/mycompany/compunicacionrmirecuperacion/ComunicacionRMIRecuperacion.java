/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.compunicacionrmirecuperacion;

import controller.FrontController;
import view.MainJFrame;

/**
 *
 * @author idurfer
 */
public class ComunicacionRMIRecuperacion {
    public static void main(String[] args) {
        MainJFrame vista = new MainJFrame();
        FrontController controller = new FrontController(vista);
        vista.setVisible(true);
    }
}