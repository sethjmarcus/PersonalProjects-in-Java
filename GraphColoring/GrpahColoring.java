package graph;

import java.util.HashMap;

public class GrpahColoring {

   public static void main(String[] args) throws Exception{
      final int numberOfVertecies = 10;
      Graph<Integer> g = new Graph<Integer>(false);
      for (int i = 0; i < numberOfVertecies; i++) {
         Vertex<Integer> v = new Vertex<Integer>("" + i, i);
         g.insertVertex(v);
      }
      for (int i = 0; i < numberOfVertecies; i++) {
         int x = (int) (Math.random() * numberOfVertecies);
         int y = (int) (Math.random() * numberOfVertecies);
         if (x == y) i--;
         else {
            try {
               g.insertEdge(g.findVertex("" + x), g.findVertex("" + y));
            } catch (Exception e) {}
         }
      }
      System.out.println("Graph with "+numberOfVertecies+" vertices and " + g.numEdges() + " edges.\n");
      
      //printGraph(g);
      
      System.out.println("A coloring of the Graph is:");
      HashMap<Vertex<Integer>, Integer> graphColoring = g.coloring();
      System.out.println(graphColoring);

   }
   
   public static void printGraph(Graph<Integer> g) {
      for(Vertex<Integer> node : g.vertices) {
         System.out.print(node.data + ":\t");
         for(Vertex<Integer> j : node.adjacent.keySet()) {
            System.out.print(j.data + " ");
         }
         System.out.println();
      }
   }
   
}
