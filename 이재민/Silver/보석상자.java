import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 42600KB | 512ms
 */
public class 보석상자 {

	static int N, M;
	static int max;
	static int[] arr;
	
	static int jewelry() {
		int s = 1;
		int e = max;
		int res = e;
		while(s <= e) {
			int m = (s + e) / 2;
			System.out.println(s + " " + e + " " + m);
			int sum = 0;
			
			for(int i=0; i<M; i++) {
				if(arr[i] % m == 0) sum += arr[i] / m;
				else sum += arr[i] / m + 1;
			}
			// 보석을 나누어 준 학생 수가 input에서 정한 학생 수 보다 많으면
			// 즉 M > N보다 큰 경우이기 때문에 답이 도출되지 않음
			// (1 ≤ M ≤ 300,000, M ≤ N) 문제 조건식
			// 나눠주는 보석의 수가 작으면 보석을 나눠주는 학생 수가 많아지기 때문에
			// 질투심을 더 크게 나누어 줄 수 밖에 없음
			if(sum > N) {
				s = m + 1;
			}
			
			// 보석을 나누어 준 학생 수가 input에서 정한 학생 수보다 작으면?
			// 문제 조건에 보석을 받지 않아도 되는 학생이 존재
			// 그러므로, 위 if 조건이 만족하기 전 까지 계속 확인해봐야 함 (답의 최소값 도출)
			else {
				res = Math.min(res, m);
				e = m-1;
			}
			
		}
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		
		
		for(int i=0; i<M; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(arr[i], max);
		}
		
		Arrays.sort(arr);
		
		System.out.println(jewelry());
	}

}