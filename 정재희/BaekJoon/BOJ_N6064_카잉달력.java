package BaekJoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * X 또는 Y 목표 중 큰 수에 맞춰 횟수 증가
 * 시간: 512ms | 메모리: 39,728KB
 */
public class BOJ_N6064_카잉달력 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x_ = Integer.parseInt(st.nextToken());
			int y_ = Integer.parseInt(st.nextToken());
			
			
			int loop = lcm(N, M);  // 최소 공배수
			
			/* M이 큰 경우, x 고정 / N이 큰 경우, y 고정 | 고정되지 않은 수가 목표와 같아질 때까지 반복 */
			int cnt = x_;
			int target = y_;
			int step = N;
			int inc = M;
			
			if(M < N) {
				cnt = y_;
				target =  x_;
				step = M;
				inc = N;
			}
			int ans = -1;
			while(cnt <= loop){	
				ans = (cnt%step == 0? step : cnt%step); 
				if(target == ans) break;
				cnt += inc;
			}
			
			sb.append((ans == target)?cnt : -1).append("\n");
		}
		System.out.println(sb);
	}
	static int gcd(int a, int b) {
		while(b!=0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
	static int lcm(int a, int b) {
		return a*b / gcd(a, b);
	}
}
