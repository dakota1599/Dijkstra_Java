package Node;

public class CompleteEdge {
    public Integer src;
    public Integer dest;
    public Float weight;
    public String street;

    public CompleteEdge(int src, int dest, float weight, String street){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.street = street;
    }
}
