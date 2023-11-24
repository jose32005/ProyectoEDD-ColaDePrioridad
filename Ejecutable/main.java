/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejecutable;

import EDD.HashTable;
import EDD.MonticuloBinario;
import EDD.Usuario;
import Extras.Funciones;
import Interfaces.Ventana1;
import java.io.IOException;

/**
 *
 * @author joses
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        HashTable nuevo_hash = new HashTable(100);
        MonticuloBinario monticulo = new MonticuloBinario();
        Funciones func = new Funciones();
        func.abrir_archivo();
        nuevo_hash.leer_db();
        //nuevo_hash.imprimir_hashtable();
        for (int i = 0; i < 10; i++) {
            nuevo_hash.buscarUsuario("angelo").insertarDocumento("doc" + i, 52, ".txt");
        }
        monticulo.insertar(nuevo_hash.buscarUsuario("angelo").getpPrim(), nuevo_hash.buscarUsuario("angelo"));
        monticulo.insertar(nuevo_hash.buscarUsuario("angelo").getpPrim().getpSig(), nuevo_hash.buscarUsuario("angelo"));
        monticulo.insertar(nuevo_hash.buscarUsuario("angelo").getpPrim().getpSig().getpSig(), nuevo_hash.buscarUsuario("angelo"));
        nuevo_hash.buscarUsuario("angelo").getpPrim().setTiempo(9);
        monticulo.imprimirMonticulo();
        monticulo.cancelarImpresion(nuevo_hash.buscarUsuario("angelo").getpPrim().getpSig());
        monticulo.imprimirMonticulo();
        monticulo.imprimirDocumento();
        monticulo.imprimirMonticulo();
        monticulo.imprimirDocumento();
        monticulo.cancelarImpresion(nuevo_hash.buscarUsuario("angelo").getpPrim().getpSig());
        monticulo.imprimirDocumento();
        
        Ventana1 ventana = new Ventana1();
        ventana.setVisible(true);
    }
}
