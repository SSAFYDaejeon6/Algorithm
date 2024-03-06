import java.io.*;

//[BOJ] 2156 포도주 시식
// 12808KB  |	96ms
// 풀이 :  XOOX OOXO OxOO 3가지 경우 중 가장 큰 값 dp에 저식
public class B2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] wine = new int[n+1];
        for(int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n+1];
        if(n == 1){
            System.out.println(wine[1]);
            return;
        }
        if(n==2){
            System.out.println(wine[1] + wine[2]);
            return;
        }
        dp[1] = wine[1];
        dp[2] = wine[1] + wine[2];
        dp[3] = Math.max(Math.max(dp[2],dp[1] + wine[3]), wine[2]+wine[3]);
        for(int i = 4; i <= n; i++) {
            // XOOX OOXO OxOO
            dp[i] = Math.max(Math.max(dp[i-1], dp[i-2] + wine[i]),wine[i-1] + wine[i] + dp[i-3]);
        }

        System.out.println(dp[n]);
    }
}
