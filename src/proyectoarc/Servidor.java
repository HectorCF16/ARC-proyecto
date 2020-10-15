/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoarc;
import java.io.*;
import java.net.*;
import java.util.logging.*;

/**
 *
 * @author Adrian
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        ServerSocket ss;
        System.out.print("Iniciando servidor. Espere un momento.");
        try{
            ss = new ServerSocket(00001);
            System.out.print("\t[OK]");
            int idSession = 0;
            while(true) {
            Socket socket;
            socket = ss.accept();
            System.out.println("Nueva conexion entrante: "+socket);
            ((ServidorHilo) new ServidorHilo(socket, idSession)).start();
            idSession++;

        }
        }catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

