import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 많은 풀이 참고
 * DP 이용
 * dp[i][j] -> i부터 j까지의 최소 행렬 연산 횟수
 * 이해하지 못했습니다...
 */
public class BOJ_N11049_행렬곱셈순서 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][N];
		int[][] matrix = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}
		if(N == 1) {
			System.out.println(0);
			return;
		}
		if(N == 2) {
			System.out.println(matrix[0][0] * matrix[0][1] * matrix[1][1]);
			return;
		}
		
		for (int i = 1; i < N; i++) {      // 차이
			for (int j = 0; j < N-i; j++) {  // 각 행
				int k = j + i;
				dp[j][k] = Integer.MAX_VALUE;
				for (int k2 = j; k2 < k; k2++) {
					dp[j][k] = Math.min(dp[j][k], dp[j][k2] + dp[k2+1][k] + matrix[j][0]*matrix[k2][1]*matrix[k][1]);
				}
			}
		}
		
		System.out.println(dp[0][N-1]);
		
	}

}
