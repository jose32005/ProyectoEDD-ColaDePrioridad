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
     */
    public static HashTable hashtable;
    public static MonticuloBinario monticulo;
    public static Ventana1 ventana;
    public static Funciones func;
    
    public static void main(String[] args) throws IOException {
        
        hashtable = new HashTable(20);
        monticulo = new MonticuloBinario();
        func = new Funciones();
        func.abrir_archivo();
        hashtable.leer_db();
        hashtable.imprimir_hashtable();
        ventana = new Ventana1();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
    }
}
