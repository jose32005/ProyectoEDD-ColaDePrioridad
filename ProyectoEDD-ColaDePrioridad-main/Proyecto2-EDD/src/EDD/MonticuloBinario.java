/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import Ejecutable.main;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author joses
 */
public class MonticuloBinario {

    private Documento[] monticulo;
    private int capacidad;
    private int tamaño;
    private int tInicial;
    private final SingleGraph graph;

    public MonticuloBinario() {
        this.capacidad = 31;
        this.tamaño = 0;
        this.monticulo = new Documento[31];
        this.tInicial = (int) (System.nanoTime() / 1000000000);
        this.graph = new SingleGraph("ColaVisualizada");

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
        if (tamaño <= 0) {
            JOptionPane.showMessageDialog(main.ventana, "Monticulo Vacio");
            return null;
        }

        Documento minimo = monticulo[0];
        if (minimo == null) {
            System.out.println("Error: El mínimo es null.");
            return null;
        } else {
            minimo.setTiempo(-1);

            monticulo[0] = monticulo[this.tamaño - 1];
            Orden(0);
            tamaño--;
            return minimo;
        }

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

    public void visualizarMonticulo() {
        SwingUtilities.invokeLater(() -> {
            mxGraph graph = new mxGraph() {
                // Desactivar la interacción del ratón
                public boolean isCellMovable(Object cell) {
                    return true;
                }

                public boolean isCellConnectable(Object cell) {
                    return false;
                }

            };
            Object parent = graph.getDefaultParent();

            graph.getModel().beginUpdate();
            try {
                dibujarMonticulo(graph, parent, 270, 100, 30, 30);
            } finally {
                graph.getModel().endUpdate();
            }

            mxGraphComponent graphComponent = new mxGraphComponent(graph) {
                // Desactivar la interacción del ratón
                public boolean isForceMarqueeEvent(MouseEvent e) {
                    return false;
                }
            };
            JFrame frame = new JFrame("Visualizador de Montículo Binario");
            frame.getContentPane().add(graphComponent);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Ajustes para el tamaño de la ventana
            frame.setSize(900, 900);
            frame.setLocationRelativeTo(null);  // Centrar en la pantalla

            frame.setVisible(true);
        }
        );
    }

    private void dibujarMonticulo(mxGraph graph, Object parent, int x, int y, int ancho, int alto) {
        graph.setCellsSelectable(true);
        graph.setConnectableEdges(false);
        int nivel = 1;
        int nivelAnterior = 0;
        int yOffset = alto * 2;

        Object[] vertices = new Object[tamaño];

        int anchoVentana = 900;
        int anchoMonticulo = tamaño * (ancho * 2);

        int ajusteX = (anchoVentana - anchoMonticulo) / 2;

        for (int i = 0; i < tamaño; i++) {
            int actualX;
            int actualY;

            if (i == 0) {
                actualX = x + ajusteX;
                actualY = y;
            } else {
                if (nivel != nivelAnterior) {
                    nivelAnterior = nivel;
                }
                int padreIndice = obtenerPadre(i);
                int ejeXPadre = (int) graph.getCellGeometry(vertices[padreIndice]).getCenterX();

                if (obtenerHijoIzquierdo(padreIndice) == i) {
                    // El nodo actual es el hijo izquierdo del padre
                    actualX = ejeXPadre - ancho * 2 - ajusteX;
                } else {
                    // El nodo actual es el hijo derecho del padre
                    int anchoPadre = (int) graph.getCellGeometry(vertices[padreIndice]).getWidth();
                    ajusteX /= 2;
                    actualX = ejeXPadre - anchoPadre + ancho * 2 + ajusteX;
                }
                actualY = y + nivel * yOffset;

            }

            vertices[i] = graph.insertVertex(parent, null, monticulo[i].getNombre(), actualX, actualY, ancho, alto);

            if (i != 0) {
                int padreIndice = obtenerPadre(i);
                graph.insertEdge(parent, null, "", vertices[padreIndice], vertices[i], "startArrow=none");
            }

            if (i == Math.pow(2, nivel) - 2) {
                nivel++;
            }
        }
    }

    public void cargarColaEnGrafo() {
        graph.clear();  // Limpia el grafo antes de recargar

        // Constante para el espacio horizontal entre nodos
        final double espacioHorizontal = 10.0;

        // Recorre los nodos del montículo y agrégales al grafo
        for (int i = 0; i < tamaño; i++) {
            Documento documento = monticulo[i];
            Node nodo = graph.addNode(Integer.toString(i));

            // Establece la posición x de cada nodo para organizarlos horizontalmente
            double posX = i * espacioHorizontal;
            nodo.setAttribute("xyz", posX, 0.0, 0.0);  // xyz son las coordenadas en GraphStream
            nodo.setAttribute("label", documento.getNombre());
        }

        // Conecta los nodos según la estructura del montículo binario
        for (int i = 0; i < tamaño; i++) {
            int izquierdo = obtenerHijoIzquierdo(i);
            int derecho = obtenerHijoDerecho(i);

            if (izquierdo < tamaño) {
                graph.addEdge("Edge_" + i + "_" + izquierdo, Integer.toString(i), Integer.toString(izquierdo));
            }

            if (derecho < tamaño) {
                graph.addEdge("Edge_" + i + "_" + derecho, Integer.toString(i), Integer.toString(derecho));
            }
        }
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.stylesheet", "node { shape: box; size-mode: fit; fill-color: lightblue; text-size: 25; text-font: 'Verdana'; }");

    }

    public void visualizarGrafo() {
        cargarColaEnGrafo();
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        viewer.getDefaultView().enableMouseOptions();
        viewer.disableAutoLayout();
    }

    public void Orden(int indice) {
        int indiceMinimo = indice;
        int izquierdo = obtenerHijoIzquierdo(indice);
        int derecho = obtenerHijoDerecho(indice);

        if (izquierdo < tamaño && monticulo[izquierdo] != null
                && (monticulo[indiceMinimo] == null || monticulo[izquierdo].getTiempo() >= monticulo[indiceMinimo].getTiempo())) {
            indiceMinimo = izquierdo;
        }

        if (derecho < tamaño && monticulo[derecho] != null
                && (monticulo[indiceMinimo] == null || monticulo[derecho].getTiempo() >= monticulo[indiceMinimo].getTiempo())) {
            indiceMinimo = derecho;
        }

        if (indice != indiceMinimo) {
            intercambiar(indice, indiceMinimo);
            Orden(indiceMinimo);
        }
    }



    public void ordenarMonticuloImprimir() {
        int temp = tamaño;
        for (int i = tamaño / 2 - 1; i >= 0; i--) {
            Orden(i);
        }
        for (int i = tamaño - 1; i > 0; i--) {
            intercambiar(0, i);
            tamaño--;
            Orden(0);
        }
        tamaño = temp;
    }

    public void imprimirDocumento() {
        if (tamaño <= 0) {
            JOptionPane.showMessageDialog(main.ventana, "Monticulo Vacio");
        } else {
            ordenarMonticuloImprimir();
            Documento doc = this.eliminarMinimo();
            System.out.println("Imprimiendo documento: " + doc.getNombre() + " con tiempo " + doc.getTiempo());
            JOptionPane.showMessageDialog(main.ventana, "Se ha impreso con exito el archivo: " + doc.getNombre());
        }
    }

    public void cancelarImpresion(Documento doc) {
        if (doc.getTiempo() != -1) {
            // Elimina el documento del montículo sin cambiar su tiempo
            eliminarDocumento(doc);
            // Restablece el tiempo a -1 después de eliminar el documento
            doc.setTiempo(-1);
            JOptionPane.showMessageDialog(main.ventana, doc.getNombre() + " eliminado con exito de la cola");
        } else {
            System.out.println("El documento no está en cola");
        }
    }

    private void eliminarDocumento(Documento doc) {
        int i;
        for (i = 0; i < tamaño; i++) {
            if (monticulo[i] == doc) {
                break;
            }
        }

        if (i < tamaño) {
            monticulo[i] = monticulo[tamaño - 1];
            tamaño--;
            Orden(i);
        } else {
            System.out.println("El documento no está en cola");
        }
    }
}
