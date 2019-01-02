import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Variable declaration/initialization
        int start, end;
        String s, e, c;
        String answer = "N";
        int choice = 0;

        //Get buildings and edges from FillData class and use them to create a graph
        FillData data = new FillData();
        ArrayList<Vertex> buildings = data.getBuildings();
        ArrayList<Edge> edges = data.getEdges();
        Graph graph = new Graph(buildings, edges);

        //Create a scanner object to get input from the user
        Scanner scanner = new Scanner(System.in);

        do {
            do {
                System.out.println("Please enter a number cooresponding to the algorithm that you would like to run");
                System.out.println("1.  Dijkstra's Algorithm");
                System.out.println("2.  Bellman-Ford Algorithm");
                c = scanner.nextLine();
                choice = Integer.parseInt(c);
            } while(choice < 1 || choice > 2);

            if (choice == 1) {
                System.out.println("Now running Dijkstra's Algorithm");
                //Get a start building for dijkstra's algorithm
                System.out.println("Please enter a start building (labeled 0-18)");
                s = scanner.nextLine();
                start = Integer.parseInt(s);

                //Now, get a destination for dijkstra's algorithm
                System.out.println("Please enter a destination (labeled 0-18)");
                e = scanner.nextLine();
                end = Integer.parseInt(e);

                //Test finding a route between the buildings the user entered
                Dijkstra dijkstra = new Dijkstra(graph);
                dijkstra.RunAlgorithm(buildings.get(start));
                LinkedList<Vertex> route = dijkstra.getRoute(buildings.get(end));

                int pathKey = 1;
                System.out.println("Now printing path:  ");
                
                for (int i = 0; i < route.size(); i++) {
                    String name = route.get(i).getName();
                    int id = route.get(i).getId() - 1;

                    System.out.println(pathKey + ":  " + name + " - ID: " +  id);
                    pathKey++;
                }
            }
            else {
                //Get a start building for bellman algorithm
                System.out.println("Now running Bellman-Ford Algorithm");
                System.out.println("Please enter a start building (labeled 0-18)");
                s = scanner.nextLine();
                start = Integer.parseInt(s);

                //Now, get a destination for bellman algorithm
                System.out.println("Please enter a destination (labeled 0-18)");
                e = scanner.nextLine();
                end = Integer.parseInt(e);

                //Test all methods for BellmanFord algorithm
                Bellman bm = new Bellman(graph, buildings.get(start), buildings.get(end));

                bm.RunAlgorithm();
                LinkedList<Vertex> route = bm.getRoute(buildings.get(end));

                int pathKey = 1;
                System.out.println("Now printing path:  ");
                for (Vertex v : route) {
                    String name = v.getName();
                    int id = v.getId() - 1;

                    System.out.println(pathKey + ":  " + name + " - ID: " +  id);
                    pathKey++;
                }
            }

            //Prompt user to repeat
            System.out.println("Do you want to rerun the program?  (Enter y for yes and anything else for no)");
            answer = scanner.nextLine();

            //If the user wants to rerun the program, reset the variables
            if(answer.equals("y"))
            {
                choice = 0;
            }

        } while (answer.equals("y"));
    }
}
