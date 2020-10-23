/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoarc;
import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Scanner;
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
        Coordenadas coordenadas;
        System.out.print("Iniciando servidor. Espere un momento.");
        
        try{
            ss = new ServerSocket(00001);
            System.out.print("\t[OK]\n");
            
            System.out.println("Dame el numero de clientes: ");
            Scanner s = new Scanner(System.in);
            String cli = s.nextLine();

            System.out.println("Dame el numero de vecinos: ");
            s = new Scanner(System.in);
            String veci = s.nextLine();

            System.out.println("Dame el numero de bucles ");
            s = new Scanner(System.in);
            String bucles = s.nextLine();
            
            int idSession = 0;
            while(true) {
                Socket socket;
                socket = ss.accept();
                coordenadas = new Coordenadas();
                System.out.println("Nueva conexion entrante: "+socket);
                ((ServidorHilo) new ServidorHilo(socket, coordenadas)).start();
                idSession++;
                
            }
        }catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


