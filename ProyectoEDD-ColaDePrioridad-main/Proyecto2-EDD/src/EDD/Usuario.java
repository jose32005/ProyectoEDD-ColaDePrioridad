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
   
}