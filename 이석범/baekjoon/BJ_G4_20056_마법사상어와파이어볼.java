import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
NxN의 격자에서 m개의 파이어볼 발사
행과 열은 각각 이어져 있음
행렬 은 1부터 시작
i번째 파이어볼의 정보는 r c 질량m 방향d 속력s
방향은 12시 부터 0 부터 8방향
이동 명령
1. 모든 파이어볼이 d의 방향으로 속력 s칸만큼 이동
2. 같은 칸에 파이어볼이 2개 이상있을 경우
    모두 하나로 합침
    파이어볼을 4개로 나눔
    나눈 파이어볼은 질량은 합의 5로 나눈 값의 lower
    나눈 파이어볼의 속력은 속력의 합을 개수로 나눔
    방향은 모두 홀수거나 짝수면 0246 아니면 1357
    질량이 0인 파이어볼은 소멸

입력
N M K 크기, 개수, 이동명령
r c m s d가 m개

출력 남은 질량의 합을 출력

102148kb 392ms
 */
public class BJ_G4_20056_마법사상어와파이어볼 {

    //모두 짝수이거나 홀수가 아니면 임의의 수인 100으로 함
    static final int NOT_SAME_DIV_DIR = 100;

    static int n, m, time;
    static Node[][] graph;


    //그래프에 잠시 저장할 무게, 속력, 개수, 방향을 저장
    static class Node {
        int weight, second, num, dir;
    }

    //12시부터
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    //큐에 파이어볼 내용 저장하기 위한 클래스
    static class FireBall {
        int r, c, weight, second, dir;
        FireBall(int r,int c, int weight, int second, int dir) {
            this.r = r;
            this.c = c;
            this.weight = weight;
            this.second = second;
            this.dir = dir;
        }
    }

    //큐 저장
    static Queue<FireBall> queue = new ArrayDeque<>();


    static void move() {
        //각 파이어볼 이동하기
        while(!queue.isEmpty()) {
            FireBall currentFireBall = queue.poll();

            int r = currentFireBall.r;
            int c = currentFireBall.c;
            int weight = currentFireBall.weight;
            int dir = currentFireBall.dir;
            //n바퀴 도는것 방지 -> 나중에 /n하는 곳에서는 원본을 더해야하는 주의점이 필요함
            int second = currentFireBall.second % n;

            //다음 좌표
            int nr = r + (dr[dir]*second);
            int nc = c + (dc[dir]*second);


            //경계 벗어나면 다시 0행부터 혹은 n행
            if(nr >= n) nr %= n;
            else if(nr < 0) nr = n + nr;

            if(nc >= n) nc %= n;
            else if(nc < 0) nc = n + nc;


            //+1 증가하는 것을 체크
            if(graph[nr][nc].num != 0) {
                //같은 짝수나 홀수인지 확인
                if(graph[nr][nc].dir != NOT_SAME_DIV_DIR) {
                    //다를 경우
                    if ((currentFireBall.dir % 2) != (graph[nr][nc].dir % 2)) graph[nr][nc].dir = NOT_SAME_DIV_DIR;
                } //같은 짝수나 홀수이면 안함
            } else {
                //num이 0인경우
                graph[nr][nc].dir = dir;
            }
            //그래프에 임시로 저장
            graph[nr][nc].num += 1;
            graph[nr][nc].weight += weight;
            graph[nr][nc].second += currentFireBall.second;

        }


        for(int r=0; r<n;r++) {
            for(int c=0; c<n;c++) {
                //만약 개수가 2개 이상일 경우
                if(graph[r][c].num >= 2) {
                    //위의 조건대로 하면 됨
                    divideFireBall(r, c);
                } else if(graph[r][c].num == 1){
                    //개수가 1개 인경우 그냥 큐에 넣기
                    queue.offer(new FireBall(r, c, graph[r][c].weight, graph[r][c].second, graph[r][c].dir));
                }
                graph[r][c] = new Node();
            }
        }

    }

    //조건에 맞게 floorDiv사용
    static void divideFireBall(int r, int c) {
        int divWeight = Math.floorDiv(graph[r][c].weight, 5);
        int divSecond = Math.floorDiv(graph[r][c].second, graph[r][c].num);

        //무게가 0이 되는 순간 소멸
        if(divWeight==0) return;

        //모두 짝수나 홀수이면 0 2 4 6
        if(graph[r][c].dir != NOT_SAME_DIV_DIR) {
            for(int i=0; i<8;i+=2) {
                queue.offer(new FireBall(r, c, divWeight, divSecond, i));
            }
        }
        //그외 1 3 5 7
        else {
            for(int i=1; i<8;i+=2) {
                queue.offer(new FireBall(r, c, divWeight, divSecond, i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());

        graph = new Node[n][n];
        for(int r=0; r<n;r++) {
            for(int c=0; c<n;c++) {
                graph[r][c] = new Node();
            }
        }

        for(int i=0; i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            //큐에 파이어볼 정보 넣기
            queue.offer(new FireBall(r, c, weight, second, dir));
        }

        for(int i=0; i<time;i++) {
            move();
        }

        int result = 0;
        for (FireBall fireBall : queue) {
            result += fireBall.weight;
        }
        System.out.println(result);
    }
}
