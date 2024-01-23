package com.ssafy.baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_20040 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int[] arr;
	static int order = 0;
	public static void main(String[] args) throws IOException {
		String str = br.readLine();
		
		StringTokenizer st = new StringTokenizer(str);
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1]; // 
		for(int i = 0; i<n+1; i++) { // 6이면 0~6까지 생성
			arr[i] = i;
		}
		
		for(int i = 1; i<=m; i++) {
			str = br.readLine();
			
			st = new StringTokenizer(str);
			
			int x = Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			
			union(x, y, i);
		}
		
		System.out.println(order);
		
		
	}
	
	public static void union(int a, int b, int k) {
		int A = find(a);
		int B = find(b);
		
		if( A == B && order == 0) { // cycle
			order = k;
		}
		arr[B] = A; // 큰쪽이 작은쪽을 참조
	}
	
	public static int find(int a) {
		if(arr[a] == a) {
			return a;
		} 
		
		return arr[a] = find(arr[a]);
	}
	
	

}
