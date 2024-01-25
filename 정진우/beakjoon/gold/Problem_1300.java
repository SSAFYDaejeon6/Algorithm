package com.ssafy.baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_1300 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;
	static int ans=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
			
		// N*N인 배열에서 K번 째 수를 찾는 것은
		// 모든 배열의 수 중에 어떤 수 S보다 작거나 같은 수의 개수가 K개보다 작거나 같은 것을 구하는것
		// K개보다 크다면 찾는 숫자의 범위를 키워서 다시 검색해야한다
		// 이때 어떤 수 S보다 작거나 같은 수는
		// 모든 행에 대하여 min( S/행의 idx, N )를 더한 값이며 이 값이 K보다 작으면 범위를 키우고
		// K보다 크면 범위를 줄여서 S를 찾는다.		
		// B[K]는 항상 K보다 작거나 같으므로 최대값을 K로 한 이분탐색을 실행하면 된다.
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		
		binarySearch(1, (1+K)/2, K); // 1에서 K까지 중간값은 (1+K)/2
		System.out.println(ans);
		
		
	}
	
	public static void binarySearch(int s, int m, int e) {
//		System.out.println(s + " " + m + " " + e);
		if(s > e) return;
		
		int count = 0;
		for(int i=1; i<=N; i++) {
			count += Math.min(m/i, N);
//			System.out.println(count);
		}
		
		if(count < K) {
			int next_s = m+1;
			binarySearch(next_s, (next_s+e)/2, e); // 큰 범위 를 탐색
		} else if (count >= K) {
			int next_e = m-1;
			ans = m;
			binarySearch(s, (s +next_e)/2, next_e); // 작은 범위를 탐색
		}
	}

}
