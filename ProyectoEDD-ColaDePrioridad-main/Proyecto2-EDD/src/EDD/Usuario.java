/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import Ejecutable.main;
import javax.swing.JOptionPane;

/**
 * La clase usuario representa a un usuario del sistema
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
    /**
    * Verifica si la lista de documentos asociada al usuario está vacía.
    * 
    * @return true si la lista de documentos está vacía, false de lo contrario.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public boolean esVacio() {
        return this.getpPrim() == null;
    }

    /**
    * Obtiene el primer documento asociado al usuario.
    * 
    * @return El primer documento asociado al usuario o null si no hay documentos.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public Documento primerDocumento() {
        return getpPrim();
    }

    /**
    * Obtiene el primer siguiente documento asociado al usuario.
    * 
    * @return El siguiente documento asociado al usuario o null si no hay un documento siguiente.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public void proximoDocumento(Documento pValor) {
        if (pValor != null && pValor.getpSig() != null) {
            pValor = pValor.getpSig();
        }
    }

    /**
    * Inserta un nuevo documento asociado al usuario.
    * 
    * @param nombre El nombre del documento.
    * @param tamaño El tamaño del documento.
    * @param tipo   El tipo del documento.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public void insertarDocumento(String nombre, String tamaño, String tipo) {
        Documento nuevoDocumento = new Documento(nombre, tamaño, tipo);
        boolean existe = false;

        if (this.esVacio()) {
            this.setpPrim(nuevoDocumento);
        } else {
            Documento pAux = this.getpPrim();
            if (pAux.getNombre().equals(nombre)) {
                existe = true;
            } else {
                while (pAux.getpSig() != null) {
                    if (pAux.getNombre().equals(nombre)) {
                        System.out.println("flag");
                        existe = true;
                        break;
                    }
                    pAux = pAux.getpSig();
                }
            }
            if (existe == false) {
                pAux.setpSig(nuevoDocumento);
                this.setiN(this.getiN() + 1);
                JOptionPane.showMessageDialog(main.ventana, "Documento: " + nombre + " creado con exito" + "");
            } else {
                JOptionPane.showMessageDialog(main.ventana, "El usuario ya tiene un documento bajo ese nombre");
            }

        }

    }

    /**
    * Elimina un documento asociado al usuario por su nombre.
    * 
    * @param nombreDoc El nombre del documento a eliminar.
    * @author S. Estefania, G. Angelo y S. Jose
    */
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
     * @return the pPrim
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public Documento getpPrim() {
        return pPrim;
    }

    /**
     * @param pPrim the pPrim to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setpPrim(Documento pPrim) {
        this.pPrim = pPrim;
    }

    /**
     * @return the iN
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public int getiN() {
        return iN;
    }

    /**
     * @param iN the iN to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setiN(int iN) {
        this.iN = iN;
    }

    /**
     * @return the pSig
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public Usuario getpSig() {
        return pSig;
    }

    /**
     * @param pSig the pSig to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setpSig(Usuario pSig) {
        this.pSig = pSig;
    }
}
