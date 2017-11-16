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
    public static void main(String[] args) {

        
        /*
        
        O token de aplicação parece que nao da o acesso total, então gerei um token no site 
        do developers e utilizei.
        
        o raio de coleta está para dois hops a partir da página inicial!
        
        FALTA FAZER: um método que verifica a ligação entre os vértices(depois explico pessoalmente)...
        
        */
        final String accessToken = "EAACEdEose0cBAMvsNOYgff0eSpWEW0XOniJLAEh9r8yxwXZBGnHjkZARkLc5VUpxdIZA0WZBnrdPC7p19vtELSjd7NHGISmMAdE76AYxy2eA637wHQMuMBFEh4Lz8y2rzvHkDBGRgDgXx9rJOIWECITNz09nMNGOITNzvWyG0JGZAZAZCMa2HjA7lZCRevu5ggJxgWCkN4DVYwZDZD";
        //final String accessToken = "1679649692066290|37f5ed9d9357dff29314217f82fc3228";

        Grafo graph = new Grafo();

        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_11);

        System.out.println("Digite o nome da página inicial:");
        Scanner scan = new Scanner(System.in);
        String readder = scan.nextLine();

        try {
            //pegando o Json da página solicitada.
            JsonObject selectedPage = facebookClient.fetchObject(readder, JsonObject.class);

            //criando o vértices correspondente a página escolhida e adicionando ele no grafo.
            Vertice pageV = new Vertice(selectedPage.getString("id"), selectedPage.getString("name"));
            graph.addVertice(pageV);

            LinkedList<Vertice> verticesNaoVisitados = graph.getAllNonVisitedVertex();

            //coletar os likes ao raio de i
            for (int i = 0; i < 2; i++) {
                //enquanto a lista de não visitados estiver com vértices eu vou pegando os likes
                while (!verticesNaoVisitados.isEmpty()) {
                    Vertice v = verticesNaoVisitados.poll();
                    //realizando a conexão para pegar todos os likes da página(vértice).
                    Connection<JsonObject> selectedPageLikes = facebookClient.fetchConnection(v.getID() + "/likes", JsonObject.class);

                    //cria os vértices correspondentes a cada página que curtiu a página escolhida.
                    //também já cria as arestas.
                    for (JsonObject jobj : selectedPageLikes.getData()) {
                        Vertice novoVertice = new Vertice(jobj.getString("id"), jobj.getString("name"));
                        graph.addVertice(novoVertice);
                        Aresta novaAresta = new Aresta(v, novoVertice);
                        graph.addAresta(novaAresta);
                    }
                    graph.setTrueOnVertice(v.getID());
                }
                verticesNaoVisitados = graph.getAllNonVisitedVertex();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        graph.imprimeTudo();
    }
}
