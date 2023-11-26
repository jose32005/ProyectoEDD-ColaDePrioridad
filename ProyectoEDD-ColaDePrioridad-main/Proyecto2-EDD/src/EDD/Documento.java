/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 * La clase Documento representa un documento que puede ser procesado en una cola
 * de impresión. Cada documento tiene un nombre, un tamaño, un tipo y un tiempo asociado.
 * 
 * El tiempo por defecto se establece en -1, indicando que el documento no ha sido
 * procesado en la cola de impresión.
 *
 * @author G. Angelo, S. Estefania y S. Jose
 */
public class Documento {
    private String nombre;
    private int tamaño;
    private String tipo;
    private Documento pSig;
    private int tiempo;
    
    /**
     * Constructor de la clase Documento.
     * 
     * @param nombre El nombre del documento.
     * @param tamaño El tamaño del documento.
     * @param tipo El tipo del documento.
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public Documento (String nombre, int tamaño, String tipo){
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.tipo = tipo;
        this.pSig = null;
        this.tiempo = -1;            
    }

    /**
     * @return the nombre
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tamaño
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * @param tamaño the tamaño to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    /**
     * @return the tipo
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the pSig
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public Documento getpSig() {
        return pSig;
    }

    /**
     * @param pSig the pSig to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setpSig(Documento pSig) {
        this.pSig = pSig;
    }

    /**
     * @return the tiempo
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public int getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    
}
