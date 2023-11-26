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
import javax.swing.SwingUtilities;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
    
/**
* La clase MonticuloBinario representa una cola de prioridad.
* Cada elemento en la cola es un documento.
* La clase también incluye un atributo de tipo SingleGraph.
*
* @author S. Estefania, G. Angelo y S. Jose
*/
public class MonticuloBinario {

    private Documento[] monticulo;
    private int capacidad;
    private int tamaño;
    private int tInicial;
    private final SingleGraph graph;
    
    /**
    * Constructor de la clase MonticuloBinario.
    * Inicializa la capacidad, tamaño y el arreglo del montículo.
    * Inicializa el tiempo inicial y crea un nuevo grafo para la visualización.
    * 
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public MonticuloBinario() {
        this.capacidad = 31;
        this.tamaño = 0;
        this.monticulo = new Documento[31];
        this.tInicial = (int) (System.nanoTime() / 1000000000);
        this.graph = new SingleGraph("ColaVisualizada");

    }

    /**
    * Obtiene el índice del padre de un elemento en el montículo.
    *
    * @param i Índice del elemento.
    * @return Índice del padre.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public int obtenerPadre(int i) {
        return (int) Math.floor((i - 1) / 2);
    }
    
    /**
    * Obtiene el índice del hijo izquierdo de un elemento en el montículo.
    *
    * @param i Índice del elemento.
    * @return Índice del hijo izquierdo.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public int obtenerHijoIzquierdo(int i) {
        return 2 * i + 1;
    }

    /**
    * Obtiene el índice del hijo derecho de un elemento en el montículo.
    *
    * @param i Índice del elemento.
    * @return Índice del hijo izquierdo.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public int obtenerHijoDerecho(int i) {
        return 2 * i + 2;
    }

    /**
    * Intercambia dos elementos en el montículo.
    *
    * @param i Índice del primer elemento.
    * @param j Índice del segundo elemento.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public void intercambiar(int i, int j) {
        Documento temp = monticulo[i];
        monticulo[i] = monticulo[j];
        monticulo[j] = temp;
    }

    /**
    * Inserta un documento en el montículo binario.
    *
    * @param doc Documento a insertar.
    * @param usuario Usuario asociado al documento.
    */
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

    /**
    * Elimina y devuelve el documento con el tiempo mínimo del montículo binario.
    *
    * @return Documento con el tiempo mínimo, o null si el montículo está vacío.
    * @author S. Estefania, G. Angelo y S. Jose
    */
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
    
    /**
    * Imprime los documentos en el montículo binario.
    */
    public void imprimirMonticulo() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print(monticulo[i].getNombre() + " " + monticulo[i].getTiempo() + " ");
        }
        System.out.println();
    }

    /**
    * Genera la etiqueta de tiempo para un documento en función del tipo de usuario.
    *
    * @param doc El documento al que se le asignará la etiqueta de tiempo.
    * @param usuario El usuario asociado al documento.
    * @author S. Estefania, G. Angelo y S. Jose
    */
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

    /**
    * Visualiza el montículo binario en una interfaz gráfica utilizando 
    * la biblioteca mxGraph.
    *
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public void visualizarMonticulo() {
        SwingUtilities.invokeLater(() -> {
            mxGraph graph = new mxGraph() {
                
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
                public boolean isForceMarqueeEvent(MouseEvent e) {
                    return false;
                }
            };
            JFrame frame = new JFrame("Visualizador de Montículo Binario");
            frame.getContentPane().add(graphComponent);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(900, 900);
            frame.setLocationRelativeTo(null); 

            frame.setVisible(true);
        }
        );
    }

    /**
    * Dibuja el montículo binario en el gráfico.
    * 
    * @param graph Objeto mxGraph para la representación gráfica.
    * @param parent Nodo principal del gráfico.
    * @param x Coordenada x inicial para el dibujo.
    * @param y Coordenada y inicial para el dibujo.
    * @param ancho Ancho de los nodos que representan los elementos del montículo.
    * @param alto Alto de los nodos que representan los elementos del montículo.
    * @author S. Estefania, G. Angelo y S. Jose
    */
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
                    actualX = ejeXPadre - ancho * 2 - ajusteX;
                } else {
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

    /**
    * Prepara y visualiza el montículo binario en un grafo para su visualización.
    * Vista como cola
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public void cargarColaEnGrafo() {
        graph.clear();  
        final double espacioHorizontal = 10.0;
        
        for (int i = 0; i < tamaño; i++) {
            Documento documento = monticulo[i];
            Node nodo = graph.addNode(Integer.toString(i));

            
            double posX = i * espacioHorizontal;
            nodo.setAttribute("xyz", posX, 0.0, 0.0);  
            nodo.setAttribute("label", documento.getNombre());
        }

        
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
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        viewer.getDefaultView().enableMouseOptions();
        viewer.disableAutoLayout();
    }

    /**
    * Muestra en una nueva ventana la cola de impresion
    * como estructura lineal
    *
    * @param indice Índice del nodo actual en el montículo.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    
    public void visualizarGrafo() {
        cargarColaEnGrafo();
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        viewer.getDefaultView().enableMouseOptions();
        viewer.disableAutoLayout();
    }

    /**
    * Realiza la operación de mantenimiento del montículo después de la eliminación
    * del mínimo. Compara el nodo actual con sus hijos y realiza intercambios según
    * sea necesario para restaurar la propiedad del montículo.
    *
    * @param indice Índice del nodo actual en el montículo.
    * @author S. Estefania, G. Angelo y S. Jose
    */
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


    /**
    * Ordena el montículo utilizando el algoritmo de heapsort e imprime los elementos ordenados.
    * Se realiza la fase de construcción del montículo seguida de la fase de extracción del mínimo
    * repetidamente hasta que el montículo esté completamente ordenado.
    *
    * @author S. Estefania, G. Angelo y S. Jose
    */
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

    /**
    * Imprime el documento con el tiempo más bajo en el montículo.
    * Si el montículo está vacío, se muestra un mensaje indicando que el montículo está vacío.
    * Si hay documentos en el montículo, se ordena el montículo por tiempo y se extrae el documento
    * con el tiempo más bajo para imprimirlo.
    *
    * @author S. Estefania, G. Angelo y S. Jose
    */
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

    /**
    * Cancela la impresión de un documento en la cola.
    * Si el documento está en la cola (su tiempo no es -1), se elimina del montículo sin cambiar su tiempo.
    * Después de eliminar el documento, se restablece su tiempo a -1.
    * Se muestra un mensaje indicando que el documento ha sido eliminado con éxito de la cola.
    * Si el documento no está en cola (su tiempo es -1), se imprime un mensaje indicando que el documento no está en la cola.
    *
    * @param doc Documento que se va a cancelar.
    * @author S. Estefania, G. Angelo y S. Jose
    */
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

    /**
    * Elimina un documento específico de la cola.
    * Busca el documento en el montículo y, si lo encuentra, lo reemplaza con el último documento del montículo.
    * Luego, disminuye el tamaño del montículo y realiza el ordenamiento para mantener la propiedad del montículo binario.
    * Si el documento no está en la cola, imprime un mensaje indicando que el documento no está en la cola.
    *
    * @param doc Documento que se va a eliminar de la cola.
    * @author S. Estefania, G. Angelo y S. Jose    
    */
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
