import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    50 * 50의 격자에서
    육지로만 이동이 가능
    육지에서 서로 거리가 제일 먼 육지
    2곳에 보물이 묻혀있음

    보물이 붇힌 두 곳 간의 최단거리를 구하자

    범위가 크지 않기에 완탐 돌려도 될듯
    최단거리 구해야 하니까 레벨별 bfs 사용
    170568KB | 416ms
 */
public class 보물섬 {
    static char[][] map;
    static boolean[][] visited;
    static int N, M;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int dist;

    static class Loc{
        int x, y;
        public Loc(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static void bfs(int x, int y){
        Queue<Loc> q = new ArrayDeque<>();
        visited = new boolean[N][M];
        visited[x][y] = true;
        q.add(new Loc(x, y));
        int cnt = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++) {
                Loc loc = q.poll();
//                System.out.println(loc.x + " " + loc.y + " " + cnt);
                for (int k = 0; k < 4; k++) {
                    int nx = loc.x + dx[k];
                    int ny = loc.y + dy[k];
                    if (!rangeCheck(nx, ny)) continue;
                    if (map[nx][ny] == 'W') continue;
                    if (visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    q.add(new Loc(nx, ny));
                }
            }
            cnt++;
        }
//        System.out.println(cnt);
        dist = Math.max(cnt-1, dist);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 'L')
                    bfs(i, j);
//                System.out.println();
            }
        }

        System.out.println(dist);

    }
}