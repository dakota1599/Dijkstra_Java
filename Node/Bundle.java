package Node;

import java.util.*;

//A class for holding all path information.
public class Bundle {
    public Float[] distances;
    public int[] previousNodes;
    public boolean[] completed;

    public Bundle(int size){
        this.distances = new Float[size];
        for(int i = 0; i < size; i++){
            distances[i] = Float.MAX_VALUE;
        }

        previousNodes = new int[size];
        completed = new boolean[size];
    }

    public void getRoute(int node, ArrayList<Integer> route){
        if(node >= 0){
            this.getRoute(previousNodes[node], route);
            route.add(node);
        }
    }
}
