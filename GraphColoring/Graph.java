package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Graph<T> {
   HashSet<Vertex<T>> vertices;
   HashSet<Edge<T>> edges;
   boolean isDirected;
   
   public Graph(boolean b) {
      vertices = new HashSet<Vertex<T>>();
      edges = new HashSet<Edge<T>>();
      isDirected = b;
   }
   
   int numVertices() {
      return vertices.size();
   }
   
   int numEdges() {
      return edges.size();
   }
   
   Iterator<Vertex<T>> vertices() {
      return vertices.iterator();
   }
   
   Iterator<Edge<T>> edges() {
      return edges.iterator();
   }
   
   Edge<T> getEdge(Vertex<T> u, Vertex<T> v) {
      return u.adjacent.get(v);
   }
   
   Vertex<T>[] endVertices(Edge<T> e) {
      Vertex<T>[] ans = (Vertex<T> []) new Vertex[2];
      ans[0] = e.origin;
      ans[1] = e.destination;
      return ans;
   }

   Vertex<T> opposite(Vertex<T> v, Edge<T> e) {
      Vertex<T>[] endVertices =  endVertices(e);
      if (v == endVertices[0]) return endVertices[1];
      if (v == endVertices[1]) return endVertices[0];
      return null;
   }
   
   int outDegree(Vertex<T> v) {
      return v.adjacent.size();
   }
   
   int inDegree(Vertex<T> v) {
      if (!isDirected) return v.adjacent.size();
      int ans = 0;
      for (Vertex<T> w:vertices)
         if (w.adjacent.containsKey(v)) ans++;
      return ans;
   }
   
   Iterator<Edge<T>> outgoingEdges(Vertex<T> v) {
      return v.adjacent.values().iterator();
   }
   
   Iterator<Edge<T>> incomingEdges(Vertex<T> v) {
      if (!isDirected) return outgoingEdges(v);
      ArrayList<Edge<T>> list = new ArrayList<>();
      for (Vertex<T> w:vertices) if (w.adjacent.containsKey(v)) list.add(w.adjacent.get(v));
      return list.iterator();
   }
   
   void insertVertex(Vertex<T> v) throws Exception {
      if (vertices.contains(v)) throw new Exception("Illegal duplicate vertex.");
      vertices.add(v);
   }
   
   void insertEdge(Vertex<T> v, Vertex<T> w) throws Exception {
      if (!vertices.contains(v)) vertices.add(v);
      if (!vertices.contains(w)) vertices.add(w);
      if (v.adjacent.containsKey(w)) throw new Exception("Illegal duplicate edge.");
      Edge<T> e = new Edge<T>(v, w); 
      v.adjacent.put(w,  e);
      if (!isDirected) w.adjacent.put(v, e);
      edges.add(e);
   }
   
   void removeEdge(Edge<T> e) throws Exception {
      Vertex<T> v = e.origin;
      Vertex<T> w = e.destination;
      if (!v.adjacent.containsKey(w)) throw new Exception("No edge to remove.");
      Edge<T> f = v.adjacent.get(w);
      edges.remove(f);
      v.adjacent.remove(w);
      if (!isDirected)  w.adjacent.remove(v);
   }
   
   void removeVertex(Vertex<T> v) throws Exception {
      if (!vertices.contains(v)) throw new Exception("No vertex to remove.");
      vertices.remove(v);
      for (Edge<T> e:v.adjacent.values()) {
         edges.remove(e);
      }
      for (Vertex<T> w:vertices) if (w.adjacent.containsKey(v)) {
         if (isDirected) edges.remove(w.adjacent.get(v));
         w.adjacent.remove(v);
      }
   }
   
   Vertex<T> findVertex(String name) {
      for (Vertex<T> v:vertices) if (v.name.equals(name)) return v;
      return null;
   }
   
   HashMap<Vertex<T>, Integer> coloring() {
      HashMap<Vertex<T>, Integer> mapping = new HashMap<>();
      /*
       * list is a list of the vertecies in descending order by the degree of each node
       */
      PriorityQueue<Vertex<T>> list = new PriorityQueue<Vertex<T>>(new VertexComparator<T>());
      boolean colorsUsed[] = new boolean[numVertices()];
      
      for(Vertex<T> node : vertices) {
         mapping.put(node, -1);
         list.add(node);
      }
      
      for(Vertex<T> node : list) {
         //mark colors that can't use
         for(Vertex<T> j : node.adjacent.keySet()) {
            if(mapping.get(j) >= 0) {
               colorsUsed[mapping.get(j)] = true;
            }
         }
         //find first free color
         for(int j= 0; j< numVertices(); j++) {
            int color = -1;
            while(colorsUsed[++color]);//Find the first available color
            mapping.replace(node, color);
         }
         Arrays.fill(colorsUsed, Boolean.FALSE);
      }
      return mapping;
   }
   
}