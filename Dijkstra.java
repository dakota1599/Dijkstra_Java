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
    

    public static Bundle DijShortest(int src, int size, Graph graph){

        //Initializing a priority queue that uses a double comparator to compare the weight of the nodes inserted.
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble(cNode -> cNode.weight));
        Bundle bundle = new Bundle(size);
        queue.offer(new Node(src, 0));
        //Distances for each node to source
        bundle.distances[src] = 0.0f;
        //The previous nodes to help build the paths
        bundle.previousNodes[src] = -1;
        //Checks for when minimum distance is found.
        bundle.completed[src] = true;
        

        while(!queue.isEmpty()){
            Node cNode = queue.poll();

            int id = cNode.id;
            System.out.println(String.format("%d\n-----",id));
            for(Edge edge: graph.edges.get(id)){
                int edgeDest = edge.dest;
                float edgeWeight = edge.weight;

                if(!bundle.completed[edgeDest] && (bundle.distances[id]+edgeWeight) < bundle.distances[edgeDest]){
                    System.out.println(edgeDest);
                    bundle.distances[edgeDest] = bundle.distances[id] + edgeWeight;
                    bundle.previousNodes[edgeDest] = id;
                    queue.offer(new Node(edgeDest, bundle.distances[edgeDest]));
                }
            }

            bundle.completed[id] = true;
        }

        return bundle;


    }

    public static void main(String[] args) throws IOException{
        File roads = new File("USRoads/Road.txt");
        File places = new File("USRoads/Place.txt");
        Scanner userInput = new Scanner(System.in);

        int roads_count = FileOps.GetLineCount(roads);roads_count++;
        ArrayList<Edge> edges = FileOps.ParseRoads(roads, roads_count);
        Graph graph = new Graph(edges, edges.size());

        Dictionary<String, Integer> locations = FileOps.ParseLocations(places);
        Dictionary<Integer, String> rev_locations = FileOps.ParseLocationsReverse(places);

        boolean escape = false;

        while(!escape){
            System.out.println("Enter \"q\" to exit the program.");
            System.out.print("Enter the Source Name: ");
            String source = userInput.nextLine();
            if(source.equals("q")) break;
            System.out.print("\nEnter the Destination Name: ");
            String destination = userInput.nextLine();

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

            Bundle bundle = DijShortest(sFull, graph.size, graph);

            ArrayList<Integer> routes = new ArrayList<>();

            FileWriter writer = new FileWriter("dump.txt");
            for(int i = 0; i < roads_count; i++){
                if(i != sFull && bundle.distances[i] != Float.MAX_VALUE){
                    bundle.getRoute(i, routes);
                    writer.write(String.format("Path(%d -> %d): Minimum cost = %.2f, Route= %s\n",sFull, i, bundle.distances[i], routes));
                    routes.clear();
                }
            }
            writer.close();

            // int node = dFull;
            // FileWriter writer = new FileWriter("dump.txt");
            // for(int val = 0; val < bundle.distances.length; val++){
            //     writer.write(String.format("%d - %f\n",val, bundle.distances[val]));
            // }

            // for(int route : routes){
            //     System.out.println(route);
            // }

            //getRoute(dFull, bundle.previousNodes, routes);
            // FileWriter writer = new FileWriter("dump.txt");
            // for(int i = 0; i < bundle.previousNodes.length; i++){
            //     writer.write(String.format("%d\n", bundle.previousNodes[i]));
            // }
            // writer.close();

            
            
            

            
                
            

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
