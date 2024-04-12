package algo0408;
/**
 * 11556kb	76ms
 * 
 * 
 * 합이 최소가 되는 2면을 구하는 방법
 * 전개도 상으로, 0-5, 1-4, 2-3번은 서로 마주 본다.
 * 따라서 2면을 선택할 때는 마주보지 않는 두 면을 선택하기
 * -> 0,5 중 하나 선택 / 1234 중 하나 선택
 * 
 * 합이 최소가 되는 3면을 구하는 방법
 * 0-5, 1-4, 2-3 중 각각 하나씩 뽑기
 * 그 중 최소를 구하기
 * 
 * 최소의 1면을 구하는 방법
 * 주사위에 써 있는 숫자중 최소값을 구하기
 * 
 * 
 * 주사위를 맨 윗줄부터 판단
 * (1) 맨 윗줄
 * 3면*4 + 2면*(N-2)*4 + 1면*(N-2)^2
 * (2) 나머지
 * 2면*4 + 1면*(N-2)*4
 * => (1) + (2) 하면 답이 나옴
 *
 * 
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1041_주사위 {
	static int N;
	static int[] num = new int[6];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<6; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		//N = 1인 경우에는 가장 큰 값 빼고 전부 더해서 답 나옴
		if (N == 1) {
			Arrays.sort(num);
			System.out.println(num[0]+num[1]+num[2]+num[3]+num[4]);
			return;
		}
		
		// N=1이 아닌 경우
		int com1 = Math.min(num[0], num[5]);
		int com2 = Math.min(num[1], num[4]);
		int com3 = Math.min(num[2], num[3]);
		
		long sum3 = com1+com2+com3; //3면의 최소합
		
		int min1 = Integer.MAX_VALUE;
		for (int i=1; i<=4; i++) {
			min1 = Math.min(min1, num[i]);
		}
		
		int[] temp2 = {num[0], num[2], num[3], num[5]};
		int min2 = Integer.MAX_VALUE;
		for (int i : temp2) {
			min2 = Math.min(min2, i);
		}
		
		int[] temp3 = {num[0], num[1], num[4], num[5]};
		int min3 = Integer.MAX_VALUE;
		for (int i : temp3) {
			min3 = Math.min(min3, i);
		}
		
		long sum2 = Integer.MAX_VALUE; //2면의 최소합
		int[] min = {com1+min1, com2+min2, com3+min3};
		for (int i : min) {
			sum2 = Math.min(sum2, i);
		}
		
		long sum1 = Integer.MAX_VALUE; //1면의 최소합
		for (int i : num) {
			sum1 = Math.min(sum1, i);
		}
		
		
		long ans_sum1 = sum3*4 + sum2*(N-2)*4 + sum1*(N-2)*(N-2); //맨 윗 줄
		long ans_sum2 = sum2*4 + sum1*(N-2)*4; //나머지

		long ans = ans_sum1 + ans_sum2*(N-1);
		System.out.println(ans);
		
	}

}
