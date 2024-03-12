import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1012 {

    static class Pair{
       int x;
       int y;

       public Pair(int x, int y){
           this.x = x;
           this.y = y;
       }
    }

    static int M;
    static int N;
    static boolean[][] visited;
    static int[][] grid;
    static Queue<Pair> q = new LinkedList<>();
    private static void push(int x, int y){
        visited[x][y] = true;
        q.add(new Pair(x,y));
    }

    private static boolean inRange(int x, int y){   //경계체크
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    private static void bfs(){
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};
        while(!q.isEmpty()){
            Pair curr = q.poll();
            for(int d = 0; d < 4; d++){
                int newX = curr.x + dx[d];
                int newY = curr.y + dy[d];
                if(inRange(newX, newY) && grid[newX][newY] == 1 && !visited[newX][newY]){
                    push(newX, newY);
                }
            }

        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int testCase = 0; testCase < T; testCase++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            grid = new int[M][N];
            for(int k = 0; k < K; k++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                grid[x][y] = 1;
            }


            //로직
            int ans = 0;
            visited = new boolean[M][N];
            for(int i = 0; i < M; i++){
                for(int j = 0; j < N; j++){
                    if(grid[i][j] == 1 && !visited[i][j]){  //현재 위치에서 벌레가 갈 수 있는 곳 모두 체크하기
                        push(i,j);
                        bfs();
                        ans++;  //벌레 한마리 갈 수 있으므로 +1
                    }
                }
            }

            sb.append(ans).append('\n');
        }
        System.out.println(sb);

    }
}
