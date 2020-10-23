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
    public static int numBucle;
    public static int numVecinos;
    public static int numClientes;
    private String bucles;
    protected int x;
    protected int y;
    
    public Cliente(int id, String clientes, String vecinos, String bucles){
        this.id = id;
        this.bucles = bucles;
    }
    @Override
    public void run(){
        try{
            sk = new Socket("127.0.0.1", 00001);
            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());
            
            for (int i = 0; i < Integer.parseInt(bucles); i++){
                dos.writeUTF(this.actualizarPosicion());
            }
            dis.close();
            dos.close();
        }catch (IOException ex){
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public String actualizarPosicion(){
        String coordenadas = new String();
        
        x += (int)(Math.random()*5+(-5));
        y += (int)(Math.random()*5+(-5));
        
        coordenadas = x + " " + y;
        
        return coordenadas;
    }
}

public class ClienteMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException {
        ArrayList<Thread> clientes = new ArrayList<Thread>();
            
        System.out.println("Dame el numero de clientes: ");
        Scanner s = new Scanner(System.in);
        String cli = s.nextLine();

        System.out.println("Dame el numero de vecinos: ");
        s = new Scanner(System.in);
        String veci = s.nextLine();

        System.out.println("Dame el numero de bucles ");
        s = new Scanner(System.in);
        String bucles = s.nextLine();
            
        for(int i = 1; i <= Integer.parseInt(cli); i++){
            clientes.add(new Cliente(i, cli, veci, bucles));
        }
        for(Thread thread : clientes){
            Thread.sleep(10);
            thread.start();
        }
    }
}
