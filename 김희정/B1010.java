import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [BOJ] 1010 다리 놓기
// 14383KB | 128ms
public class B1010 {
    static int[][] dp = new int[30][30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            sb.append(comb(m,n)).append('\n');  //조합 Top-down 재귀로 해결
        }
        System.out.println(sb);
    }

    private static int comb(int m, int n){
        if(dp[m][n] > 0) return dp[m][n];   //이미 계산한 경우 리턴
        if(m == n || n == 0) return dp[m][n] = 1;   // nCn = nC0 = 1
        return dp[m][n] = comb(m-1,n-1) + comb(m-1,n);      //nCr = n-1Cr-1 + n-1Cr
    }
}
