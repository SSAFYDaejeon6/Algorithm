package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 	14,960KB | 112ms
 * 
 *  부분집합 이용 모든 조합 검사
 *  가격이 같은 경우, 사전순으로 택하기
 */
public class BOJ_N19942_다이어트 {
	static int N;
	static int[] goal;
	static int[][] info;
	static int minCost = Integer.MAX_VALUE;
	static int minRes;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		goal = new int[4];
		info = new int[N][5];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			goal[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		subset(0, 0, new int[4], 0);
		
		if(minCost == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(minCost).append("\n");
		for (int i = 0; i < N; i++) {
			if((minRes & (1<<i)) !=0)
				sb.append(i+1).append(" ");
		}
		System.out.println(sb);
		
	}
	private static void subset(int depth, int result, int[] sum, int cost) {
		if(depth == N) {
			for (int i = 0; i < 4; i++) {
				if(sum[i] < goal[i]) return;
			}
			if(minCost > cost) {
				minCost = cost;
				minRes = result;
			}
			else if(minCost == cost) {
				if(compareBit(minRes, result)) minRes = result;
			}
			return;
		}
		
		
		int[] newSum = new int[4];
		for (int i = 0; i < 4; i++) {
			newSum[i] = sum[i] + info[depth][i];
		}
		subset(depth+1, result | 1<<depth, newSum, cost+info[depth][4]);
		subset(depth+1, result, sum, cost);
	}
	
	private static boolean compareBit(int val1, int val2) {

		StringBuilder temp = new StringBuilder();
		
		String str1 = Integer.toBinaryString(val1);
		int len1 = str1.length();
		str1 = String.format("%"+ N +"s", str1).replace(" ", "0");
		str1 = temp.append(str1).reverse().toString();
		
		temp = new StringBuilder();
		String str2 = Integer.toBinaryString(val2);
		int len2 = str2.length();
		str2 = String.format("%"+ N +"s", str2).replace(" ", "0");
		str2 = temp.append(str2).reverse().toString();
		
		
		int len = Math.min(len1, len2);
		for (int i = 0; i < len; i++) {
			if(str1.charAt(i) != str2.charAt(i)) {
				if(str1.charAt(i) == '1') return false;
				else return true;
			}
		}
		if(len1 < len2)
			return false;
		return true;
	}
}
