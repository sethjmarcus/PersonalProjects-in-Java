package graph;

import java.util.Comparator;

public class VertexComparator<T> implements Comparator<Vertex<T>>{
   @Override
   public int compare(Vertex<T> o1, Vertex<T> o2) {
      return o2.adjacent.size() - o1.adjacent.size();
   }
   
}
