package Node;

public class Edge
{
    public int src;
    public int dest;
    public float weight;
    public String street;
    public Edge(int src, int dest, float weight, String street)
    {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.street = street;
    }
    public Edge(){

    }
}