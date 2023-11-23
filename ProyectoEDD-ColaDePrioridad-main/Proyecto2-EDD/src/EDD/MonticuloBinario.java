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

    private Documento[] monticulo;
    private int capacidad;
    private int tamaño;
    private int tInicial;

    public MonticuloBinario() {
        this.capacidad = 31;
        this.tamaño = 0;
        this.monticulo = new Documento[31];
        this.tInicial = (int) (System.nanoTime() / 1000000000);
    }

    public int obtenerPadre(int i) {
        return (int) Math.floor((i - 1) / 2);
    }

    public int obtenerHijoIzquierdo(int i) {
        return 2 * i + 1;
    }

    public int obtenerHijoDerecho(int i) {
        return 2 * i + 2;
    }

    public void intercambiar(int i, int j) {
        Documento temp = monticulo[i];
        monticulo[i] = monticulo[j];
        monticulo[j] = temp;
    }

    public void insertar(Documento doc, Usuario usuario) {
        if (tamaño >= capacidad) {
            System.out.println("El montículo está lleno, no se puede insertar.");
            return;
        }

        this.generarEtiquetaTiempo(doc, usuario);
        monticulo[tamaño] = doc;
        int indice = tamaño;
        tamaño++;

        while ((indice > 0) && (monticulo[indice].getTiempo() < monticulo[obtenerPadre(indice)].getTiempo())) {
            intercambiar(indice, obtenerPadre(indice));
            indice = obtenerPadre(indice);
        }
    }

    public Documento eliminarMinimo() {

        Documento minimo = monticulo[0];
        minimo.setTiempo(-1);
        monticulo[0] = monticulo[tamaño - 1];
        tamaño--;
        Orden(0);

        return minimo;
    }

    public void Orden(int indice) {
        int indiceMinimo = indice;
        int izquierdo = obtenerHijoIzquierdo(indice);
        int derecho = obtenerHijoDerecho(indice);

        if (izquierdo < tamaño && monticulo[izquierdo].getTiempo() < monticulo[indiceMinimo].getTiempo()) {
            indiceMinimo = izquierdo;
        }

        if (derecho < tamaño && monticulo[derecho].getTiempo() < monticulo[indiceMinimo].getTiempo()) {
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
            System.out.print(monticulo[i].getNombre() + " " + monticulo[i].getTiempo() + " ");
        }
        System.out.println();
    }

    public void generarEtiquetaTiempo(Documento doc, Usuario usuario) {
        int tActual = (int) (System.nanoTime() / 1000000000);
        int etiqueta = tActual - this.tInicial;
        if (usuario.getTipo().equals("prioridad_alta")) {
            etiqueta = (int) etiqueta / 3;
        } else if (usuario.getTipo().equals("prioridad_media")) {
            etiqueta = (int) etiqueta / 2;
        }
        doc.setTiempo(etiqueta);
    }

    public void cancelarImpresion(Documento doc) {
        if (doc.getTiempo() != -1) {
            doc.setTiempo(0);
            this.Orden(0);
            this.eliminarMinimo();
        } else {
            System.out.println("El documento no esta en cola");
        }
    }

    public void imprimirDocumento() { //Liberar Impresora
        if (tamaño <= 0) {
            System.out.println("El montículo está vacío, no se puede eliminar el mínimo.");
        } else {
            Documento doc = this.eliminarMinimo();
            System.out.println("Documento impreso: " + doc.getNombre());
        }

    }
}
