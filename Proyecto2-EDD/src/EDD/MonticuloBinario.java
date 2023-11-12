/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author joses
 */
public class MonticuloBinario {

    private int[] monticulo;
    private int capacidad;
    private int tamaño;

    public MonticuloBinario (int capacidad) {
        this.capacidad = capacidad;
        this.tamaño = 0;
        this.monticulo = new int[capacidad];
    }

    public int obtenerPadre(int i) {
        return (i - 1) / 2;
    }

    public int obtenerHijoIzquierdo(int i) {
        return 2 * i + 1;
    }

    public int obtenerHijoDerecho(int i) {
        return 2 * i + 2;
    }

    public void intercambiar(int i, int j) {
        int temp = monticulo[i];
        monticulo[i] = monticulo[j];
        monticulo[j] = temp;
    }

    public void insertar(int elemento) {
        if (tamaño >= capacidad) {
            System.out.println("El montículo está lleno, no se puede insertar.");
            return;
        }

        monticulo[tamaño] = elemento;
        int indice = tamaño;
        tamaño++;

        while (indice > 0 && monticulo[indice] < monticulo[obtenerPadre(indice)]) {
            intercambiar(indice, obtenerPadre(indice));
            indice = obtenerPadre(indice);
        }
    }

    public int eliminarMinimo() {
        if (tamaño <= 0) {
            throw new IllegalStateException("El montículo está vacío, no se puede eliminar el mínimo.");
        }

        int minimo = monticulo[0];
        monticulo[0] = monticulo[tamaño - 1];
        tamaño--;
        Orden(0);

        return minimo;
    }

    public void Orden(int indice) {
        int indiceMinimo = indice;
        int izquierdo = obtenerHijoIzquierdo(indice);
        int derecho = obtenerHijoDerecho(indice);

        if (izquierdo < tamaño && monticulo[izquierdo] < monticulo[indiceMinimo]) {
            indiceMinimo = izquierdo;
        }

        if (derecho < tamaño && monticulo[derecho] < monticulo[indiceMinimo]) {
            indiceMinimo = derecho;
        }

        if (indice != indiceMinimo) {
            intercambiar(indice, indiceMinimo);
            Orden(indiceMinimo);
        }
    }

    public boolean estaVacio() {
        return tamaño == 0;
    }

    public void imprimirMonticulo() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print(monticulo[i] + " ");
        }
        System.out.println();
    }
}
