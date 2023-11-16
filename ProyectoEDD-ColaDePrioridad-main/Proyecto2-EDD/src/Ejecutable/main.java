/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejecutable;

/**
 *
 * @author joses
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        long inicio = System.nanoTime();
        System.out.println("Hola mundo"); 
        
      
        System.out.println("Tiempo transcurrido: ");
        
        long ultimo = System.nanoTime();
        
        double total = ultimo - inicio / 1_000_000_000.0;
        
        System.out.println(total);
   }
    }