import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//45508kb 392ms
public class BJ_S1_1926_그림 {

    static int R, C;
    static int[][] graph;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r;
        int c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int max = 0;

    //BFS
    static void BFS(int r, int c, int num) {
        int cnt = 1;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(r, c));
        visited[r][c] = true;
        graph[r][c] = num;

        while(!q.isEmpty()) {
            Node node = q.poll();
            int curR = node.r;
            int curC = node.c;

            for(int d=0; d<4;d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];

                if(nr < 0 || nr >= R || nc < 0 || nc >= C || graph[nr][nc] != 1) continue;

                graph[nr][nc] = num;
                q.offer(new Node(nr, nc));
                visited[nr][nc] = true;
                cnt++;

            }

        }

        max = Math.max(max, cnt);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new int[R][C];
        visited = new boolean[R][C];


        for(int r=0; r<R;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C;c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

//        print();

        int num = 2;
        int cnt = 0;

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(graph[r][c]==1) {
                    BFS(r, c, num++);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(max);

    }

    static void print() {
        for (int[] r : graph) {
            for (int i : r) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}