/* Main_B_6064_카잉달력_이동준 19104KB 360ms
 * Facts
 * 	년도 x, y에 대해
 * 	M = 2라고 하자
 * 	x: 1 -> 2 -> 1- >2.....
 * 	M = P 라고 하자
 *  x: 1 -> P -1 .. P -> 1...
 *  
 *  M = 2이고 K = 5 -> 1,2,1,2,1 -> 1 = (K % M)
 *  M = 2이고 K = 4 -> 1,2,1,2 -> 2 = (K % M) == 0이면 P
 *  M = 3 이고 K = 11 -> 1,2,3,1,2,3,1,2,3,1,2 -> (11 % 3)
 *  M = 3 이고 k = 12 -> 1213123213123 
 *  즉, (K % M) == 0 이면 M, 아니면 (K%M)
 *  
 * 문제 해결 프로세스
 * 
 * 	마지막 년도 E 는 M과 N의 최소공배수이다.
 * 		최소공배수를 구하려면, A * B / A와 B의 최대공약수 하면 된다.
 * 	A와 B의 최대공약수 구하기
 * 	i = 1 부터 시작해서 두 수 중 작은 쪽의 1/2 까지 진행하면서 두 수를 각각 나눈다. 그러면서 나오는 결과를 Set에 저장한다.
 * 		둘 중 큰 수 /i 를 Set에 중복 저장하려는 순간이 최대공약수이다.
 * 
 * 	x 또는 y 에 대해 찾는 년도 k의 가능한 공식을 찾을 수 있다. x또는 y로부터 k % M 또는 k % N 을 알 수 있다.
 *  
 *  즉 이 문제는 k = M * a + c_x = N * b + c_y 가 되는 두 정수 a,b가 있는지의 문제이다.
 *  
 *  (M >= N 가정)
 *  0 <= a <= M_u = (E-c_x) / M 에 대해 
 *   N*b = M * a + c_x - c_y 인 a를 찾는다. 이때 b는 정수이다.
 *   edge case 주의!!!(c_x = c_y = 0인 경우)
 *   
 * 시간복잡도 계산
 * 	M, N 최대일때 최소공배수
 *  20000회의 계산
 *  최대공약수를 계산하지 않으면 최대 8억까지 뛰기 때문에 최대공약수 구하기가 필수적이다.
 *  
 *  
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_B_6064_카잉달력_이동준{
	
	static int T;
	static int M;
	static int N;
	static int x;
	static int y;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./b/6064.txt"));
		StringTokenizer st;
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		int maxYear;
		int r_x, r_y; //x 및 y 나머지
		int Increment;
		int toDivide;
		int offset_inc, offset_div;
		int maxTrial;
		int result;
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			maxYear = getISM(M, N);
			r_x = (x == M) ? 0 : x;//x == M 이면 목표하는 수는 M의 배수이다. 아니라면 목표하는 수 % M = x이다.
			r_y = (y == N) ? 0 : y;
			if( M >= N) {
				Increment = M;
				toDivide = N;
				offset_inc = r_x;
				offset_div = r_y;
			}else {
				Increment = N;
				toDivide = M;
				offset_inc = r_y;
				offset_div = r_x;				
			}
			result = -1;//초기화
			maxTrial = (maxYear - offset_inc)  / Increment ;
			for(int trial = (offset_inc == 0) ? 1 : 0; trial <= maxTrial; trial++) {
				if((Increment * trial + offset_inc - offset_div) % toDivide != 0)  continue;//
				
				result = Increment * trial + offset_inc;
				break;
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);
		br.close();
	}
	
	public static int getASD(int a, int b){//int a와 b의 최대공약수를 구한다.
		if(a % b == 0 || b % a == 0) return Math.min(a, b);//둘이 서로 배수 관계이면 둘중 작은거 return하고 끝
		Set<Integer> divisors = new HashSet<>();
		int[] ints = {a, b};
		Arrays.sort(ints);
		a = ints[0]; b = ints[1]; //a가 더 작은 쪽이 되게 함
		int trial = a / 2;//둘 중 작은수의 절반까지만 시도하면 됨
		for(int i = 2; i <= trial; i++) {
			if(a % i == 0 && b % i == 0) {//공약수임
				divisors.add(a / i);
				if(! divisors.add(b / i)) return b / i;
			}
		}
		return 1;//둘이 서로소임
	}
	
	public static int getISM(int a, int b) {//최소공배수 구하기
		int ASD = getASD(a, b);
		return (a * b) / ASD;
	}
	
}
