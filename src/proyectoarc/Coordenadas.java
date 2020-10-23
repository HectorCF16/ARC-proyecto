/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoarc;

import java.util.Observable;

/**
 *
 * @author Adrian
 */

public class Coordenadas extends Observable{
    private String coor;
    public Coordenadas(){
        
    }
    
    public String getCoordenadas(){
        return this.coor;
    }
    
    public void setCoordenadas(String coor){
        this.coor = coor;
        this.setChanged();
        this.notifyObservers(this.getCoordenadas());
    }
}
