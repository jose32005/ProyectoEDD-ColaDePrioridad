/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author joses
 */
public class Documento {
    private String nombre;
    private int tamaño;
    private String tipo;
    private Documento pSig;
    private int tiempo;
    
    
    public Documento (String nombre, int tamaño, String tipo){
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.tipo = tipo;
        this.pSig = null;
        this.tiempo = -1;
                
    }
    
    public Documento getpSig() {
        return pSig;
    }
    
    public void setpSig(Documento pSig) {
        this.pSig = pSig;
    }
    
    public String gettInfo() {
        return nombre;
    }
}
