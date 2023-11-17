/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author joses
 */
public class Usuario {
    private String nombre;
    private String tipo;
    private Documento pPrim;
    private int iN;
    private Usuario pSig;
    
    public Usuario(String nombre, String tipo){
        this.nombre = nombre;
        this.tipo = tipo;
        this.pPrim = null;
        this.iN = 0;
        this.pSig = null;
      
    }
   
    public boolean esVacio() {
        return this.pPrim == null;
}
    public Documento primero() {
        return pPrim;
    }
    
    public void proximo(Documento pValor) {
        if (pValor != null && pValor.getpSig() != null) {
            pValor = pValor.getpSig();
        }
    }
    
    public void insertar(String nombre, int tamaño, String tipo) {
        Documento nuevoDocumento = new Documento(nombre, tamaño, tipo);
        if (this.esVacio()) {
            this.pPrim = nuevoDocumento;
        } else {
            Documento pAux = this.pPrim;
            while (pAux.getpSig() != null) {
                pAux = pAux.getpSig();
            }
            pAux.setpSig(nuevoDocumento);
        }
        this.iN++;
    }
    
    public void eliminar(Documento elemento) {
        if (pPrim == null || elemento == null) {
            return;
        }

        if (pPrim.gettInfo().equals(elemento)) {
            pPrim = pPrim.getpSig();
            iN--;
            return;
        }

        Documento actual = pPrim;
        while (actual.getpSig() != null && !actual.getpSig().gettInfo().equals(elemento)) {
            actual = actual.getpSig();
        }

        if (actual.getpSig() != null) {
            actual.setpSig(actual.getpSig().getpSig());
            iN--;
        }
    }
    
}
