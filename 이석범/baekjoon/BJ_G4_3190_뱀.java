import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
NxN 보드위
상하좌우에 벽이 존재
0,0에서 시작 뱀의 길이는 1이고 처음에는 R로 향함
매초 규칙에 따름
1. 먼저 몸길이를 늘려 다음칸에 위치
2. 벽이나 자기자신과 부딪히면 게임이 끝남
3. 만약 이동칸에 사과가 있다면 사과는 없어지고 꼬리는 움직이지 않으
4. 이동칸에 사과가 없다면 꼬리 부분은 없어짐
몇초에 끝나는지 계산
n은 2~100 보드 크기
k는 0~100 사과 개수
r c로 사과 위치
L 변환 횟수
num dir로 x초에 dir만큼 회전

먼저 사과는 2로 하고 뱀은 1로 하기 그리고 꼬리와 머리를 노드에 저장 -> 덱큐로 앞뒤로 빼면서하기
변환 정보는 배열에 저장하기?
x는 1만초까지
12512KB 84ms
 */
public class BJ_G4_3190_뱀 {

    //그래프
    static int[][] graph;


    //특정시간에 회전하는 정보 저장 최대 1만
    static char[] timeList = new char[10_001];;
    static int n, apple;

    //좌표 저장할 노드 클래스
    static class Node {
        int r;
        int c;
        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        //출력해보기 위해 tostring
        @Override
        public String toString() {
            return r+" "+c;
        }
    }

    //처음에는 오른쪽
    static final int RIGHT = 3;
    static int dir = RIGHT;
    //static int dir = 3;

    //방향 변경
    static void changeDir(char change) {
        switch (change) {
            //delta 는 위 왼쪽 아래 오른쪽 순서
            //l이 왼쪽으로 회전, d는 오른쪽으로 회전
            case 'L':
                //회전할때 1을 더하면 넘어가므로 %4를 해야함
                dir = (dir + 1) % 4;
                break;

            case 'D':
                //-1을 빼는데 음수가 되면 3으로 변경
                dir -= 1;
                if(dir < 0) dir = 3;
                break;
        }
    }


    //뱀의 정보를 저장하기 위한 덱큐
    static Deque<Node> snake = new ArrayDeque<>();


    //위 왼쪽 아래 오른쪽
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    //결과 저장
    static int result = 0;

    //움직임 메서드
    static void move(int time) {
        //다음 위치의 r,c 값
        int nr = snake.peekLast().r + dr[dir];
        int nc = snake.peekLast().c + dc[dir];

        //만약 갈 수 없으면 그때의 시간을 저장
        if(!check(nr, nc)) {
            result = time;
            return;
        }
        //갈 수 있는 경우
        //만약 이 시간에 바꿔야 되는 경우 즉 공백이 아닌 경우 방향 변경
        if(timeList[time]!=' ') {
            changeDir(timeList[time]);
        }
        //다음 시간으로 움직임
        move(time+1);
    }

    static boolean check(int r, int c) {
        //사과 일 경우 헤드를 늘림 큐에 뒤에 붙이고 현재 r c좌표에 1을 함
        if(graph[r][c] == 2) {
            Node head = new Node(r, c);
            snake.addLast(head);
            graph[r][c] = 1;
            return true;
        } else if (graph[r][c] == 1) {
            //벽이거나 자기자신을 만날때 false 반환
            return false;
        } else {
            //빈 곳일 때 헤드를 넣고 꼬리를 빼야함
            Node head = new Node(r, c);
            snake.addLast(head);
            graph[r][c] = 1;

            //어차피 비지 않을 거임
            Node tail = snake.pollFirst();
            graph[tail.r][tail.c] = 0;

            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        //그래프 크기 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new int[n+2][n+2];


        //벽 생기기
        for(int r=0; r<n+2;r++) {
            for(int c=0; c<n+2;c++) {
                if(r==0 || c==0 || r==n+1||c==n+1) graph[r][c] = 1;
            }
        }

        //초기 뱀 위치 1, 1
        graph[1][1] = 1;
        snake.push(new Node(1, 1));
/*

        for (int[] ints : graph) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
*/

        //사과 정보 입력
        st = new StringTokenizer(br.readLine());
        apple = Integer.parseInt(st.nextToken());
        for(int i=0; i<apple;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[r][c] = 2;
        }

        //방향정보 입력
        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        for(int i=0; i<l;i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            timeList[time] = dir;
        }

        //움직임 시작 메서드 시간은 1초로 해야함
        move(1);

        System.out.println(result);

    }
}
