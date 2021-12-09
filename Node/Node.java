package Node;

import java.util.ArrayList;

//Heavily inspired from the graph used here: https://www.geeksforgeeks.org/graph-and-its-representations/
//12.8.21: Though original inspiration came from the url above, most of this node structure has been ripped apart and reworked
//at nauseum to attempt to fit my needs as I worked through the problem.
public class Node{
    public Integer id;
   // public int dest;
    public Float weight;

    public Node(int id, float weight){
        this.id = id;
        this.weight = weight;
    }

}
