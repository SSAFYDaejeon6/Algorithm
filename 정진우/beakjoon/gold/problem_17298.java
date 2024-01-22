package com.ssafy.baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Problem_17298 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> A = new ArrayList<>(1_000_000);
		
		String str = br.readLine();
		
		StringTokenizer st = new StringTokenizer(str);
		
		int N = Integer.parseInt(st.nextToken());
		

		String[] str2 = br.readLine().split(" ");
				
		
		StringBuilder sb = new StringBuilder();
		
		int[] intArray = new int[N];
				
		for(int i=0; i<N; i++) {
			intArray[N-i-1] = getNGE(A, Integer.parseInt(str2[N-i-1])); 
		}
		
		for(int i=0; i<N; i++) {
			sb.append(intArray[i] + " ");
		}
		System.out.println(sb.toString().trim());
					
	}
	
	public static int getNGE(ArrayList<Integer> A, int B) {
		if(A.isEmpty()) { // 비어 있으면 가장 오른쪽 수를 넣음
			A.add(B);
			return -1;
		}
		
		
		while(A.get(A.size() - 1 ) <= B) { // 스택에 나보다 작은것이 있을 경우
			A.remove(A.size() - 1); // 제거
			if(A.isEmpty()) {
				A.add(B); // 자기자신을 넣고 리턴
				return -1;
			}
		}
		
		int return_value =  A.get(A.size()-1);
		
		if (A.isEmpty()) {
			A.add(B); //넣고
		 	return -1;
		} else {
			A.add(B);
			return return_value;
		}
	}
}
