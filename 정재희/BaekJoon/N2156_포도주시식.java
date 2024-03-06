import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 13,112 KB | 104ms
 */
public class N2156_포도주시식 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine());
		int[] val = new int[N+1];
		for (int i = 1; i <= N; i++) {
			val[i] = Integer.parseInt(br.readLine());
		}
		int[] dp = new int[N+1];
		dp[1] = val[1];
		if(N > 1) dp[2] = val[1] + val[2];   // N=1일 때, 런타임 에러 발생
		
		//dp[3] = Max(val[1]+val[2], val[1]+val[3], val[2]+val[3]) 
		// --> val[2]+val[3]에는 dp[0]이 포함되어 있음
		
		for (int i = 3; i < N+1; i++) {
			dp[i] = Math.max(Math.max(dp[i-1], dp[i-2]+val[i]), dp[i-3]+val[i-1]+val[i]);
		}
		System.out.println(dp[N]);
	}
}
