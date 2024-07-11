import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
[BOJ] 21736 헌내기는 친구가 필요해
26544KB | 	264ms
풀이 : BFS로 만나는 사람 수 count
 */
public class B21736 {
    static int n;
    static int m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] campus;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        campus = new char[n][m];
        for (int i = 0; i < n; i++) {
            campus[i] = br.readLine().toCharArray();
        }
        cnt = 0;
        A : for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (campus[i][j] == 'I') {
                   BFS(i,j);
                   break A;
                }
            }
        }
        System.out.println(cnt == 0? "TT" : cnt);
    }

    static void BFS(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i, j});
        boolean[][] visited = new boolean[n][m];
        visited[i][j] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int d = 0; d < 4; d++){
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]|| campus[nx][ny] == 'X') continue;
                if(campus[nx][ny] == 'P'){
                    cnt++;
                }

                q.add(new int[]{nx,ny});
                visited[nx][ny] = true;
            }

        }
    }
}
