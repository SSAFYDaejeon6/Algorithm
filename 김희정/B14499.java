import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ] 14499 주사위 굴리기
// 11852KB  |	84ms
public class B14499 {
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] dice = new int[6]; // 밑, 동, 서 ,북, 남, 윗면
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dx = {0, 0, 0, -1, 1}; // 동, 서, 북, 남 : 1부터 시작
        int[] dy = {0, 1, -1, 0, 0};
        for (int i = 0; i < K; i++) {
            //명령
            int command = Integer.parseInt(st.nextToken());
            // 0. 만약 범위 벗어나는 명령이면 continue
            // 1. 주사위 이동 -> 각 면의 값 바꾸기 -> 윗면 출력
            // 2. 이동한 칸 체크
            //  - 이동한 칸에 쓰여있는 수가 0이면, 칸 = 주사위 바닥값 쓰여짐
            //  - 0이 아니면, 주사위 바닥 = 칸의 값, 칸의 값 = 0
            int nx = x + dx[command];
            int ny = y + dy[command];
            if(!inRange(nx, ny)) continue;
            int temp = dice[0];
            dice[0] = dice[command];
            dice[command] = dice[5];
            int next = command % 2 == 0? command - 1 : command + 1;
            dice[5] = dice[next];
            dice[next] = temp;
            sb.append(dice[5]).append('\n');    //윗면 출력
            //이동한 칸 체크
            if(map[nx][ny] == 0){
                map[nx][ny] = dice[0];
            }else{
                dice[0] = map[nx][ny];
                map[nx][ny] = 0;
            }

            x = nx;
            y = ny;
        }
        System.out.println(sb);
    }

    static boolean inRange(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
