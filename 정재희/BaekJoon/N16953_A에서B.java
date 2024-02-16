import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class N16953_A에서B {
	/**
	 * 시간: 80ms | 메모리: 11,524KB
	 */
	static int minCnt = Integer.MAX_VALUE;
	static long B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		
		calculation(A, 0);
		System.out.println(minCnt == Integer.MAX_VALUE? -1:minCnt+1);
	}

	private static void calculation(long num, int cnt) {
		if(num > B) {  // 목표 값보다 커진 경우, 가지치기
			return;
		}
		else if(num == B) {  // 목표 값과 같은 경우, 연산 최소 회수 갱신
			minCnt = Math.min(minCnt, cnt);
			return;
		}
		
		calculation(num*10+1, cnt+1);
		calculation(num*2, cnt+1);
	}

}
