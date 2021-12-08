package Node;

public class QueueNode implements Comparable<QueueNode>{
    public Integer id;
    public float weight;

    public QueueNode(int id, float weight){
        this.id = id;
        this.weight = weight;
    }

    @Override
    public int compareTo(QueueNode node){
        return this.id.compareTo(node.id);
    }

    public int GetID(){
        return this.id;
    }
    public float GetWeight(){
        return this.weight;
    }

}
