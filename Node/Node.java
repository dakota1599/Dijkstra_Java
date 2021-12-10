package Node;

import java.util.ArrayList;

//Heavily inspired from the graph used here: https://www.geeksforgeeks.org/graph-and-its-representations/
//12.8.21: Though original inspiration came from the url above, most of this node structure has been ripped apart and reworked
//at nauseum to attempt to fit my needs as I worked through the problem.
public class Node{
    public Integer id;
   // public int dest;
    public Edge neighbors = null;

    public Node(int id){
        this.id = id;
    }
    public Node(int id, Edge edge){
        this.id = id;
        this.neighbors = edge;
    }

}
