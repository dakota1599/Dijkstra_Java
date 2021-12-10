package Node;

import java.util.ArrayList;
//Regular node class
public class Node{
    public Integer id;
    public Edge neighbors = null;

    public Node(int id){
        this.id = id;
    }
    public Node(int id, Edge edge){
        this.id = id;
        this.neighbors = edge;
    }

}
