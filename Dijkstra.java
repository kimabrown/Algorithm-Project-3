import java.util.*;

public class Dijkstra {
    //Private data members
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private Set<Vertex> visitedNodes;
    private Set<Vertex> unVisitedNodes;
    private Map<Vertex, Vertex> previousNodes;
    private Map<Vertex, Integer> distance;

    //Default Constructor created using graph
    public Dijkstra(Graph g)
    {
        //Create a deep copy of the graph's properties so as not to manipulate the original
        this.vertices = new ArrayList<Vertex>(g.getVertices());
        this.edges = new ArrayList<Edge>(g.getEdges());

    }

    //Get distance between two nodes
    public int getDistance(Vertex start, Vertex end)
    {
        //For each loop to iterate over all edges in the graph
        for (Edge edge : edges)
        {
            //Test to be sure you have the correct start and end nodes, but they can be either because the graph is undirected
            if ((edge.getEnd1().equals(start) && edge.getEnd2().equals(end)) || (edge.getEnd1().equals(end) && edge.getEnd2().equals(start)))
            {
                return edge.getDistance();
            }
        }
        return 0; //If this happens, something went very very wrong
    }

    //Get the minimum vertex
    public Vertex getMinVertices(Set<Vertex> vertices)
    {
        //Create a minimum vertex and initialize it to null
        Vertex min = null;

        //For each loop to iterate over all vertices and test to find minimum
        for (Vertex v : vertices)
        {
            if (min == null)
            {
                min = v;
            }
            else
            {
                if (getShortestDistance(v) < getShortestDistance(min))
                {
                    min = v;
                }
            }
        }
        return min;
    }


    //Get all of the nodes that the current node can link to
    public ArrayList<Vertex> getConnections(Vertex v)
    {
        ArrayList<Vertex> connections = new ArrayList<Vertex>();
        for (int i = 0; i < edges.size(); i++)
        {
        	Edge edge = edges.get(i);
        	
            //test to be sure that the right edges are selected
            if (edge.getEnd1().equals(v) && (!isVisited(edge.getEnd2())))
            {
                connections.add(edge.getEnd2());
            }
            
            if (edge.getEnd2().equals(v) && (!isVisited(edge.getEnd1())))
            {
            	connections.add(edge.getEnd1());
            }
        }
        return connections;
    }

    //Method that caluclates the shortest distance
    public  int getShortestDistance(Vertex v)
    {
        //Gets the distance of a vertex
        Integer dist = distance.get(v);

        //If there is no distance, return infinity, otherwise return the distance
        if (dist == null)
        {
            return Integer.MAX_VALUE;
        }
        else
        {
            return dist;
        }
    }

    //Boolean function that returns true if the vertex has been visited
    public boolean isVisited(Vertex v)
    {
        return visitedNodes.contains(v);
    }

    //Returns the path from the source to the destination, unless one doesn't exist then it returns null
    public LinkedList<Vertex> getRoute(Vertex destination)
    {
        LinkedList<Vertex> route = new LinkedList<Vertex>();
        Vertex v = destination;

        //Check to see if a route exists
        
/*        if (previousNodes.get(v) == null)
        {
            return null;
        }*/
        
        //If it does, add the building to the route
        route.add(v);
        //While there is still a path to go, add buildings to the route
        while (previousNodes.get(v) != null)
        {
            v = previousNodes.get(v);
            route.add(v);
        }

        //Now correct the order of the route
        Collections.reverse(route);
        return route;
    }

    //Find the minimum distance between two vertices by finding all connections to a vertex and exploring all routes
    public void findMinDist(Vertex v)
    {
        ArrayList<Vertex> nextTo = getConnections(v);
        for (Vertex target : nextTo)
        {
            if (getShortestDistance(target) > getShortestDistance(v) + getDistance(v, target))
            {
                distance.put(target, getShortestDistance(v) + getDistance(v, target));
                previousNodes.put(target, v);
                unVisitedNodes.add(target);
            }
        }
    }

    //Most important function that begins at a vertex and explores graph
    public void RunAlgorithm(Vertex begin)
    {
        //Initializing all variables to their respective data structures
        distance = new HashMap<Vertex, Integer>();
        previousNodes = new HashMap<Vertex, Vertex>();
        visitedNodes = new HashSet<Vertex>();
        unVisitedNodes = new HashSet<Vertex>();

        //Place the first node
        distance.put(begin, 0);
        //Set the first node to being unvisited
        unVisitedNodes.add(begin);

        //While loop to iterate over all unvisited nodes
        while (unVisitedNodes.size() > 0)
        {
            Vertex v = getMinVertices(unVisitedNodes);
            visitedNodes.add(v);
            unVisitedNodes.remove(v);
            findMinDist(v);
        }
    }

}
