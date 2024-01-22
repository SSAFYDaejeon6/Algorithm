package com.ssafy.baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Sangdam{
	int T;
	int P;
	
	Sangdam(int T, int P){ // 상담 Pair
		this.T = T;
		this.P = P;
	}
}

public class problem_14501 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {		
		String st = br.readLine();
		int N = Integer.parseInt(new StringTokenizer(st).nextToken());
		
		Sangdam[] sd = new Sangdam[N];
		int[] maxDay = new int[N];
		
		
		for(int i=0; i<N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st2.nextToken());
			int P = Integer.parseInt(st2.nextToken());
			
			sd[i] = new Sangdam(T, P);
		}
		
		maxDay[0] = (sd[0].T <= N) ? sd[0].P : 0; // 처음 상담이 N을 초과해 못하는 경우에 0 아니면 했다고 가정하고 P값 할당
		
		for(int i=1; i<N; i++) {
			// i 일에 한다고 가정 했을때 maxDay는 그 때 받을 수 있는 최대값,
			if (sd[i].T + i > N) { // 상담 일수가 N을 초과하는 경우
				
				maxDay[i] = Arrays.stream(maxDay).max().getAsInt(); // 아무것도 못하고 그전에 할 수 있는 값의 최댓값
//				System.out.println(maxDay[i-1]);
				continue;
			} 
			
			for(int j=0; j<i; j++) {
				if(sd[j].T + j <= i) { // 상담 가능 할 떄
					maxDay[i] = Math.max(maxDay[i], maxDay[j] + sd[i].P); // 오늘 + 이전 상담 중 가능한 상담의 값 더함 
				} else { // 당일 상담만 한다고 가정
					maxDay[i] = Math.max(maxDay[i], sd[i].P); // 상담 못하면 그냥 당일 상담만
				}
			}
			  
		}
		

		System.out.println(Arrays.stream(maxDay).max().getAsInt()); // 최댓값 출력
	}
}
