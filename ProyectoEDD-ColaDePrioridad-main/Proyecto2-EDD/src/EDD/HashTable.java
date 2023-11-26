/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import Ejecutable.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author joses
 */
public class HashTable {

    public Usuario[] arreglo;
    private int tamaño;
    private int num_usuarios;

    public HashTable(int n) {
        arreglo = new Usuario[n * 2];
        tamaño = n * 2;
        num_usuarios = 0;
    }

    // n = tamano del arreglo
    // i =  indice del arreglo
    public boolean EsEspacioVacio(int i) {
        return this.getArreglo()[i] == null;
    }

    public int funHash(String nombre) {
        int indice = 0;
        for (int i = 0; i < nombre.length(); i++) {
            indice += (int) nombre.charAt(i);
        }
        indice = indice % this.getTamaño();
        return indice;
    }

    public void InsertarUsuario(String nombre, String tipo, boolean nuevo) {
        nuevo = nuevo;
        Usuario nuevo_usuario = new Usuario(nombre, tipo);
        boolean usuario_existente = false;
        int i = this.funHash(nombre);
        if (this.EsEspacioVacio(i)) {
            this.getArreglo()[i] = nuevo_usuario;
        } else {
            if (getArreglo()[i].getNombre().equals(nuevo_usuario.getNombre())) {
                JOptionPane.showMessageDialog(main.ventana, "El usuario: " + nombre + " ya existe");
                usuario_existente = true;
            } else {
                Usuario aux = getArreglo()[i];
                while (aux.getpSig() != null) {
                    if (aux.getNombre().equals(nuevo_usuario.getNombre())) {
                        JOptionPane.showMessageDialog(main.ventana, "El usuario: " + nombre + " ya existe");
                        usuario_existente = true;
                    } else {
                        aux = aux.getpSig();
                    }

                }
                if (usuario_existente == false) {
                    aux.setpSig(nuevo_usuario);
                }
            }

        }
        if (usuario_existente == false) {
            this.setNum_usuarios(+1);
            
        }
        if (nuevo == true && usuario_existente == false) {
            JOptionPane.showMessageDialog(main.ventana, "Usuario: " + nombre + " agregado con exito");           
        }
        
    }

    public void EliminarUsuario(String nombre) {
        int i = this.funHash(nombre);
        Usuario aux = getArreglo()[i];
        if ((aux.getNombre().equals(nombre)) && (aux.getpSig() == null)) {
            getArreglo()[i] = null;
            this.setNum_usuarios(- 1);
            JOptionPane.showMessageDialog(main.ventana, "Usuario: " + nombre +" eliminado con exito");
        } else {
            while (aux.getpSig() != null) {
                Usuario prev = aux;
                aux = aux.getpSig();
                if (aux.getNombre().equals(nombre)) {
                    prev.setpSig(aux.getpSig());
                    aux.setpSig(null);
                    this.setNum_usuarios(- 1);
                    JOptionPane.showMessageDialog(main.ventana, "Usuario: " + nombre +" eliminado con exito");
                    break;
                } else {
                    System.out.println("El usuario no existe");
                    break;
                }

            }
        }

    }

    public Usuario buscarUsuario(String nombre) {
        if (getArreglo()[this.funHash(nombre)] != null) {
            Usuario usuario = getArreglo()[this.funHash(nombre)];
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            } else {
                while (usuario.getpSig() != null) {
                    usuario = usuario.getpSig();
                    if (usuario.getNombre().equals(nombre)) {
                        return usuario;
                    }
                }
                System.out.println("Usuario no encontrado");
                return null;
            }
        } else {
            System.out.println("Usuario no encontrado");
            return null;
        }

    }

    public void leer_db() throws IOException {
        String line;
        String usuarios = "";
        String path = "test\\db.csv";
        File file = new File(path);
        System.setProperty("org.graphstream.ui", "swing");
        
        for (int i = 0; i < this.tamaño; i++) {
            arreglo[i] = null;
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                FileReader fr = new FileReader(file);
                try (BufferedReader br = new BufferedReader(fr)) {
                    while ((line = br.readLine()) != null) {
                        if (!line.isEmpty()) {
                            usuarios += line + "\n";
                        }
                    }
                    if (!"".equals(usuarios)) {
                        String[] usuarios_split = usuarios.split("\n");
                        for (int i = 1; i < usuarios_split.length; i++) {
                            String nuevo_elemento = usuarios_split[i];
                            String[] nueva_relacion = nuevo_elemento.split(", ");
                            this.InsertarUsuario(nueva_relacion[0], nueva_relacion[1], false);
                        }

                    }
                }
            }

        } catch (IOException err) {
            JOptionPane.showMessageDialog(null,
                    "Error al leer archivo",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /**
    * Imprime la lista de usuarios en la tabla de dispersión.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public void recorrer_hashtable() {
        if (this.getNum_usuarios() == 0) {
            System.out.println("La base de usuarios esta vacia");
        } else {
            for (int i = 0; i < getTamaño(); i++) {
                if (getArreglo()[i] != null) {
                    System.out.println(getArreglo()[i].getNombre() + i);
                    Usuario aux = getArreglo()[i].getpSig();
                    while (aux != null) {
                        System.out.println(aux.getNombre() + i);
                        aux = aux.getpSig();
                    }
                }
            }
        }
    }

    /**
     * @return the arreglo
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public Usuario[] getArreglo() {
        return arreglo;
    }

    /**
     * @param arreglo the arreglo to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setArreglo(Usuario[] arreglo) {
        this.arreglo = arreglo;
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
     * @return the num_usuarios
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public int getNum_usuarios() {
        return num_usuarios;
    }

    /**
     * @param num_usuarios the num_usuarios to set
     * @author S. Estefania, G. Angelo y S. Jose
     */
    public void setNum_usuarios(int num_usuarios) {
        this.num_usuarios = num_usuarios;
    }
    
    
}
