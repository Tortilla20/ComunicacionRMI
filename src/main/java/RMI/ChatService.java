/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author idurfer
 */
// Interfaz remota del chat
// Meotodos que se pueden invocar desde un RMI
public interface ChatService extends Remote {
    void recibirMensaje(String usuarioOrigen, String mensaje) throws RemoteException;
    String getNombre() throws RemoteException;
}