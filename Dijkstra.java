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

        //This creates an array of QueueNodes that are then put into the PriorityQueue.
        //I did this because I needed a way to reference the queues within the PriorityQueue to alter their priority
        //when their distances are updated.
        for(int i = 0; i < size; i++){
            if( i != src){
                nodes[i] = new QueueNode(i, Float.MAX_VALUE);
                queue.add(nodes[i]);
            }else{
                nodes[i] = new QueueNode(i, 0.0f);
                queue.add(nodes[i]);
            }
        } 
        //Setting source information.  Zero for distance and -1 for previous node.
        bundle.distances[src] = 0.0f;
        bundle.previousNodes[src] = -1;
        try{
        
        while(!queue.isEmpty()){
            //Pulls from the top node.
            QueueNode cNode = queue.poll();
            int id = cNode.id;
            Edge current = graph.get(id).neighbors;
            while(current != null){
                float total = bundle.distances[id] + current.weight;
                if(total < bundle.distances[current.id]){
                    bundle.distances[current.id] = total;
                    bundle.previousNodes[current.id] = id;
                    //These actions are for editing the priority of the QueueNodes
                    queue.remove(nodes[current.id]);
                    nodes[current.id] = new QueueNode(current.id, total);
                    queue.add(nodes[current.id]);
                }
                current = current.next;
                
                
            }
        }
    }catch(Exception ex){
        //Prints to the error stream.
        System.err.println(ex);
    }
        return bundle;


    }

    public static void main(String[] args) throws Exception{
        //Opening up the input files.  DO NOT CHANGE THE INTERNAL DIRECTORY WHERE THESE FILES ARE STORED.
        File roads = new File("USRoads/Road.txt");
        File places = new File("USRoads/Place.txt");
        Scanner userInput = new Scanner(System.in);
        
        //I get the line count ahead of time to be used when parsing the file.
        int roads_count = FileOps.GetLineCount(roads);roads_count++;
        ArrayList<Node> graph = FileOps.ParseRoads(roads, roads_count);

        Dictionary<String, Integer> locations = FileOps.ParseLocations(places);
        Dictionary<Integer, String> rev_locations = FileOps.ParseLocationsReverse(places);

        boolean escape = false;

        while(!escape){
            //Input information
            //***************************************************************
            System.out.println("Enter \"q\" to exit the program.");
            System.out.print("Enter the Source Name: ");
            String source = userInput.nextLine();
            if(source.toLowerCase().equals("q")) break;
            System.out.print("\nEnter the Destination Name: ");
            String destination = userInput.nextLine();
            if(destination.toLowerCase().equals("q")) break;
            //***************************************************************

            int sFull = 0;
            int dFull = 0;
            //Attempts to get the IDs for the inputted names.
            try{
                sFull = locations.get(source);
                dFull = locations.get(destination);
            }catch(Exception ex){
                //Failure to do this results in an error.
                System.err.println(String.format("Your source [%s] or destination [%s] could not be found.", source, destination));
                continue;
            }

            //Calling Dijkstra's Algorithm and getting the bundle.
            Bundle bundle = DijShortest(sFull, graph.size(), graph);
            
            
            int node = dFull;
            ArrayList<Integer> path = new ArrayList<>();
            int count = 1;
            boolean esc = false;
            //This traverses the previous nodes lists and creates a new ArrayList of their IDs.
            while(node >= 0){
                path.add(node);
                node = bundle.previousNodes[node];
                //If two locations are not connected in the graph, then this loop goes on forever.
                //This check is here to prevent that from happening.  If the count exceeds the total length of the
                //distance records, then the program resets.
                if(count >= bundle.distances.length){
                    System.out.println("Unfortunately, your destination cannot be reached by your starting point.");
                    esc = true;
                    break;
                }
                count++;
            }
            if(esc){
                continue;
            }

            //This is the display section.  It displays the results at the end of the search.
            //***************************************************************************************************************************************
            System.out.println(String.format("Searching from %d (%s) to %d (%s)",sFull, source, dFull, destination));

            count = 1;
            for(int i = path.size()-1; i > 0; i--){
                Node cNode = graph.get(path.get(i));
                Edge current = cNode.neighbors;
                while(current != null){
                    if(current.id == path.get(i-1)){
                        break;
                    }
                    current = current.next;
                }

                System.out.println(String.format("%d: %d(%s) -> %d(%s), %s, %.2f mi.", count, cNode.id, rev_locations.get(cNode.id), current.id,rev_locations.get(current.id), current.street, current.weight));
                count++;
            }

            System.out.println(String.format("It takes %.2f miles from %d (%s) to %d (%s).", bundle.distances[dFull], sFull, source, dFull, destination));
            //***************************************************************************************************************************************

         }
         userInput.close();
    }
}
