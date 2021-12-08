package Node;

//Heavily inspired from the graph used here: https://www.geeksforgeeks.org/graph-and-its-representations/
public class Node {
    public int id;
    public float weight;
    public int dest;
    public String street;

    public Node(int id, float weight, int dest, String street){
        this.id = id;
        this.weight = weight;
        this.dest = dest;
        this.street = street;
    }
}
