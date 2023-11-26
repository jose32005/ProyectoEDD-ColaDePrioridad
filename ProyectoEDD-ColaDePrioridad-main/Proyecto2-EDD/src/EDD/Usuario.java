/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import Ejecutable.main;
import javax.swing.JOptionPane;

/**
 * La clase usuario representa aun usuario del sistema
 * 
 * @author S. Estefania, G. Angelo y S. Jose
 */
public class Usuario {

    private String nombre;
    private String tipo;
    private Documento pPrim;
    private int iN;
    private Usuario pSig;

    /**
    * Constructor de la clase Usuario.
    * 
    * @param nombre El nombre del usuario.
    * @param tipo   El tipo de usuario.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public Usuario(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.pPrim = null;
        this.iN = 0;
        this.pSig = null;

    }

    public boolean esVacio() {
        return this.getpPrim() == null;
    }

    public Documento primerDocumento() {
        return getpPrim();
    }

    public void proximoDocumento(Documento pValor) {
        if (pValor != null && pValor.getpSig() != null) {
            pValor = pValor.getpSig();
        }
    }

    public void insertarDocumento(String nombre, String tamaño, String tipo) {
        Documento nuevoDocumento = new Documento(nombre, tamaño, tipo);
        if (this.esVacio()) {
            this.setpPrim(nuevoDocumento);
        } else {
            Documento pAux = this.getpPrim();
            while (pAux.getpSig() != null) {
                pAux = pAux.getpSig();
            }
            pAux.setpSig(nuevoDocumento);
        }
        this.setiN(this.getiN() + 1);
        JOptionPane.showMessageDialog(main.ventana, "Documento: " + nombre + " creado con exito"
                + "");
    }

    public void eliminarDocumento(String nombreDoc) {
        if (getpPrim() == null || nombreDoc == null) {
            System.out.println("flag 1");
            return;
        }

        if (getpPrim().getNombre().equals(nombreDoc)) {
            if (getpPrim().getTiempo() == -1) {
                setpPrim(getpPrim().getpSig());
                setiN(getiN() - 1);
                JOptionPane.showMessageDialog(main.ventana, "Documento " + nombreDoc + " eliminado con exito");
            }
            return;

        }

        Documento prev = this.getpPrim();
        Documento actual = getpPrim().getpSig();

        while (actual.getpSig() != null && !actual.getNombre().equals(nombreDoc)) {
            prev = actual;
            actual = actual.getpSig();
        }

        if (actual.getNombre().equals(nombreDoc)) {
            prev.setpSig(actual.getpSig());
            actual.setpSig(null);
            setiN(getiN() - 1);
            JOptionPane.showMessageDialog(main.ventana, "Documento " + nombreDoc + " eliminado con exito");
        }

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
     * @return the pPrim
     */
    public Documento getpPrim() {
        return pPrim;
    }

    /**
     * @param pPrim the pPrim to set
     */
    public void setpPrim(Documento pPrim) {
        this.pPrim = pPrim;
    }

    /**
     * @return the iN
     */
    public int getiN() {
        return iN;
    }

    /**
     * @param iN the iN to set
     */
    public void setiN(int iN) {
        this.iN = iN;
    }

    /**
     * @return the pSig
     */
    public Usuario getpSig() {
        return pSig;
    }

    /**
     * @param pSig the pSig to set
     */
    public void setpSig(Usuario pSig) {
        this.pSig = pSig;
    }

}
