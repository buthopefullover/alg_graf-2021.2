import java.util.Scanner;

/*
    Para importar um grafo por arquivo de texto, modifique o arquivo "grafo.txt"
*/
public class Init {
    public static void main(String args[ ]) {
        
        Scanner scan = new Scanner(System.in);
        
        String menu = "\n\n 0 Sair \n 1 Print \n 2 Ler de arquivo texto \n "
                    + "3 Aplicar o algoritmo de Kruskal e imprimir resultado \n Escolha a opção: ";
        
        Graph g = new Graph();
        
        while( true ) {
            System.out.printf( menu );
            int choice = scan.nextInt();
            switch( choice ) {
                case 0:
                    scan.close();
                    return;
                case 1:
                    g.print();
                    break;
                case 2:
					String arq_ent = "Grafo.txt";
					g.open_text( arq_ent );
                    break;
                case 3:
                    System.out.print(CographRecognition.recognize(g));
                    break;
            }
        }
    }
}
