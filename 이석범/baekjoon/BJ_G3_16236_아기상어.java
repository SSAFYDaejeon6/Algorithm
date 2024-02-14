import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
기본적인 컨셉은 bfs를 사용하면서 일치할 경우를 찾는 것
다만 우선순위 큐를 사용하여 상단 왼쪽 위에 해당하는 것을 먼저 찾도록 사용함

 */
public class BJ_G3_16236_아기상어 {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] graph;
    static Node shark;
    static int n;

    static class Node {
        int x;
        int y;
        int dir;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int sharkSize = 2;
    static int eat = 0;
    static int move = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        for(int x=0; x<n;x++) {
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<n;y++) {
                graph[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        for(int x=0; x<n;x++) {
            for(int y=0; y<n;y++) {
                if(graph[x][y] == 9) {
                    shark = new Node(x, y);
                    graph[x][y] = 0;
                    break;
                }
            }
        }

        while (true) {
            PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) ->
                    o1.dir != o2.dir ? Integer.compare(o1.dir, o2.dir) : (o1.x != o2.x ? Integer.compare(o1.x, o2.x) : Integer.compare(o1.y, o2.y))
            );
            boolean[][] visited = new boolean[n][n];

            queue.offer(new Node(shark.x, shark.y, 0));
            visited[shark.x][shark.y] = true;

            boolean flag = false;

            while (!queue.isEmpty()) {
                shark = queue.poll();
                if (graph[shark.x][shark.y] != 0 && graph[shark.x][shark.y] < sharkSize) {
                    graph[shark.x][shark.y] = 0;
                    eat++;
                    move += shark.dir;
                    flag = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = shark.x + dx[i];
                    int ny = shark.y + dy[i];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if (visited[nx][ny]) continue;
                    if (graph[nx][ny] > sharkSize) continue;

                    queue.offer(new Node(nx, ny, shark.dir + 1));
                    visited[nx][ny] = true;
                }
            }
            if (!flag) break;
            if (sharkSize == eat) {
                sharkSize++;
                eat = 0;
            }

        }
        System.out.println(move);

    }
}