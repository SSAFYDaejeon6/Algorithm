import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[BOJ] 11049 행렬 곱셈 순서
14508KB |	228ms서
dp[i번째 행렬][j번째 행렬] = 필요한 곱셈 연산 수 최솟값
dp[i][j] = dp[i][k] + dp[k+1][j]
 */
public class B11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N+1][2];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            matrix[i][0] = R;
            matrix[i][1] = C;
        }

        //로직
        int[][] dp = new int[N+1][N+1];

        //구간 간격을 설정해주고, 그 구간 길이에 따라 구할 수 있는 행렬의 곱셈 구하기
        for(int interval = 1; interval <= N; interval++){
            for(int i = 1; i + interval <= N; i++){ //시작점
                int j = i + interval;   //끝점
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++){ //중간 행렬
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1]);
                }
            }
        }

        System.out.println(dp[1][N]);   //  첫번째 행렬 ~ N번째 행렬 곱셈 연산 수 최솟값
    }
}
