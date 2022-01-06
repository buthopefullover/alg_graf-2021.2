import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class CographRecognition {

    // generate actual subset by index sequence
    public static int[] getSubset(List<Integer> set, int[] subset) {

        int[] result = new int[subset.length]; 
        for (int i = 0; i < subset.length; i++) 
            result[i] = set.get(subset[i]);
        return result;
    }

    public static List <int[]> find_subsets(List<Integer> set, int k) {
        List <int[]> subsets = new ArrayList <>();

        int [] s = new int[k];
        if (k <= set.size()){
            for (int i = 0; (s[i] = i) < k - 1; i++); 
            subsets.add(getSubset(set, s)); 
            for(;;) {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && s[i] == set.size() - k + i; i--); 
                if (i < 0) {
                    break;
                }
                s[i]++;                    // increment this item
                for (++i; i < k; i++) {    // fill up remaining items
                    s[i] = s[i - 1] + 1; 
                }
                subsets.add(getSubset(set, s));
            }
        }
        return subsets;
    }  


    public static int has_p4_path (List <Integer> sg, Graph g){
        // acha todos as combinacoes de 4 vértices
        List <int []> comb = find_subsets(sg, 4);
        int counter = 0;


        for (int [] s : comb){
            // transforma o array em lista para usar o contain()
            List<Integer> set = IntStream.of(s)    // returns IntStream
                            .boxed()
                        .collect(Collectors.toList());
            // acha todas as arestas possiveis do subgrafo
            List <int []> possible_edges = find_subsets(set, 2);
            boolean test = false;
            System.out.println(Arrays.toString(s));
            for (int [] c : possible_edges){
                System.out.println(Arrays.toString(c));
            }
    


            int count_edges = 0;  // numero de arestas no subgrafo
		    List <Integer> vertex_cover = new ArrayList<>(); // lista todos os vertices que possuem  vertices adjacentes no subgrafo
            int [] vertex_counter = new int [g.vertexSet.size()]; // count how many edges are adjacent to each node

            for (int [] e : possible_edges){
                if (g.has_edge (e)){
                    count_edges++;
                    if (!(vertex_cover.contains(e[0]))){
                        vertex_cover.add(e[0]);
                    }
                    vertex_counter[e[0]-1]++;
                    if (!(vertex_cover.contains(e[1]))){
                        vertex_cover.add(e[1]);
                    }
                    vertex_counter[e[1]-1]++;
                }
                if (count_edges>=4){
                    break;
                }
            }
            if (count_edges==3 && vertex_cover.size()==4){
                for (int i : vertex_counter){
                    if (i == 3){
                        test = true;
                        break;
                    }
                }
                if (!test){
                    counter++;
                }
            }
            System.out.println(counter);
        }
        System.out.println(counter);
        return counter;
    }

    public static boolean recognize (Graph g){
        List<Integer> keys = new ArrayList<Integer>(g.vertexSet.keySet());
        List <int []> subgraphs = find_subsets(keys, 5);

        for (int [] graph : subgraphs){
            List<Integer> list = IntStream.of(graph)    // returns IntStream
                            .boxed()
                            .collect(Collectors.toList());
            if (has_p4_path(list, g)>1){
                return false;
            }
        }
        return true;

    }
    
}
