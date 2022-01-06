import java.util.HashMap;

public class Vertex {
    
    int id; // Id do vértice

    // Guardo os vizinhos do vértice em um map int -> Vertex onde o inteiro é o id do vizinho
    HashMap<Integer, Vertex> vizinhos; 
     

    // Construtor de Vertex
    public Vertex(int id) {
        this.id = id;
        vizinhos = new HashMap<>();
    }

    // Adiciona um vizinho
    public void add_neighbor(Vertex v) {
        if(vizinhos.get(v.id) == null) {
            vizinhos.put(v.id, v);
        }
    }

    // Printa o vértice
    public void print() {
        System.out.print("\nId do vértice " + id + ", Vizinhança: " );
        for( Vertex v : vizinhos.values())
            System.out.print(" " + v.id);
    }
}
