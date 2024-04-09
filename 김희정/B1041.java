import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 	11588KB	|	76ms
// 풀이 : 주사위 면이 1면, 2면, 3면이 보이는 경우를 점화식으로 작성
// 3면을 고르는 경우의 수는 마주보는 면 중 작은 값들을 선택
public class B1041 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		long[] dice = new long[6];
		long max = 0;
		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, dice[i]);
		}
		
		// 3면 고르기
		long[] d = new long[3];
		d[0] = Math.min(dice[0], dice[5]);
		d[1] = Math.min(dice[1], dice[4]);
		d[2] = Math.min(dice[2], dice[3]);
		
		Arrays.sort(d);
		
		long one = d[0];
		long two = one + d[1];
		long three = two + d[2];
		
		if(N == 1) {
			long sum = 0;
			for(int i = 0; i < 6; i++) {
				sum += dice[i];
			}
			System.out.println(sum - max);
			return;
		}
		long result = 0;
		result += one * (( N - 1)*(N-2) * 4 + (N - 2) * (N - 2));
		result += (two) * ((N - 1) * 4 + (N - 2) * 4);
		result += (three) * 4;
		
		System.out.println(result);

	}
}
