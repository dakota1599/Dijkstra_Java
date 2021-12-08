import java.io.*;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.DocFlavor.READER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.PriorityQueue;

import Node.*;
public class Dijkstra {
    

    public static Float [] DijShortest(int source, int size, ArrayList<ArrayList<Node>> graph){

        Float[] result = new Float[size];

        for(int i = 0; i < size; i++){
            result[i] = Float.MAX_VALUE;
        }
        result[source] = 0.0f;

        PriorityQueue<QueueNode> queue = new PriorityQueue<QueueNode>(size);
        queue.offer(new QueueNode(source, 0.0f));

        while(queue.size() > 0){
            QueueNode current = queue.poll();

            for(Node node : graph.get(current.GetID())){
                System.out.println(node.GetID());
                if(result[current.GetID()] + node.GetWeight() < result[node.GetID()]){
                    result[node.GetID()] = node.GetWeight() + result[current.GetID()];
                    queue.offer(new QueueNode(node.GetID(), result[node.GetID()]));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException{
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

            List<Float> lresult = Arrays.asList(result);


            System.out.println(lresult.size());

            // while(true){
            //     float dist = Collections.min(lresult);
            //     int ind = lresult.indexOf(dist);
            //     System.out.println(String.format("%d - %f", ind, dist));
            //     lresult.remove(ind);
            //     if(ind == dFull){
            //         break;
            //     }
                
            // }

            FileWriter writer = new FileWriter("dump.txt");
            for(int i = 0; i < lresult.size(); i++){
                if(lresult.get(i) < Float.MAX_VALUE){
                    float dist = lresult.get(i);
                    int ind = lresult.indexOf(lresult.get(i));
                    writer.write(String.format("%d - %f\n", ind, dist));
                }
            }

            // FileWriter writer = new FileWriter("dump.txt");
            // for(int i = 0; i < graph.size(); i++){
            //     for(Node node : graph.get(i)){
            //         writer.write(String.format("%d - %d : %f\n", node.id, node.dest, node.weight));
            //     }
            // }
            writer.close();

         }
    }
}
