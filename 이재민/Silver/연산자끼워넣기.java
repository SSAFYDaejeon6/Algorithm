import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * n의 범위가 크지 않아 재귀를 이용한 완전탐색
 * 같은 것이 있는 순열로 순열에서 중복되는 것을 팩토리얼로 나눠주면 됨
 * 예제 3번의 input은 11234 ->  5! / 2!로 60가지의 경우의 수가 나옴
 */

public class 연산자끼워넣기 {

	static int[] arr;
	static int[] oper;
	// 끝까지 0보다 작거나 큰값이 나올수도 있기 때문에 int형에서 제일 큰 수로 초기화
	static int maxRes = Integer.MIN_VALUE; 
	static int minRes = Integer.MAX_VALUE; 
	static int n;
	static int c;
	
	public static void operation(int idx, int res, int cnt) {
		
		// 연산자를 다 끼워 넣었으면 max min 변수에 값을 넣고 종료
		if(cnt == n-1) {
			c++;
			maxRes = Math.max(maxRes, res);
			minRes = Math.min(minRes, res);
			return ;
		}
		
		
		// 재귀를 통한 연산 수행
		// 순열과 유사한 로직
		for(int i=0; i<4; i++) {
			if(oper[i] > 0) {
				oper[i]--;
				if(i==0) {
					operation(idx+1, res + arr[idx], cnt+1);
				}
				else if(i==1) {
					operation(idx+1, res - arr[idx], cnt+1); 
				}
				else if(i==2) {
					operation(idx+1, res * arr[idx], cnt+1); 
				}
				else {
					operation(idx+1, res / arr[idx], cnt+1); 
				}
				oper[i]++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n];
		oper = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		
		// res에 arr[0]를 넘겨 주었으니 인덱스도 1부터 시작
		operation(1, arr[0], 0);
		
		System.out.println(maxRes);
		System.out.println(minRes);
	}


}
