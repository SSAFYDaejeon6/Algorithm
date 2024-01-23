package com.ssafy.baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem_2477 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		int[] arr = new int[6];
		
		String str = br.readLine();
		int K = Integer.parseInt(str);
		
		int maxWidth = 0;
		int maxHeight = 0;
		for(int i=0; i<6; i++) {
			str = br.readLine();
			StringTokenizer st = new StringTokenizer(str);
			int direction = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());			
			
			if(direction == 4 | direction == 3) 
				maxHeight = Math.max(maxHeight,  value);
			if(direction == 1 | direction == 2)
				maxWidth = Math.max(maxWidth, value);
						
			arr[i] = value;
		}
//		System.out.println(maxHeight + " " + maxWidth);
		int result = maxHeight * maxWidth;
		for(int i=0; i<12; i++){
			if((arr[i%6]) == maxHeight || (arr[i%6]) == maxWidth) {
				if((arr[(i+1)%6]) == maxHeight || (arr[(i+1)%6]) == maxWidth) {
					result -= arr[(i+4)%6] * arr[(i+3)%6];
					break;
				}
			}
		}
		
//		System.out.println(result);
		System.out.print(result * K);
		
	}
}


