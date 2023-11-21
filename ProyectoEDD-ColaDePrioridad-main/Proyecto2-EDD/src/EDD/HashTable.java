/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author joses
 */
public class HashTable {

    private Usuario[] arreglo;
    private int tamaño;

    public HashTable(int n) {
        arreglo = new Usuario[n];
        tamaño = n;
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
        int i = this.funHash(nombre);
        if (this.EsEspacioVacio(i)) {
            this.arreglo[i] = nuevo_usuario;
        } else {
            Usuario aux = arreglo[i];
            while (aux.getpSig() != null) {
                aux = aux.getpSig();
            }
            aux.setpSig(nuevo_usuario);
        }
    }

    public void EliminarUsuario(String nombre) {
        int i = this.funHash(nombre);
        Usuario aux = arreglo[i];
        if ((aux.getNombre().equals(nombre)) && (aux.getpSig() == null)) {
            arreglo[i] = null;
        } else {
            while (aux.getpSig() != null) {
                Usuario prev = aux;
                aux = aux.getpSig();
                if (aux.getNombre().equals(nombre)) {
                    prev.setpSig(aux.getpSig());
                    aux.setpSig(null);
                    break;
                } else {
                    System.out.println("El usuario no existe");
                    break;
                }

            }
        }

    }

}
