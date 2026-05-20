/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Mensaje;

/**
 *
 * @author idurfer
 */

public interface ChatService extends Remote {
    void recibirMensaje(Mensaje mensaje) throws RemoteException;
    String getNombre() throws RemoteException;
}