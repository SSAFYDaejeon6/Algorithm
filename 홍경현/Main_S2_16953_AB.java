package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 11480kb 76ms
 * 문제 해석
	정수 A → B로 바꾸기
	1) 2를 곱한다
	2) 1을 수의 가장 오른쪽에 추가
	A를 B로 바꾸는데 필요한 최솟값
	
	입력
	A, B (1≤A<B≤10^9)
	
	출력
	A를 B로 바꾸는데 필요한 연산의 최솟값 +1 
	만들 수 없으면 -1
	
	문제 해결 프로세스
	1. B%10 == 1 이면 1을 제거 (B/10)
	2. B%10 != 1 이면 2로 나눔 (B/2)
	3. A>=B || B%2==1 이 될 때까지 반복
	4. A==B면 count 출력, 그 외 -1 출력
 */

public class Main_S2_16953_AB {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int count = 1;
		
		while(B>A) {
			if(B%10 == 1) B /= 10;
			else if(B%2 == 0) B /= 2;
			else break;
			count++;
		}
		
		System.out.println(A==B?count:-1);
	}
}
