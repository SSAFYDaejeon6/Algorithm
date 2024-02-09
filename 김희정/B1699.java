import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 14700KB | 172ms
/* ex) dp[10]
3*3 +1  = dp[3*3] + dp[10-3*3] = 1 + dp[n - 3*3]
2*2 + 6 = dp[2*2] + dp[10-2*2] = 1 + dp[n- 2*2]
1*1 + 9 = dp[1*1] + dp[10-1*1] = 1 + dp[ n - 1*1] 에서 가장 작은 값
*/
public class B1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];    // dp[n] = n의 제곱수의 합 최소 항의 갯수
         for(int i = 1; i <= n; i++){
             int min = i;
            for(int j = 1; j <= Math.sqrt(i); j++){
                min = Math.min(dp[i - j*j] + 1, min);
            }
            dp[i] = min;
        }
        System.out.println(dp[n]);
    }
}
