/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Vertice {
    private String ID;
    private String pageName;
    private List<String> idsDeQuemEsteCurtiu;
    private boolean visited;

    public Vertice(String ID, String pageName) {
        this.ID = ID;
        this.pageName = pageName;
        this.visited = false;
        this.idsDeQuemEsteCurtiu = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<String> getQuemEsteCurtiu() {
        return idsDeQuemEsteCurtiu;
    }

    public void setQuemEsteCurtiu(List<String> idsDeQuemEsteCurtiu) {
        this.idsDeQuemEsteCurtiu = idsDeQuemEsteCurtiu;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addQuemEleCurtiu(String id){
        this.idsDeQuemEsteCurtiu.add(id);
    }
    
}
