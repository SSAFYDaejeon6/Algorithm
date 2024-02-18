import javax.management.QueryEval;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * The type Bj g 5 21610 마법사상어와비바라기.
 * 격자의 크기는 NxN
 * 1행 1열 부터 시작
 * 행과 열은 연결되어 있음 -> % 연산으로 계산 가능
 * 초기 비구름 위치
 * n,1 -> n-1 2로 4칸에서 생김
 * m번 이동 명령
 * 1부터 순서대로 9시 부터 8시까지 8방 탐색
 * 모든 구름이 di방향으로 si칸 이동
 * 각 구름에서 비내려 1만큼 증가
 * 구름 사라짐
 * 증가한 칸에서 대각선 방향으로 1칸인 칸에 물이 존재하는 수 만큼 1씩증가
 * -> 단 경계를 넘어가는 대각선은 치지 않음 주의
 * 그후 물의 양이 2이상인 모든 칸에 구름 생기고 물의 양 2줄어듬
 * -> 구름이 사라진 칸이 아닌 다른 칸
 *
 * 출력 모든 물의 양의 합
 *
 * 입력
 * n m
 * nxn개
 *
 * dir step m개
 *
 * 17820kb 152ms
 */
public class BJ_G5_21610_마법사상어와비바라기 {

    //9시부터 8방향
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int n, m;

    static int[][] graph;

    static Move[] moves;

    //움직임 저장할 클래스
    static class Move {
        int dir;
        int step;

        Move(int dir, int step) {
            this.dir = dir;
            this.step = step;
        }
    }

    //구름의 위치를 저장할 클래스
    static class Node {
        int r;
        int c;

        Node(int r, int c) {
            this.r  = r;
            this.c = c;
        }
    }

    //구름들 저장할 큐
    static Queue<Node> queue = new ArrayDeque<>();

    //구름이 있었던 위치에서 다시 구름이 발생하지 않아야함
    static boolean[][] checkGraph;

    //결과
    static int result = 0;

    //시작 메서드
    static void start(int time) {

        //만약 time이 m이면 그때의 저장된 물들의 합을 계산
        if(time==m) {
            for(int r=0; r<n;r++) {
                for(int c=0; c<n;c++) {
                    result += graph[r][c];
                    //System.out.print(graph[r][c]+" ");
                }
                //System.out.println();
            }
            return;
        }

        //현재 시간에서 이동할 방향과 이동할 스텝들 연산
        Move nextMove = moves[time];

        //n바퀴 도는 거 방지?하기 위해 % 연산으로 방지
        int step = nextMove.step % n;
        //1부터 시작 보정하기 위해 -1
        int dir = nextMove.dir--;

        //이동할 구름이 없을때까지 반복
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            //일단은 다음 칸으로 이동
            int nr = current.r + (dr[dir] * step);
            int nc = current.c + (dc[dir] * step);

            //행으로
            //오른쪽으로 넘어갔을때
            if(nr >=n) nr %= n;
            //왼쪽으로 넘어갔을때
            else if (nr < 0) nr = n + nr;

            //열에서
            if(nc >=n) nc %= n;
            else if(nc < 0) nc = n + nc;

            //다음에 또 사용방지 위해
            checkGraph[nr][nc] = true;

            //물 1 증가
            graph[nr][nc] += 1;
        }

        //대각선 물있는지 체크
        //주의 사항 위에서 할 경우 중간중간에 하기 때문에 +1되지 않은 구름들이 저장되지 않을 수 있음
        for(int r=0; r<n;r++) {
            for(int c=0; c<n;c++) {
                int plusWater = 0;
                if(checkGraph[r][c]) {
                    plusWater = checkDegark(r, c);
                }
                graph[r][c] += plusWater;
            }
        }

        //다음에 갈 구름들 저장
        for(int r=0; r<n;r++) {
            for(int c=0; c<n;c++) {
                //아까 구름 사용한 곳이라면 false로 바꾸고 continue
                if(checkGraph[r][c]) {
                    checkGraph[r][c] = false;
                    continue;
                }
                //2이상이면 현재 노드 저장
                if(graph[r][c] >= 2) {
                    queue.offer(new Node(r, c));
                    graph[r][c] -= 2;
                }
            }
        }

//        queue.forEach((o1)-> System.out.println(o1.r+" "+ o1.c));
//
//
//        for(int r=0; r<n;r++) {
//            for(int c=0; c<n;c++) {
//                System.out.print(graph[r][c]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        start(time+1);
    }

    //대각선 체크
    static int checkDegark(int r, int c) {
        //dr 1, 3, 5, 7만 체크
        int check = 0;
        for(int i=1;i<8;i+=2) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            //경계벗어나거나 0인 경우 continue
            if(nr < 0 || nr >= n || nc < 0 || nc >= n || graph[nr][nc] == 0) continue;

            check++;
        }
        return check;
    }

    public static void main(String[] args) throws IOException {
        //입력값
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        checkGraph = new boolean[n][n];
        moves = new Move[m];

        //그래프 정보
        for(int r=0; r<n;r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<n;c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        //이동 명령 정보
        for(int i=0; i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int step = Integer.parseInt(st.nextToken());
            moves[i] = new Move(dir, step);
        }

        //문제와 달리 0,0부터 시작하는게 편하므로 n-1, 0
        queue.offer(new Node(n-2, 0));
        queue.offer(new Node(n-2, 1));
        queue.offer(new Node(n-1, 0));
        queue.offer(new Node(n-1, 1));


        start(0);

        System.out.println(result);
    }
}
