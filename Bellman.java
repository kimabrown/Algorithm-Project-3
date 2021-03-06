import java.util.*;

public class Bellman {
    //Private data members
    private ArrayList<Vertex> vertex;
    private ArrayList<Edge> edges;
    private Vertex start;
    private Vertex end;
    private Set<Vertex> visitedNodes;
    private Set<Vertex> unVisitedNodes;
    private Map<Vertex, Vertex> previousNodes;
    private Map<Vertex, Integer> distance;

    //Constructor
    public Bellman(Graph g, Vertex start, Vertex end)
    {
        this.vertex = new ArrayList<Vertex>(g.getVertices());
        this.edges = new ArrayList<Edge>(g.getEdges());
        this.start = start;
        this.end = end;

        visitedNodes = new HashSet<Vertex>();
        unVisitedNodes = new HashSet<Vertex>();
        previousNodes = new HashMap<Vertex, Vertex>();
        distance = new HashMap<Vertex, Integer>();
    }

    public void RunAlgorithm()
    {
        //Private Data Members
        ArrayList<Integer> dist = new ArrayList<Integer>();

        //Initialize graph.  Source starts at 0; everything else at infinity
        for (Vertex v : vertex)
        {
            if (v.equals(start))
            {
                dist.add(v.getId() - 1, 0);
            }
            else
            {
                dist.add(v.getId() - 1, Integer.MAX_VALUE);
            }
        }
        distance.put(start, 0);

        //Relax edges repeatedly
        for (int i = 0; i < vertex.size() - 1; i++)
        {
            for (Edge e : edges)
            {
                if (dist.get(e.getEnd1().getId() - 1) + e.getDistance() < dist.get(e.getEnd2().getId() - 1))
                {
                    dist.set(e.getEnd2().getId() - 1, e.getDistance() + dist.get(e.getEnd1().getId() - 1));
                }
            }
        }
        unVisitedNodes.add(start);

        //Check for negative weight cycles
        for (Edge e : edges)
        {
            if (dist.get(e.getEnd1().getId() - 1) + e.getDistance() < dist.get(e.getEnd2().getId() - 1))
            {
                System.out.println("Graph contains negative-weight cycle");
            }
        }

        //While loop to iterate over unvisited nodes
        while (unVisitedNodes.size() > 0)
        {
            Vertex v = getMinVertices(unVisitedNodes);
            visitedNodes.add(v);
            unVisitedNodes.remove(v);
            findMinDist(v);
        }
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
        if (previousNodes.get(v) == null)
        {
            return null;
        }
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
}
