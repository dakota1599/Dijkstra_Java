import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.PriorityQueue;

import Node.*;
public class Dijkstra {
    

    public static Float [] DijShortest(int source, int size, ArrayList<ArrayList<Node>> graph){
        Float[] result = new Float[size];

        for(int i = 0; i < size; i++){
            result[i] = Float.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(new Node(source, 0, 0, ""));

        while(queue.size() > 0){
            Node current = queue.poll();

            for(Node node : graph.get(current.id)){
                if(result[current.id] + node.weight < result[node.id]){
                    result[node.id] = node.weight + result[current.id];
                    queue.add(new Node(node.id, result[node.id], 0 ,""));
                }
            }
        }

        return result;
    }

    public static void main(String[] args){
        File roads = new File("USRoads/Road.txt");
        File places = new File("USRoads/Place.txt");
        Scanner user_input = new Scanner(System.in);

        int roads_count = FileOps.GetLineCount(roads);roads_count++;
        ArrayList<ArrayList<Node>> graph = FileOps.ParseRoads(roads, roads_count);

        Dictionary<String, Integer> locations = FileOps.ParseLocations(places);

        boolean escape = false;

        while(!escape){
            System.out.println("Enter \"q\" to exit the program.");
            System.out.print("Enter the Source Name: ");
            String source = user_input.nextLine();
            if(source.equals("q")) break;
            System.out.print("\nEnter the Destination Name: ");
            String destination = user_input.nextLine();

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

            Float[] result = DijShortest(sFull, roads_count, graph);

            for(int i = 0; i < roads_count; i++){
                System.out.println(i + "             "
                               + result[i]);
            }

         }
    }
}
