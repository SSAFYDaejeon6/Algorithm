package algo0709;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    dfs로 그래프 탐색
    44088KB | 244ms
 */

public class 헌내기는친구가필요해 {
    static int N, M;
    static char[][] map;
    static int res;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static void dfs(int x, int y){
        visited[x][y] = true;
        if(map[x][y] == 'P') {
            res++;
            map[x][y] = 'O';
        }

        for(int i = 0; i < dx.length; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!rangeCheck(nx, ny)) continue;
            if(visited[nx][ny]) continue;
            if(map[nx][ny] == 'X') continue;

            dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = 0;
        int y = 0;
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'I') {
                    x = i;
                    y = j;
                }
            }
        }

        dfs(x, y);

        System.out.println(res!=0?res:"TT");
    }
}
