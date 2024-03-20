import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * The type Swea.
 * 주어지는 것
 * 미생물 위치, 미생물 수, 이동방향
 * 다음 셀로 가기
 * 약품이 칠해진 곳 도착 -> 경계도착시 절반이고 이동방향 반대
 * 두개이상이 한 셀에  도착시 합쳐짐 -> 많은 수의 미생물 수의 방향으로 이동
 * M시간 후 남은 미생물 수 총합
 * K는 천개 이하
 * M시간은 천 이하
 * 미생물 수는 만개
 *
 * 입력
 * 테케
 * n m k
 * 세로 가로 미생물 이동방향
 * 상 하 좌 우
 * 1 2 3 4 -> -1로 빼서 계산 쉽게 하기
 *
 * 우선 순위 큐에서 미생물 수로 하고 하면 될듯
 *
 * 95,080 kb
 * 메모리
 * 413 ms
 * 실행시간
 */
public class SWEA_2382_미생물격리 {
    static int N, time, M;

    static int[][] graph;

    static Queue<Node> queue;
    static PriorityQueue<Node> pq;
    static class Node {

        int r;
        int c;
        int dir;
        int num;

        Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        Node(int r, int c, int dir, int num) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.num = num;
        }
    }

    //1부터 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void move() {

        int cnt = 0;

        while (!pq.isEmpty()) {
            int size = pq.size();
//            System.out.println(size);
            while (--size >= 0) {
                Node current = pq.poll();
                int d = current.dir;
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                //일단 경계에 가면 2로 나누기
                if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
                    int num = current.num / 2;
                    //0인 경우 없어짐
                    if (num == 0) continue;

                    //0이 아니면
                    //반대방향으로 가기
                    else {
                        if (graph[nr][nc] == 0) {
                            if(d==0) d = 1;
                            else if(d==1) d = 0;
                            else if(d==2) d = 3;
                            else d = 2;
                            queue.offer(new Node(nr, nc, d));
                            graph[nr][nc] = num;
                        } else {
                            graph[nr][nc] += num;
                        }
                    }
                }

                else {
                    //값이 존재하지 않는 경우
                    if (graph[nr][nc] == 0) {
                        queue.offer(new Node(nr, nc, d));
                        graph[nr][nc] = current.num;
                    }
                    //값이 존재하는 경우
                    else {
                        graph[nr][nc] += current.num;
                    }

                }

            }
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                int r = current.r;
                int c = current.c;
                int dir = current.dir;

//                System.out.println("num "+graph[r][c]);
                pq.offer(new Node(r, c, dir, graph[r][c]));
                graph[r][c] = 0;
            }
            cnt++;
            if(cnt == time) return;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        //초기화 주의
        for(int t=1; t<=T;t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new int[N][N];


            queue = new ArrayDeque<>();
            pq = new PriorityQueue<>((o1, o2) -> o2.num - o1.num);

            for(int i=0; i<M;i++) {
                st = new StringTokenizer(br.readLine());

                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                pq.offer(new Node(r, c, dir-1, num));
            }

            move();

            int result = 0;
            while (!pq.isEmpty()) {
                int n = pq.poll().num;
//                System.out.println(n);
                result += n;
            }

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        System.out.println(sb);

    }
}
