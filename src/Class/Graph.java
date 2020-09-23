package Class;

import java.util.*;

    public class Graph {
        public final Map<String, Vertex> graph;
        public static String string1;
          public static String string2;
        
        public static class Edge{
            public final String vertex1, vertex2;
            public final int dist;

        public String getV1() {
            return vertex1;
        }

        public String getV2() {
            return vertex2;
        }

        public int getDist() {
            return dist;
        }
            
            
            public Edge(String v1, String v2, int dist){
                this.vertex1 = v1;
                this.vertex2 = v2;
                this.dist = dist;
                string2 += this.vertex1 + " ------ >" + this.vertex2 + " = " + this.dist+"\n\n";
                
            }
        }
        /*
        *Inner Class Vertex
        */
        public static class Vertex implements Comparable<Vertex>{
            public final String name;
            public int dist = Integer.MAX_VALUE;
            public Vertex previous = null;
            public final Map<Vertex, Integer> neighbours = new HashMap<>();

            public Vertex(String name){
                this.name = name;
            }
            public void printPath(){
                if(this == this.previous){
                    System.out.printf("%s", this.name);
                    string1+="\n" +this.name;
                }
                else if(this.previous == null){
                    System.out.printf("%s(unreached", this.name);
                    string1+="" +this.name;
                }
                else{
                    this.previous.printPath();
                    System.out.printf(" ---- >%s(%d)", this.name, this.dist);
                    string1 +=" ------ >  " +this.name + "( " + this.dist +")";
                }
            }
            @Override
             public int compareTo(Vertex other){
                return Integer.compare(dist,other.dist);
            }
        }
           public Graph (Edge[] edges){
            graph= new HashMap<> (edges.length);

            for(Edge e: edges){
                if(!graph.containsKey(e.vertex1)) graph.put(e.vertex1, new Vertex(e.vertex1));
                if(!graph.containsKey(e.vertex2)) graph.put(e.vertex2, new Vertex(e.vertex2));
            }
            for(Edge e: edges){
             graph.get(e.vertex1).neighbours.put(graph.get(e.vertex2), e.dist);
            }
       }
           public void dijkstra(String startName){
            if(!graph.containsKey(startName)){
            System.err.printf("Graph doesn't contain start vertex\"%s\"\n", startName);
            return;
        }
           final Vertex source = graph.get(startName);
            NavigableSet<Vertex> q = new TreeSet<>();
            for(Vertex v : graph.values()){
             v.previous = v == source? source:null;
             v.dist =v == source ? 0: Integer.MAX_VALUE;
              q.add(v);
            }
            dijkstra(q);
        }
          public void dijkstra(final NavigableSet<Vertex> q){
               Vertex u, v;
              while(!q.isEmpty()){
                    u = q.pollFirst();
                    if(u.dist == Integer.MIN_VALUE) {
                        break;
                    }

                 for(Map.Entry<Vertex, Integer> a: u.neighbours.entrySet()){
                     v = a.getKey();

                    final int alternateDist = u.dist + a.getValue();
                 if(alternateDist <v.dist){
                 q.remove(v);
                 v.dist = alternateDist;
                 v.previous = u;
                    q.add(v);
                 }
            }
           }
        }
         public void printPath(String endName){
        if(!graph.containsKey(endName)){
        System.err.printf("Graph doesn't contain end vertex\"%s\"n", endName);
        return;
        }
        graph.get(endName).printPath();
         System.out.println();
    }
        public void printAllPaths(){
        for (Vertex v : graph.values()){
        v.printPath();
        System.out.println();
        string1+="\n";
    }
        System.out.println("The value of str " + string1);

    }

      }
