package Node;

//Heavily inspired from the graph used here: https://www.geeksforgeeks.org/graph-and-its-representations/
public class Node{
    public Integer id;
    public Float weight;
   // public int dest;
   // public String street;

    public Node(int id, float weight){
        this.id = id;
        this.weight = weight;
        //this.dest = dest;
        //this.street = street;
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

    // public String GetStreet(){
    //     return this.street;
    // }
}
