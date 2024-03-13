import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ] 17070 파이프 옮기기 1
// 11784KB  |	76ms
// 풀이 : dp[i][j][d] : 놓이는 방향이 d(가로, 세로, 대각선)일 때, i,j에 파이프가 놓일 수 있는 경우의 수
// j = 2부터 한 이유 : 첫 시작이 (0,0), (0,1) 에서 가로로 시작하기 때문에 어느 행에서도 열 0~1에 파이프의 끝점이 갈 수 없음
public class B17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][N][3];    //0 : 가로, 1 : 세로, 2 : 대각선
        dp[0][1][0] = 1;
        for(int i = 0; i < N; i++){
            for(int j = 2; j < N; j++){
                if(map[i][j] == 1) continue;
                //가로로 놓기 -> 이전 파이프가 가로 + 대각선으로 놓은 경우
                if(j-1 >= 0 ){
                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
                }
                //세로로 놓기 -> 이전 파이프가 세로 + 대각선으로 놓은 경우
                if(i-1 >= 0){
                    dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
                }
                //대각선으로 놓기 -> 이전 파이프가 가로 + 세로 + 대각선으로 놓은 경우
                if(i-1 >= 0 && j-1 >= 0 && map[i-1][j] == 0 && map[i][j-1] == 0){
                    dp[i][j][2] = dp[i-1][j-1][2] + dp[i-1][j-1][0] + dp[i-1][j-1][1];
                }
            }
        }

        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
    }
}
