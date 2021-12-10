package Node;

//Edge class ala a linked list
public class Edge
{
    public int id;
    public float weight;
    //Reference to the next edge.
    public Edge next = null;
    public String street;
    public Edge(int id, float weight, String street)
    {
        this.id = id;
        this.weight = weight;
        this.street = street;
    }
    public Edge(){

    }
}