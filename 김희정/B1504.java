import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// [BOJ] 1504 특정한 최단 경로
// 	68428KB |	652ms
public class B1504 {
    static class Node implements Comparable<Node>{
        int index;
        int cost;

        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N;
    static ArrayList<Node>[] graph;
    static int v1;
    static int v2;

    static int MAX = 200000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());


        // 1 -> v1 -> v2 -> end
        int sum1 = 0;
        sum1 += dijkstra(1,v1);
        sum1 += dijkstra(v1, v2);
        sum1 += dijkstra(v2, N);
        // 1 -> v2 -> v1 -> end
        int sum2 = 0;
        sum2 += dijkstra(1, v2);
        sum2 += dijkstra(v2, v1);
        sum2 += dijkstra(v1, N);

        if(sum1 >= MAX && sum2 >= MAX){
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(sum1, sum2));
    }

    static int dijkstra(int start, int end){
        //다익스트라
        boolean[] check = new boolean[N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist, MAX);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){
            int currIdx = pq.poll().index;

            if(check[currIdx]) continue;
            check[currIdx] = true;

            for(Node next : graph[currIdx]){
                if(dist[next.index] > dist[currIdx] + next.cost){
                    dist[next.index] = dist[currIdx] + next.cost;

                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        return dist[end];
    }
}
