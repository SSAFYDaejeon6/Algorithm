package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author SEOK BEOM LEE
 *12984kb 120ms
 */
public class BJ_G5_1038_감소하는수 {
	
	static int N;
	
	static List<Long> list = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		
		if(N<=10) System.out.println(N);
		//이게 왜 1022?
		else {
			//0번째 자리가 i인 경우
			for(int i=0; i<10;i++) {
				back(i, 1);
			}
			Collections.sort(list);
			//크기보다 클경우 -1
			if(list.size()<=N) System.out.println(-1);
			else System.out.println(list.get(N));
		}
	}
	
	//수와 자릿수
	static void back(long num, int idx) {
		
		//자릿수가 10이 넘으면 987654321 보다 큰 건 없음
		if(idx>10) return;
		
		//리스트 넣기
		list.add(num);
		
		//현재 수에 10을 곱하고 마지막자리에 숫자를 넣기
		for(int i=0; i<num % 10;i++) {
			back((num*10)+i, idx+1);
		}
	}
}
