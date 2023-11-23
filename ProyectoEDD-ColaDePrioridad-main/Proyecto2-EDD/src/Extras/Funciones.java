/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extras;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author joses
 */
public class Funciones {

    public void abrir_archivo() {
        String aux = "";
        String texto = "";
        try {
            /**
             * llamamos el metodo que permite cargar la ventana
             */
            JFileChooser file = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".CSV", "csv");
            file.setFileFilter(filter);
            Component FileChooser = null;
            file.showOpenDialog(FileChooser);
            /**
             * abrimos el archivo seleccionado
             */
            File abre = file.getSelectedFile();

            /**
             * recorremos el archivo, lo leemos para plasmarlo en el area de
             * texto
             */
            if (abre != null) {
                FileReader archivos = new FileReader(abre);
                try (BufferedReader lee = new BufferedReader(archivos)) {
                    while ((aux = lee.readLine()) != null) {
                        texto += aux + "\n";
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
        try {
            if (!texto.equals("")) {
                PrintWriter pw = new PrintWriter("test\\db.csv");
                pw.print(texto);
                pw.close();
                JOptionPane.showMessageDialog(null,
                        "Archivo cargado exitosamente",
                        "", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null,
                    "Error en la carga del archivo",
                    "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

}
