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
    private String tamaño;
    private String tipo;
    private Documento pSig;
    private int tiempo;
    
    
    public Documento (String nombre, String tamaño, String tipo){
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.tipo = tipo;
        this.pSig = null;
        this.tiempo = -1;
                
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tamaño
     */
    public String getTamaño() {
        return tamaño;
    }

    /**
     * @param tamaño the tamaño to set
     */
    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the pSig
     */
    public Documento getpSig() {
        return pSig;
    }

    /**
     * @param pSig the pSig to set
     */
    public void setpSig(Documento pSig) {
        this.pSig = pSig;
    }

    /**
     * @return the tiempo
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    
}
