package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 21,640 kb    |   140 ms
// 풀이 : 각 사람마다 dfs() 수행 -> 끝나면 count++
public class Solution_7465_창용마을무리의개수 {
    static int N;
    static List<Integer>[] adjList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int test = 1; test <= T; test++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            adjList = new List[N+1];
            for(int i = 1; i <= N; i++){
                adjList[i] = new ArrayList();
            }
            for(int i = 1; i <= M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adjList[a].add(b);
                adjList[b].add(a);
            }

            //로직
            int answer = 0;
            visited = new boolean[N+1];
            for(int i = 1; i <= N; i++){
                if(!visited[i]){
                    dfs(i);
                    answer++;
                }
            }
            sb.append('#').append(test).append(' ').append(answer).append('\n');

        }
        System.out.println(sb);
    }

    static void dfs(int start){
        visited[start] = true;

        for(int next : adjList[start]){
            if(!visited[next]){
                dfs(next);
            }
        }
    }
}
