package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_14888_연산자_끼워넣기 {

	static int[] numList;
	static int[] cirList;
	
	static int min, max, num;
	
	//완전탐색을 이용하여 max값, min값 계산
	static void cir(int cnt, int result) {
		
		if(cnt==num-1) {
			max = Math.max(result, max);
			min = Math.min(result, min);
			return;
		}
		
		for(int i=0; i<4;i++) {
			int tmp = result;
			if(cirList[i]==0) continue;
			switch(i) {
			case 0:
				tmp += numList[cnt+1];
				break;
			case 1:
				tmp -= numList[cnt+1];
				break;
			case 2:
				tmp *= numList[cnt+1];
				break;
			case 3:
				tmp = Math.abs(result);
				tmp = tmp / numList[cnt+1];
				if(result <0) tmp = -tmp;
				
				break;
			}
			cirList[i]--;
			cir(cnt+1, tmp);
			cirList[i]++;
		}
		return;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		num = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		numList = new int[num];
		for(int i=0; i<num;i++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}
		cirList = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4;i++) {
			cirList[i] = Integer.parseInt(st.nextToken());
		}
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		cir(0, numList[0]);
		System.out.println(max);
		System.out.println(min);
		
	}
}
