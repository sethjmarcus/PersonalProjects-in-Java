package graph;

import java.util.HashMap;

public class Vertex<T>{
   String name;
   T data;
   HashMap<Vertex<T>, Edge<T>> adjacent;
   
   public Vertex(String n) {
      name = n;
      adjacent = new HashMap<>();
   }
   
   public Vertex(String n, T d) {
      this(n);
      data = d;
   }
   
   public int hashCode() {
      return name.hashCode();
   }
   
   public String toString() {
      return name;
   }
   
}