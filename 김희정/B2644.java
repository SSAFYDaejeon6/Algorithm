import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
[BOJ] 2644 촌수계산
풀이 : 양방향 그래프로 표현 -> bfs로 start에서 end로 가는 가장 짧은 거리 구함
 */
public class B2644 {
    static int[] distance;
    static boolean[] visited;
    static List<List<Integer>> graph = new ArrayList<>();       //양방향그래프
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        distance = new int[n+1];
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){ //그래프 입력
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        System.out.println(findShortestDistance(x,y));

    }

    static int findShortestDistance(int x, int y){
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        visited[x] = true;
        distance[x] = 0;

        while(!q.isEmpty()){
            int curr = q.poll();
            for(int next : graph.get(curr)){
                if(!visited[next]){
                    distance[next] = distance[curr] + 1;
                    if(next == y) return distance[next];
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
        return -1;
    }
}
