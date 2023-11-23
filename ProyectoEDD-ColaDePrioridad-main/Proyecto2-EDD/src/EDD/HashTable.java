/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

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

    private Usuario[] arreglo;
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
        return this.arreglo[i] == null;
    }

    public int funHash(String nombre) {
        int indice = 0;
        for (int i = 0; i < nombre.length(); i++) {
            indice += (int) nombre.charAt(i);
        }
        indice = indice % this.tamaño;
        return indice;
    }

    public void InsertarUsuario(String nombre, String tipo) {
        Usuario nuevo_usuario = new Usuario(nombre, tipo);
        boolean usuario_existente = false;
        int i = this.funHash(nombre);
        if (this.EsEspacioVacio(i)) {
            this.arreglo[i] = nuevo_usuario;
        } else {
            if (arreglo[i].getNombre().equals(nuevo_usuario.getNombre())) {
                System.out.println("El usuario ya existe");
                usuario_existente = true;
            } else {
                Usuario aux = arreglo[i];
                while (aux.getpSig() != null) {
                    if (aux.getNombre().equals(nuevo_usuario.getNombre())) {
                        System.out.println("El usuario ya existe");
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
            this.num_usuarios = +1;
        }
    }

    public void EliminarUsuario(String nombre) {
        int i = this.funHash(nombre);
        Usuario aux = arreglo[i];
        if ((aux.getNombre().equals(nombre)) && (aux.getpSig() == null)) {
            arreglo[i] = null;
            this.num_usuarios = - 1;
        } else {
            while (aux.getpSig() != null) {
                Usuario prev = aux;
                aux = aux.getpSig();
                if (aux.getNombre().equals(nombre)) {
                    prev.setpSig(aux.getpSig());
                    aux.setpSig(null);
                    this.num_usuarios = - 1;
                    break;
                } else {
                    System.out.println("El usuario no existe");
                    break;
                }

            }
        }

    }

    public Usuario buscarUsuario(String nombre) {
        if (arreglo[this.funHash(nombre)] != null) {
            Usuario usuario = arreglo[this.funHash(nombre)];
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
                            this.InsertarUsuario(nueva_relacion[0], nueva_relacion[1]);
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

    public void imprimir_hashtable() {
        if (this.num_usuarios == 0) {
            System.out.println("La base de usuarios esta vacia");
        } else {
            System.out.println("Lista de usuarios");
            for (int i = 0; i < tamaño; i++) {
                if (arreglo[i] != null) {
                    System.out.println(arreglo[i].getNombre() + i);
                    Usuario aux = arreglo[i].getpSig();
                    while (aux != null) {
                        System.out.println(aux.getNombre() + i);
                        aux = aux.getpSig();
                    }
                }
            }
        }

    }
}
