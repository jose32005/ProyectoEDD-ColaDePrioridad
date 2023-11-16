/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejecutable;
import java.util.Timer;
import javax.swing.JOptionPane;
/**
 *
 * @author joses
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int inicio = (int) (System.nanoTime()/1000000000);
        System.out.println("Hola mundo"); 
        
        System.out.println("Tiempo transcurrido: ");
        
        int ultimo = (int) (System.nanoTime()/1000000000);
        
        int total = (ultimo - inicio);
              
        
        System.out.println(total);
   }
    }