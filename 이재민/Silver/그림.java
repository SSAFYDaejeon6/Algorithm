package algo0705;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    bfs
    37096KB | 328ms
 */

public class 그림 {
    static int N, M;
    static int[][] arr;
    static int cnt;
    static int max;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Pair> q;
    static boolean[][] visited;

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean rangeCheck(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static int bfs(int i, int j) {

        visited[i][j] = true;
        q.clear();
        q.add(new Pair(i, j));

        int count = 1;

        while(!q.isEmpty()) {
            Pair p = q.poll();
            for(int d=0; d<4; d++) {
                int x = p.x + dx[d];
                int y = p.y + dy[d];

                if(!rangeCheck(x, y)) continue;
                if(visited[x][y]) continue;
                if(arr[x][y] == 0) continue;
                visited[x][y] = true;
                count++;
                q.add(new Pair(x, y));
            }

        }

        return count;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        q = new ArrayDeque<>();
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0 ; i<N ; i++) {
            for(int j=0 ; j<M ; j++) {
                if(!visited[i][j] && arr[i][j] == 1) {
                    cnt++;
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }
}
