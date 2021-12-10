/*
Dakota Shapiro
Dijkstra's Shortest Path Algorithm
Assignment 5
Professor Li Yang - CS 4310
*/

import java.io.*;
import java.util.*;

import Node.*;
public class Dijkstra {
    

    public static Bundle DijShortest(int src, int size, ArrayList<Node> graph){

        //Initializing a priority queue that uses a double comparator to compare the weight of the nodes inserted.
        PriorityQueue<QueueNode> queue = new PriorityQueue<>(Comparator.comparingDouble(cNode -> (double)cNode.weight));
        Bundle bundle = new Bundle(size);
        QueueNode[] nodes = new QueueNode[size];

        for(int i = 0; i < size; i++){
            if( i != src){
                nodes[i] = new QueueNode(i, Float.MAX_VALUE);
                queue.add(nodes[i]);
            }else{
                nodes[i] = new QueueNode(i, 0.0f);
                queue.add(nodes[i]);
            }
        } 

        bundle.distances[src] = 0.0f;
        bundle.previousNodes[src] = -1;
        try{
        FileWriter writer = new FileWriter("dump.txt");
        
        while(!queue.isEmpty()){
            QueueNode cNode = queue.poll();
            int id = cNode.id;
            writer.write(String.format("%d\n-------\n",id));
            Edge current = graph.get(id).neighbors;
            while(current != null){
                float total = bundle.distances[id] + current.weight;
                if(total < bundle.distances[current.id]){
                    bundle.distances[current.id] = total;
                    bundle.previousNodes[current.id] = id;
                    queue.remove(nodes[current.id]);
                    nodes[current.id] = new QueueNode(current.id, total);
                    queue.add(nodes[current.id]);
                }
                writer.write(String.format("%.2f\n",current.weight));
                current = current.next;
                
                
            }
        }
        writer.close();
    }catch(Exception ex){
        System.out.println(ex);
    }
        return bundle;


    }

    public static void main(String[] args) throws IOException{
        File roads = new File("USRoads/Road.txt");
        File places = new File("USRoads/Place.txt");
        Scanner userInput = new Scanner(System.in);

        int roads_count = FileOps.GetLineCount(roads);roads_count++;
        ArrayList<Node> graph = FileOps.ParseRoads(roads, roads_count);

        Dictionary<String, Integer> locations = FileOps.ParseLocations(places);
        Dictionary<Integer, String> rev_locations = FileOps.ParseLocationsReverse(places);

        boolean escape = false;

        while(!escape){
            userInput.nextLine();
            // System.out.println("Enter \"q\" to exit the program.");
            // System.out.print("Enter the Source Name: ");
            String source = "MIKALAMAZOO N";//userInput.nextLine();
            // if(source.equals("q")) break;
            // System.out.print("\nEnter the Destination Name: ");
            String destination = "MIANN ARBOR C-N";//userInput.nextLine();

            int sFull = 0;
            int dFull = 0;
            try{
                sFull = locations.get(source);
                dFull = locations.get(destination);
            }catch(Exception ex){
                System.out.println(String.format("Your source [%s] or destination [%s] could not be found.", source, destination));
                continue;
            }
            System.out.println(String.format("%d - %d", sFull, dFull));

            Bundle bundle = DijShortest(sFull, graph.size(), graph);

            // FileWriter writer = new FileWriter("dump.txt");
            // for(int i = 0; i < graph.size(); i++){
            //     Node node = graph.get(i);

            //     writer.write(String.format("\n%d\n",node.id));

            //     Edge current = node.neighbors;
            //     writer.write("Neighbors: ");
            //     while(current != null){
            //         writer.write(String.format("%d ",current.id));
            //         current = current.next;
            //     }
            // }
            // writer.close();
            // ArrayList<Integer> routes = new ArrayList<>();

            
            int node = dFull;
            ArrayList<Integer> path = new ArrayList<>();
            while(node >= 0){
                System.out.println(String.format("%d - %.2f", node, bundle.distances[node]));
                path.add(node);
                node = bundle.previousNodes[node];
            }

            System.out.println(String.format("Searching from %d (%s) to %d (%s)",sFull, source, dFull, destination));

            int count = 1;
            for(int i = path.size()-1; i > 0; i--){
                Node cNode = graph.get(path.get(i));
                Edge current = cNode.neighbors;
                while(current != null){
                    if(current.id == path.get(i-1)){
                        break;
                    }
                    current = current.next;
                }

                System.out.println(String.format("%d: %d(%s) -> %d(%s), %s, %.2f", count, cNode.id, rev_locations.get(cNode.id), current.id,rev_locations.get(current.id), current.street, current.weight));
                count++;
            }


            node = dFull;
            FileWriter writer = new FileWriter("dump.txt");
            for(int val = 0; val < bundle.distances.length; val++){
                if(bundle.distances[val] != Float.MAX_VALUE){
                    writer.write(String.format("%d - %f Prev: %d\n",val, bundle.distances[val], bundle.previousNodes[val]));
                }
                
            }

            // for(int route : routes){
            //     System.out.println(route);
            // }

            //getRoute(dFull, bundle.previousNodes, routes);
            // FileWriter writer = new FileWriter("dump.txt");
            // for(int i = 0; i < bundle.previousNodes.length; i++){
            //     writer.write(String.format("%d\n", bundle.previousNodes[i]));
            // }
             writer.close();

            
            
            

            
                
            

            // FileWriter writer = new FileWriter("dump.txt");
            // for(int i = 0; i < lresult.size(); i++){
            //     if(lresult.get(i) < Float.MAX_VALUE){
            //         float dist = lresult.get(i);
            //         int ind = lresult.indexOf(lresult.get(i));
            //         writer.write(String.format("%d - %f\n", ind, dist));
            //     }
            // }

            // FileWriter writer = new FileWriter("dump.txt");
            // for(int i = 0; i < graph.size(); i++){
            //     for(Node node : graph.get(i)){
            //         writer.write(String.format("%d - %d : %f\n", node.id, node.dest, node.weight));
            //     }
            // }
            //writer.close();
            //userInput.close();
         }
    }
}
