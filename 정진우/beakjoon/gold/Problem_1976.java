package com.ssafy.baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_1976 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int[] pts;
	public static void main(String[] args) throws IOException {
		String str = br.readLine();
		
		int N = Integer.parseInt(str) + 1;
		str = br.readLine();
		
		int M = Integer.parseInt(str);
		
		pts = new int[N];
		for(int i=0; i<N; i++) {
			pts[i] = i;
//			System.out.println(i + " " + pts[i]);
		}
		
		for(int i=1; i<N; i++) {
			str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);			
			for(int j=1; j<N; j++) { // 0이 1, 1이 2...
				int token = Integer.parseInt(st.nextToken());
//				if(j <= i || token == 0) continue; // 대각행렬 왼쪽은 안봄
				
				if(token == 1) {
//					System.out.println(i + " " + j);
					union(i, j);
					
				}
				
			}
		}
		
//		System.out.println(Arrays.toString(pts));
		
		System.out.println(answer(M));
		
	}
	public static String answer(int M) throws IOException {
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str);
		
		int group = pts[Integer.parseInt(st.nextToken())];
		
		for(int i=0; i<M-1; i++) {
			int a = Integer.parseInt(st.nextToken());
//			System.out.println(pts[a]);
			if(group != pts[a]) {
				return "NO";
			}
			
		}
		
		return "YES";
	}
	
	public static void union(int a, int b) { // 강제 i는 j보다 커질수 없음
		int ptsA = find(a);
		int ptsB = find(b);
		
		if(ptsA < ptsB) pts[ptsB] = ptsA;
		else pts[ptsA] = ptsB;
//		pts[ptsB] = ptsA; // 큰쪽을 작은쪽으로 변경
	}
	
	public static int find(int a) {
		if(pts[a] == a) return a; // 부모와 값이 같으면 인정!
		
		return pts[a] = find(pts[a]);// 아니면 ? 부모를 찾아 경로 압축하고 리턴
		
	}

}
