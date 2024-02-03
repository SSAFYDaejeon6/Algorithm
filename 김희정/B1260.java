import java.io.*;
import java.util.*;

public class Main{
    
    static boolean[] visited;
    static List<List<Integer>> arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        for(int i = 0; i < n+1; i++){
            arr.add(new ArrayList<Integer>());
        }
        
        
        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.get(x).add(y);    //양방향
            arr.get(y).add(x);
        }
        // 방문 번호가 여러 개인 경우, 정점 번호가 작은 것 먼저 방문
        for(int i = 0; i < n+1; i++){
            Collections.sort(arr.get(i));
        }
        
        visited = new boolean[n+1];
        dfs(v);
        System.out.println();
        visited = new boolean[n+1];
        bfs(v);
    }
    
    private static void dfs(int v){
        visited[v] = true;
        System.out.print(v + " ");
        for(int i = 0; i < arr.get(v).size(); i++){
            int x = arr.get(v).get(i);
            if(!visited[x]) dfs(x);
        }
    }
    
    private static void bfs(int v){
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visited[v] = true;
        while(!q.isEmpty()){
            int x = q.poll();
            System.out.print(x + " ");
            for(int i = 0; i < arr.get(x).size();i++){
                int y = arr.get(x).get(i);
                if(!visited[y]){
                    q.add(y);
                    visited[y] = true;
                }
            }
        }
    }
}
