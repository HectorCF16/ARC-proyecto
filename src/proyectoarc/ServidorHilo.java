/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoarc;
import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.*;

public class ServidorHilo extends Thread implements Observer{
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;
    private String bucles;
    private Coordenadas coordenadas;
    public ServidorHilo(Socket socket, Coordenadas coordenadas){
        this.socket = socket;
        this.bucles = bucles;
        this.coordenadas = coordenadas;
        
        try{
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            
        } catch (IOException e) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    public void desconnectar(){
        try{
            socket.close();
        } catch (IOException e) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    @Override
    public void run(){/*
        String coordenadas = "";
        
            try{
                //accion = dis.readUTF();
                /*if(accion.equals("hola")){
                    System.out.println("El cliente con idSesion "+ this.idSessio + " saluda");

                    dos.writeUTF("adios");
                }*

                //this.wait(20000);
                for(int i = 0; i < Integer.parseInt(this.bucles); i++){
                    coordenadas = dis.readUTF();

                }
            }catch(IOException ex){
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE,null,ex);
            } /*catch (InterruptedException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }*
        
        desconnectar();*/
        
        boolean conectado = true;
        String c;
        
        while (conectado){
            try{
                c = dis.readUTF();//lee un mensaje enviado por el cliente
                this.coordenadas.setCoordenadas(c);
                
            }catch(IOException ex){
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE,null,ex);
                conectado = false;
                try {
                    dis.close();
                    dos.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            dos.writeUTF(arg.toString());//envia el mensaje al cliente
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
