import java.util.ArrayList;

public class FillData {

    private ArrayList<Vertex> buildings = new ArrayList<Vertex>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    public FillData()
    {
        fill();
    }

    public void fill()
    {
        //Create all buildings
        Vertex b1 = new Vertex(1, "College Square");
        Vertex b2 = new Vertex(2, "Speech Language Hearing");
        Vertex b3 = new Vertex(3, "Lewis Science Center");
        Vertex b4 = new Vertex(4, "Math-Comp Science");
        Vertex b5 = new Vertex(5, "Burdick");
        Vertex b6 = new Vertex(6, "Prince Center");
        Vertex b7 = new Vertex(7, "Torreyson Library");
        Vertex b8 = new Vertex(8, "Maintenance College");
        Vertex b9 = new Vertex(9, "Old Main");
        Vertex b10 = new Vertex(10, "Police Dept.");
        Vertex b11 = new Vertex(11, "Fine Art");
        Vertex b12 = new Vertex(12, "McALister Hall");
        Vertex b13 = new Vertex(13, "Student Center");
        Vertex b14 = new Vertex(14, "Wingo");
        Vertex b15 = new Vertex(15, "Student Health Center");
        Vertex b16 = new Vertex(16, "New Business Building");
        Vertex b17 = new Vertex(17, "Oak Tree Apt.");
        Vertex b18 = new Vertex(18, "Brewer-Hegeman");
        Vertex b19 = new Vertex(19, "Bear Village Apt");

        //Now add all buildings to the arraylist
        buildings.add(0, b1);
        buildings.add(1, b2);
        buildings.add(2, b3);
        buildings.add(3, b4);
        buildings.add(4, b5);
        buildings.add(5, b6);
        buildings.add(6, b7);
        buildings.add(7, b8);
        buildings.add(8, b9);
        buildings.add(9, b10);
        buildings.add(10, b11);
        buildings.add(11, b12);
        buildings.add(12, b13);
        buildings.add(13, b14);
        buildings.add(14, b15);
        buildings.add(15, b16);
        buildings.add(16, b17);
        buildings.add(17, b18);
        buildings.add(18, b19);

        //Now create all 37 edges provided in the project description
        addConnection(1, 1, 6, 300);
        addConnection(2, 1, 3, 200);
        addConnection(3, 3, 2, 250);
        addConnection(4, 2, 8, 120);
        addConnection(5, 2, 5, 100);
        addConnection(6, 5, 8, 300);
        addConnection(7, 4, 5, 30);
        addConnection(8, 3, 4, 150);
        addConnection(9, 4, 6, 80);
        addConnection(10, 4, 7, 40);
        addConnection(11, 5, 7, 80);
        addConnection(12, 5, 12, 200);
        addConnection(13, 8, 12, 150);
        addConnection(14, 6, 7, 30);
        addConnection(15, 7, 9, 30);
        addConnection(16, 6, 10, 100);
        addConnection(17, 9, 10, 200);
        addConnection(18, 9, 11, 90);
        addConnection(19, 9, 12, 100);
        addConnection(20, 8, 14, 100);
        addConnection(21, 8, 16, 150);
        addConnection(22, 8, 17, 160);
        addConnection(23, 11, 12, 180);
        addConnection(24, 10, 15, 100);
        addConnection(25, 11, 13, 80);
        addConnection(26, 10, 11, 50);
        addConnection(27, 12, 13, 100);
        addConnection(28, 12, 14, 50);
        addConnection(29, 13, 15, 50);
        addConnection(30, 15, 18, 200);
        addConnection(31, 13, 14, 100);
        addConnection(32, 14, 16, 50);
        addConnection(33, 13, 16, 110);
        addConnection(34, 16, 17, 30);
        addConnection(35, 16, 18, 20);
        addConnection(36, 17, 18, 40);
        addConnection(37, 18, 19, 350);
    }

    private void addConnection(int id, int end1ID, int end2ID, int distance)
    {
        Edge edge = new Edge(id, buildings.get(end1ID - 1), buildings.get(end2ID - 1), distance);
        edges.add(edge);
    }

    public ArrayList<Vertex> getBuildings() {
        return buildings;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
