/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import controller.ChatController;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import model.Mensaje;

/**
 *
 * @author idurfer
 */

// Usar unicast para que sea accesible por red
public class ChatServiceImplementation extends UnicastRemoteObject implements ChatService {
    
    private String nombre;
    private ChatController controller;

    public ChatServiceImplementation(String nombre, ChatController controller) throws RemoteException {
        super();
        this.nombre = nombre;
        this.controller = controller;
    }
    
    // Si un usuario "invoca" el metodo por remoto, se tendria que mostrar el mensaje en la vista
    @Override
    public void recibirMensaje(Mensaje mensaje) throws RemoteException {
        controller.mensajeRecibido(mensaje);
    }

    @Override
    public String getNombre() throws RemoteException {
        return nombre;
    }
}