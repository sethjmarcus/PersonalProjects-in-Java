package graph;

public class Edge<T> {
   Vertex<T> origin;
   Vertex<T> destination;
   double weight;
   
   public Edge(Vertex<T> a, Vertex<T> b) {
      this(a, b, 1.0);
   }
   
   public Edge(Vertex<T> a, Vertex<T> b, double w) {
      origin = a;
      destination = b;
      weight = w;
   }
   
   
   public String toString() {
      return "" + origin.toString() + " -- " + destination.toString();
   }
}