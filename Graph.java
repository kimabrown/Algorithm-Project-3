import java.util.ArrayList;

public class Graph {
    //Private data members
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;

    //Default Contructor
    public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges)
    {
        this.vertices = vertices;
        this.edges = edges;
    }

    //Getters
    public ArrayList<Vertex> getVertices()
    {
        return vertices;
    }

    public ArrayList<Edge> getEdges()
    {
        return edges;
    }

    //Methods for adding edges and vertices to the graph
    public void addEdge(Edge e)
    {
        edges.add(e);
    }

    public void addVertex(Vertex v)
    {
        vertices.add(v);
    }
}
