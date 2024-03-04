/**Main_B_2156_포도주시식_이동준 14068KB, 112ms
 * Facts
 * 	오버플로우 우려 없음.
 * 	잔의 개수가 최소 1개이기 때문에 Edge Case 조심해야 함.
 * Idea
	그냥 부분집합 구하
 * 	->터짐
 * 	DP?
 * 		N번째 잔은 다음과 같은 정보를 가진다.
 * 			자신 -1 , 자신
 * 			true, true -> N - 2 false중 최대
 * 			true, false -> N - 2 false, true 혹은 true, false
 * 			false, true -> N - 2 true 중 최대
 * 			false, false -> N - 2 true 중 최대
 * 
 * 	잔은 2차원 배열로 나타내며, 빈 잔 두개를 먼저 앞에 놓고 시작한다.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main_B_2156_포도주시식_이동준 {
	static BufferedReader br;
	final static int TT = 0;
	final static int TF = 1;
	final static int FT = 2;
	final static int FF = 3;
	
	static int N;
	static int[][] glasCal;
	static int[] glasses;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()) + 2;//+2 주의!!!!!!!!!!!!!
		glasCal = new int[N][4];
		glasses = new int[N];
		for(int i = 2; i < N; i++) {
			
			glasses[i] = Integer.parseInt(br.readLine());
			glasCal[i][TT] = getMax(new int[] {
					glasCal[i - 2][TF] + glasses[i - 1],
					glasCal[i - 2][FF] + glasses[i - 1]
			}) + glasses[i];
			glasCal[i][TF] = getMax(new int[] {
					glasCal[i - 2][TF] + glasses[i - 1],
					glasCal[i - 2][FF] + glasses[i - 1],
					glasCal[i - 2][FT] + glasses[i - 1],
			});
			glasCal[i][FT] = getMax(new int[] {
					glasCal[i - 2][TT],
					glasCal[i - 2][TF],
					glasCal[i - 2][FT],
					glasCal[i - 2][FF],
			}) + glasses[i];
			glasCal[i][FF] = glasCal[i][FT] - glasses[i];
		}
		System.out.println(getMax(glasCal[N - 1]));
	}
	
	static int getMax(int[] arr) {
		int MAX = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++) {
			MAX = Math.max(MAX, arr[i]);
		}
		return MAX;
	}
}
