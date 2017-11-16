/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Grafo {

    List<Vertice> vertices;
    List<Aresta> arestas;

    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public boolean verticeContains(String idToCheck) {
        for (Vertice v : this.vertices) {
            if (v.getID().equals(idToCheck)) {
                return true;
            }
        }
        return false;
    }

    public boolean arestaContains(String from, String to) {
        for (Aresta a : this.arestas) {
            if (a.getV1().getID().equals(from) && a.getV2().getID().equals(to)) {
                return true;
            } else if (a.getV1().getID().equals(to) && a.getV2().getID().equals(from)) {
                return true;
            } else if (a.getV2().getID().equals(from) && a.getV1().getID().equals(to)) {
                return true;
            } else if (a.getV2().getID().equals(to) && a.getV1().getID().equals(from)) {
                return true;
            }
        }
        return false;
    }

    public void addVertice(Vertice v) {
        if (!this.verticeContains(v.getID())) {
            this.vertices.add(v);
        }
    }

    public void addAresta(Aresta a) {
        if (!this.arestaContains(a.getV1().getID(), a.getV2().getID())) {
            this.arestas.add(a);
        }
    }

    public void imprimeVertices() {
        if (this.vertices.isEmpty()) {
            System.out.println("Sem vertices!");
        } else {

            for (Vertice v : this.getVertices()) {
                System.out.println(v.getID() + " | " + v.getPageName()+ " | " + v.isVisited());
            }
        }
    }

    public void imprimeArestas() {
        if (this.arestas.isEmpty()) {
            System.out.println("Sem arestas!");
        } else {
            for (Aresta a : this.getArestas()) {
                System.out.println("[" + a.getV1().getPageName() + "] - [" + a.getV2().getPageName() + "]");
            }
        }
    }
    
    public LinkedList<Vertice> getAllNonVisitedVertex(){
        LinkedList<Vertice> novaLista = new LinkedList<Vertice>();
        for (Vertice v : this.getVertices()){
            if (!v.isVisited()){
                novaLista.add(v);
            }
        }
        return novaLista;
    }
    
    public void setTrueOnVertice(String verticeID){
        for (Vertice v : this.getVertices()){
            if (v.getID().equals(verticeID)){
                v.setVisited(true);
            }
        }
    }
    
    public void imprimeTudo() {
        System.out.println("-------------Vertices-------------");
        this.imprimeVertices();
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.println("-------------Arestas-------------");
        this.imprimeArestas();
    }

}
