/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import RMI.ChatServiceImplementation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import view.ChatJDialog;
import RMI.ChatService;

/**
 *
 * @author idurfer
 */

// Controlador pricipal del chat
// Gestionar la conexion del RMI y tambien al enviar y recibir mensajes
public class ChatController {
    
    private ChatJDialog vista;
    private String nombre;
    // Hace referencia al servicio del otro usuario
    private ChatService servicioRemoto; 
    private static final int PORT = 1099;
    private static final String NOMBRE_SERVICIO = "ChatServicie";

    public ChatController(ChatJDialog vista, String nombre) {
        this.vista = vista;
        this.nombre = nombre;
        publicarServicio();
        vista.addConectarButtonListener(botonConectar());
        vista.addEnviarButtonListener(botonEnviarMensaje());
    }
    
    // publicar servicio RMI ara que el otro usuario pueda conectarse
    private void publicarServicio() {
        try {
            ChatServiceImplementation ChatServiceImplementation = new ChatServiceImplementation(nombre, this);
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(PORT);
            } catch (RemoteException ex) {
                // Si ya existe el registry lo reutilizamos
                registry = LocateRegistry.getRegistry(PORT);
            }
            registry.rebind(NOMBRE_SERVICIO + nombre, ChatServiceImplementation);
            System.out.println("Servicio publicado como: " + NOMBRE_SERVICIO + nombre);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(vista, "Error al publicar servicio RMI: " + ex.getMessage());
        }
    }
    
    // Se conecta al RMI del otro usuario por ip y nombre
    public void conectar(String ip, String nombre) {
        try {
            Registry registro = LocateRegistry.getRegistry(ip, PORT);
            servicioRemoto = (ChatService) registro.lookup(NOMBRE_SERVICIO + nombre);
            vista.mostrarMensaje("Conectado a: " + nombre);
            System.out.println("Conectado a: " + nombre);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
    
    // Enviar mensaje a usuario
    private void enviarMensaje(String texto) throws RemoteException {
        if(servicioRemoto == null) {
            JOptionPane.showMessageDialog(vista, "No estas conectado a ningun usuario");
            return;
        }
        servicioRemoto.recibirMensaje(nombre, texto);
        vista.mostrarMensaje("Yo: " + texto);
    }
    
    // Llamar a ChatServiceImplementation cuando llega un mensaje
    public void mensajeRecibido(String usuarioOrigen, String mensaje) {
        // Usar swingUtilities.invokeLater para actualizar la vista desde el hilo correcto
        SwingUtilities.invokeLater(() -> {
            vista.mostrarMensaje(usuarioOrigen + ":" + mensaje);
        });
    }
    
    //BOTONES
    private ActionListener botonConectar() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = vista.getIpTextField();
                String nombreRemoto = vista.getNombreRemotoTextField();
                if(ip.isEmpty() ||nombreRemoto.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Introduce la IP y el nombre del otro usuario");
                    return;
                }
                conectar(ip, nombreRemoto);
            }
        };
        return al;
    }
    
    private ActionListener botonEnviarMensaje() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String texto = vista.getMensajeTextField();
                    if(texto.isEmpty()) {
                        JOptionPane.showMessageDialog(vista, "El manesaje no puede estar vacio");
                        return;
                    }
                    enviarMensaje(texto);
                    vista.limpiarMensajeTextField();
                } catch (RemoteException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        };
        return al;
    }
}