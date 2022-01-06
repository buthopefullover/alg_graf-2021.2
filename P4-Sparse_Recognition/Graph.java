import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;

public class Graph {

    // Guardo os vértices do grafo em um map int -> Vertex onde o int é o id do vértice
    HashMap<Integer, Vertex> vertexSet;
    List <int []> edgeSet = new ArrayList <>();

    // Construtor de Graph
    public Graph() {
        vertexSet = new HashMap<>();
    }

    // Adiciona um vértice com identificador id
    public void add_vertex(int id) {
        if(this.vertexSet.get(id) == null) {
            Vertex v = new Vertex(id);
            vertexSet.put(id, v);
        }
    }

    // Adiciona uma aresta entre dois vértices com peso
    public void add_edge(int id1, int id2) {
        int [] edge = {id1, id2};
        Vertex v1 = vertexSet.get(id1);
        Vertex v2 = vertexSet.get(id2);
        v1.add_neighbor( v2);
        v2.add_neighbor( v1);
        edgeSet.add(edge);
    }

    public boolean has_edge (int[] edge){
        for (int [] e : edgeSet){
            if (Arrays.equals(e, edge)){
                return true;
            }
        }
        return false;
    }


    // Preenche o grafo a partir de um arquivo de texto arq_ent
    public void open_text( String arq_ent ) {
		String thisLine = null;
        vertexSet = new HashMap<Integer,Vertex>();
		String pieces[ ];

		try {
		    FileReader file_in = new FileReader( arq_ent );
		    BufferedReader br = new BufferedReader( file_in );
		    while ( (thisLine = br.readLine( )) != null) {
			    
			    thisLine = thisLine.replaceAll("\\s+", " ");
			    pieces = thisLine.split(" ");
			    int v1 = Integer.parseInt( pieces[0] );
			    this.add_vertex( v1 );
			    for( int i = 2; i < pieces.length; i ++ ) {

                    // Lê o id do vizinho de v1 e o peso da aresta
                    int v2 = Integer.parseInt( pieces[ i ] );

					this.add_vertex( v2 );
					this.add_edge( v1, v2);
				}				    
            }
            br.close();
			System.out.print("Arquivo lido com sucesso.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

    // Printa o grafo, informando o custo
    public void print() {
		System.out.print("\n\n -------------------------------");
        if( this.vertexSet.size() == 0 ) {
			System.out.printf("\n\n Conjunto de vértices vazio");
			System.out.print("\n\n -------------------------------");
			return;
        }
        
        for( Vertex v : vertexSet.values())
            v.print();
		System.out.print("\n\n -------------------------------");
    }
}
