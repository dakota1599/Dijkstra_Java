package Node;

import java.util.ArrayList;

public class Graph{

    public ArrayList<ArrayList<Edge>> edges;
    public int size;

    public Graph(ArrayList<Edge> edges, int size){
        this.edges = new ArrayList<>();
        this.size = size;

        for(int i = 0; i < size; i++){
            this.edges.add(new ArrayList<>());
        }

        for(Edge cEdge: edges){
            this.edges.get(cEdge.src).add(cEdge);
        }
    }

}
