import java.util.*;

public class Graph{
    private int v;
    private LinkedList<Integer>[] adj;

    Graph(int v){
        this.v=v;
        adj=new LinkedList[v];
        for(int i=0;i<v;i++)
            adj[i]=new LinkedList<>();
    }

    void addEdge(int v,int w){
        adj[v].add(w);
    }

    List<Integer> BFS(int s,int dest){
       boolean[] visited=new boolean[v];
       int[] distance=new int[v];
       int[] parent=new int[v];
       int current;
       Queue<Integer> queue=new LinkedList<>();
       List<Integer> path=new ArrayList<>();

       visited[s]=true;
       distance[s]=0;
       queue.add(s);

       while(!queue.isEmpty()){
           current=queue.poll();
           if(current==dest){
               int node=dest;
               while(node!=s){
                   path.add(node);
                   node=parent[node];
               }
               path.add(s);
               Collections.reverse(path);
               System.out.println("Shorten distance is "+ distance[current]);
               return path;
           }

           Iterator<Integer> i=adj[current].listIterator();
           while(i.hasNext()){
               int n=i.next();
               if(!visited[n]){
                   visited[n]=true;
                   distance[n]=distance[current]+1;
                   parent[n]=current;
                   queue.add(n);
               }
           }
       }

       return null;
    }

    public static void main(String[] args) {
        int v=7;
        Graph g=new Graph(v);

        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,0);
        g.addEdge(1,4);
        g.addEdge(2,0);
        g.addEdge(2,3);
        g.addEdge(2,5);
        g.addEdge(3,4);
        g.addEdge(3,2);
        g.addEdge(4,3);
        g.addEdge(4,1);
        g.addEdge(4,5);
        g.addEdge(4,6);
        g.addEdge(5,4);
        g.addEdge(5,2);

        int source=5,dest=1;
        List<Integer> shortestPath=g.BFS(source,dest);
        for(int i : shortestPath){
            System.out.print(i+" ");
        }
    }

}