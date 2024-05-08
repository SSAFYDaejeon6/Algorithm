import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
[BOJ] 4386 별자리 만들기
12600KB |	96ms
내 풀이 : 크루스칼 알고리즘으로 MST
 */
public class B4386 {
    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }
    static int N;
    static List<Edge> edges;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        double[][] star = new double[N][2];
        parents = new int[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            star[i][0] = Double.parseDouble(st.nextToken());
            star[i][1] = Double.parseDouble(st.nextToken());
            parents[i] = i;
        }

        //간선 리스트 구하기
        edges = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = i+1; j < N; j++){
                double weight = Math.sqrt(Math.pow(star[i][0] - star[j][0],2) + Math.pow(star[i][1] - star[j][1], 2));
                edges.add(new Edge(i, j, weight));
            }
        }

        //전처리 => 간선리스트 가중치 중심으로 오름차순 정렬
        Collections.sort(edges);

        double weightSum = 0;
        int cnt = 0;
        //2. 정렬된 간선 하나씩 꺼내 신장 트리 생성
        for(Edge edge : edges){
            if(!union(edge.from, edge.to)) continue;

            weightSum += edge.weight;
            if(++cnt == N-1) break;
        }
        System.out.printf("%.2f", weightSum);
    }

    static boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);
        if(parentA == parentB) return false;
        parents[parentA] = parentB;
        return true;
    }

    static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }


}
