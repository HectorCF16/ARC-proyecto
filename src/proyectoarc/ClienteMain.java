/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoarc;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;


/**
 *
 * @author Adrian
 */
class Cliente extends Thread{
    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    private int id;
    public Cliente(int id){
        this.id = id;
    }
    @Override
    public void run(){
        try{
            sk = new Socket("127.0.0.1", 00001);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            System.out.println(id + " envia saludo");
            dos.writeUTF("hola");
            String respuesta = "";
            System.out.println(id + " Servidor devuelve saludo " + respuesta);
            dis.close();
            dos.close();
        }catch (IOException ex){
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
public class ClienteMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        ArrayList<Thread> clientes = new ArrayList<Thread>();
        for(int i = 0; i < 100; i++){
            clientes.add(new Cliente(i));
        }
        for(Thread thread : clientes){
            thread.start();
        }
    }
}
