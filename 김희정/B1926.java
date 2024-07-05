import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
[BOJ] 1926 그림
47716KB | 	356ms
풀이 : 정점별 BFS
 */
public class B1926 {
    static int n;
    static int m;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int max;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //로직
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 1 && !visited[i][j]){
                    BFS(new int[]{i,j});
                    count++;
                }
            }
        }

        System.out.println(count);
        System.out.println(max);


    }


    static void BFS(int[] coord){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(coord);
        visited[coord[0]][coord[1]] = true;
        int area = 0;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            area++;
            for(int i = 0; i < 4; i++){
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if(nx >= n || nx < 0 || ny >= m || ny < 0 ) continue;
                if(arr[nx][ny] == 1 && !visited[nx][ny]){
                    q.add(new int[]{nx,ny});
                    visited[nx][ny] = true;
                }
            }
        }

        max = Math.max(area, max);
    }
}
