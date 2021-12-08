package Node;

//Heavily inspired from the graph used here: https://www.geeksforgeeks.org/graph-and-its-representations/
//12.8.21: Though original inspiration came from the url above, most of this node structure has been ripped apart and reworked
//at nauseum to attempt to fit my needs as I worked through the problem.
public class Node{
    public Integer id;
    public Float weight;
   // public int dest;
   public String street;

    public Node(int id, float weight, String street){
        this.id = id;
        this.weight = weight;
        //this.dest = dest;
        this.street = street;
    }
    public Node(int id, float weight){
        this.id = id;
        this.weight = weight;
        //this.dest = dest;
    }

    // @Override
    // public int compareTo(Node node){
    //     return this.street.compareTo(node.street);
    // }

    public int GetID(){
        return this.id;
    }

    public float GetWeight(){
        return this.weight;
    }

    // public int GetDestination(){
    //     return this.dest;
    // }

    public String GetStreet(){
        return this.street;
    }
}
