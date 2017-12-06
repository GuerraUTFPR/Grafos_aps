/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.Connection;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author guerra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        /*
        
        O token de aplicação parece que nao da o acesso total, então gerei um token no site 
        do developers e utilizei.
        
        o raio de coleta está para dois hops a partir da página inicial!        
        
         */
        final String accessToken = "EAACEdEose0cBAMEk2Gg2uA3QS1IvZB3bgLjfW3T4RZBKctUWE91kw1ZC7Bm2ZAZCFQ3ZCZByYRerlhB1Is2ulC6GmBjyBY1jiogwpZAw6graGgTYZAAEBhx0vTZC4DT4I2x7c6ySB3nFBk4bAB6Iy6OrA3VamYolBl57oZAoWK800FWEnuNj6pWk5RctuRQRHjAAgVkz2UptBHvHAZDZD";
        //final String accessToken = "1679649692066290|37f5ed9d9357dff29314217f82fc3228";

        Grafo graph = new Grafo();

        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_11);

        System.out.println("Digite o nome da página inicial:");
        Scanner scan = new Scanner(System.in);
        String readder = scan.nextLine();
        System.out.println("Digite o alcance do raio de busca");
        int raio = scan.nextInt();
        System.out.println("Iniciando a busca...");

        try {
            //pegando o Json da página solicitada.
            JsonObject selectedPage = facebookClient.fetchObject(readder, JsonObject.class);

            //criando o vértices correspondente a página escolhida e adicionando ele no grafo.
            //Vertice vezao = new Vertice(selectedPage.getString(readder, readder), readder)
            
            String defaultString = null;
            
            Vertice pageV = new Vertice(selectedPage.getString("id",defaultString), selectedPage.getString("name",defaultString));
            graph.addVertice(pageV);

            LinkedList<Vertice> verticesNaoVisitados = graph.getAllNonVisitedVertex();

            //coletar os likes ao raio de i
            for (int i = 0; i < raio; i++) {
                //enquanto a lista de não visitados estiver com vértices eu vou pegando os likes
                while (!verticesNaoVisitados.isEmpty()) {
                    Vertice v = verticesNaoVisitados.poll();
                    //realizando a conexão para pegar todos os likes da página(vértice).
                    Connection<JsonObject> selectedPageLikes = facebookClient.fetchConnection(v.getID() + "/likes", JsonObject.class);

                    //cria os vértices correspondentes a cada página que curtiu a página escolhida.
                    //também já cria as arestas.
                    for (JsonObject jobj : selectedPageLikes.getData()) {
                        Vertice novoVertice = new Vertice(jobj.getString("id",defaultString), jobj.getString("name",defaultString));
                        graph.addVertice(novoVertice);
                        Aresta novaAresta = new Aresta(v, novoVertice);
                        graph.addAresta(novaAresta);
                        v.addQuemEleCurtiu(jobj.getString("id",defaultString));
                    }
                    graph.setTrueOnVertice(v.getID());
                }
                verticesNaoVisitados = graph.getAllNonVisitedVertex();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //o método checkEdges olha todos os vértices checando se há alguma aresta em comum
        graph.checkEdges();
        
        //método para calcular o grau de cada vértice.
        graph.calculaGrau();
        
        //imprime vértices e arestas do grafo
        graph.imprimeTudo();
        
        //cria o arquivo de entrada pra o GraphViz
        System.out.println("Busca Finalizada.\nCriando o arquivo de saida...");
        graph.toGraphViz();
        
        
        System.out.println("Page com maior grau: "+graph.verticeComMaiorGrau());
    }
}
