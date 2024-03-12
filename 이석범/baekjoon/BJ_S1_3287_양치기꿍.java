import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
6 6
...#..
.##v#.
#v.#.#
#.k#.#
.###.#
...###

v가 늑대 k가 양
14016kb 104ms
 */
public class BJ_S1_3287_양치기꿍 {
    static char[][] graph;
    static int v, k;
    static int n, m;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(int x, int y) {
        for(int i=0; i<4;i++) {
            int nx = x+ dx[i];
            int ny = y+ dy[i];
            if(nx < 0 || nx >= n || ny < 0 || ny >= m || graph[nx][ny] =='#') continue;
            if(graph[nx][ny]=='v')  v++;
            if(graph[nx][ny]=='k')  k++;
            graph[nx][ny] = '#';
            bfs(nx, ny);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int vCount = 0;
        int kCount = 0;
        graph = new char[n][];
        for(int x=0; x<n;x++) {
           st = new StringTokenizer(br.readLine());
           graph[x] = st.nextToken().toCharArray();
        }

        for(int x=0; x<n;x++) {
            for(int y=0; y<m;y++) {
                if(graph[x][y]=='#') continue;
                v = 0;
                k = 0;
                if(graph[x][y] == 'v') v++;
                if(graph[x][y] == 'k') k++;
                graph[x][y] = '#';
                bfs(x, y);
                if(v < k) kCount += k;
                else vCount += v;
            }
        }
        System.out.println(kCount+" "+vCount);
    }
}
