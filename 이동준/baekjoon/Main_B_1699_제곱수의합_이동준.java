/**Main_B_1699_제곱수의합_이동준 	11992KB 156ms
 * Idea
 * 	주어진 수 N에 대해, n = sqrt(N) 보다 작은 자연수들만 합 요소로 사용 가능하다.
 * 
 * 	가장 크기가 작은 중복조합을 찾는 문제라고 할 수 있다.
 * 
 * Brute force 부터 생각해볼 수 있다.
 * 
 * 	이 과정에서 어떤 수의 제곱을 미리 계산해서 배열에 저장할 수 있다.
 * 
 * 그런데, 위 과정에서 제곱된 수 자체도 미리 저장해 놓으면, 찾고자 하는 답이 1인 경우를 바로 찾을 수 있다.
 * 
 * 
 * N의 항 수 는 N보다 작은 어떤 수(들)의 항 수의 합일 것이다.
 * 
 * 만약 N이 어떤 수 n의 제곱수라면, 답은 그냥 1이다.  그리고 이건 미리 계산해놓은 배열에서 꺼내오면 된다.
 * 
 * 아니라면, N은 두 항 이상으로 이루어져 있고, 그 중 하나는 n 보다 작은 수의 제곱수이고. 나머지는 DP의 재귀로 구하면 된다!
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_1699_제곱수의합_이동준 {
	static BufferedReader br;
	static int N; // 1 <= N <= 100_000
	static int n; //sqrt(N)
	static int[] pows;
	static int[] terms;//알아낸 수 저장. tems[9] = 1 -> 9 - 3^2 라서. 최대 4* 100000 bytes. = 400kbytes

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new FileReader("./b/1699.txt"));
		N = Integer.parseInt(br.readLine());
		//입력 끝------
		n = (int) Math.sqrt(N);
		pows = new int[n + 1];
		terms = new int[N + 1];
		for(int i = 0; i <= n; i++) {
			pows[i] = i * i;//자연수들의 제곱을 만든다
			terms[pows[i]] = 1;//해당 자연수들의 제곱합 항은 항상 1이다.
		}
		//DFS
		System.out.println(minTermDFS(N));
	}
	
	static int minTermDFS(int target) {
		if(terms[target] != 0) return terms[target];//이미 만들어 놓았으면 그냥 리턴
		//만든 적이 없음. 따라서 target의 term 수는 2 이상임.
		int minTerms = Integer.MAX_VALUE;
		for(int num = (int) Math.sqrt(target); num > 0; num--) { //term중의 하나는 일단 미리 만들어놓은 제곱결과에서 찾을 수 있음(그리고 1임). 나머지 들은 target값에서 빼서 알 수 있다.
			minTerms = Math.min(minTerms, 1 + minTermDFS(target - pows[num]));
		}
		terms[target] = minTerms;
		return minTerms;
	}
	
}
