import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *	11,560KB | 80ms
 */
public class BOJ_N5557_1학년 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] number = new int[N];
		long[][] cal = new long[2][21]; // i%2번째 수까지 연산했을 때, 각 값이 나왔던 개수 누적 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		cal[0][number[0]] = 1;
		for (int i = 1; i < N-1; i++) {
			int idx1 = (i-1)%2;
			int idx2 = i%2;
			for (int j = 0; j < 21; j++) {
				if(cal[idx1][j] == 0) continue;
				
				if(j + number[i] <= 20)
					cal[idx2][j+number[i]] += cal[idx1][j];
				
				if(j-number[i] >= 0) 
					cal[idx2][j-number[i]] += cal[idx1][j];
				
				cal[idx1][j] = 0;
			}
		}
		
		System.out.println(cal[N%2][number[N-1]]);
	}

}
