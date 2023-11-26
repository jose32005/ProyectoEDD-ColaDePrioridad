/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JFrame;
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
                dibujarMonticulo(graph, parent, 300, 100, 20, 20);
            } finally {
                graph.getModel().endUpdate();
            }

            mxGraphComponent graphComponent = new mxGraphComponent(graph) {
                // Desactivar la interacción del ratón
                public boolean isForceMarqueeEvent(MouseEvent e) {
                    return false;
                }
            };
            JFrame frame = new JFrame("Montículo Binario Como Arbol");
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
        graph.setCellsDisconnectable(false);
        graph.setConnectableEdges(false);
        graph.setEdgeLabelsMovable(false);
        
        int nivel = 1;
        int nivelAnterior = 0;
        int yOffset = alto * 2;

        Object[] vertices = new Object[tamaño];

        int anchoVentana = 900;
        int anchoMonticulo = tamaño * (ancho * 2);

        int ajusteX = (anchoVentana - anchoMonticulo) / 2;

        String estiloNodo = "rounded=1; shadow=1; fontSize=12; fontColor=#000000;";
        Color[] paletaColores = {Color.PINK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE};  // Puedes ajustar la paleta de colores

        for (int i = 0; i < tamaño; i++) {
            int actualX;
            int actualY;
            Color colorAleatorio = obtenerColorAleatorio(paletaColores, nivel);
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
                    ajusteX = (ajusteX / 2) + 8;
                    actualX = ejeXPadre - anchoPadre + ancho * 2 + ajusteX;
                }
                actualY = y + nivel * yOffset;

            }

            vertices[i] = graph.insertVertex(parent, null, monticulo[i].getNombre(), actualX, actualY, ancho, alto, estiloNodo + "fillColor=" + convertirColorAHexadecimal(colorAleatorio) + ";");

            if (i != 0) {
                int padreIndice = obtenerPadre(i);
                graph.insertEdge(parent, null, "", vertices[padreIndice], vertices[i], "startArrow=none");
            }

            if (i == Math.pow(2, nivel) - 2) {
                nivel++;
            }
        }
    }
    private Color obtenerColorAleatorio(Color[] paletaColores, int nivel) {
        Random rand = new Random(nivel);  // Fija la semilla para obtener el mismo color para el mismo nivel
        return paletaColores[rand.nextInt(paletaColores.length)];
    }


    private String convertirColorAHexadecimal(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
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
        viewer.getDefaultView().enableMouseOptions();

        viewer.disableAutoLayout();
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
