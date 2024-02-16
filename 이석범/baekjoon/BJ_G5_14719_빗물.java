package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * 현재를 기준으로 왼쪽에서 제일 큰 값과 오른쪽에서 제일 큰 값을 비교해서 작은 것을 더함
 * 더할 때 자기자신에서 뺀 값을 더함
 */
public class BJ_G5_14719_빗물 {

	static int[] arr;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		arr = new int[c];
		
		int result = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<c;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<c-1;i++) {
			int left = 0;
			int right = 0;
			
			for(int j=0; j<i;j++) {
				if(left < arr[j]) left = arr[j];
			}
			
			for(int j=i; j<c;j++) {
				if(right < arr[j]) right = arr[j];
			}
			
			int num = left > right ? right: left;
			if(num - arr[i] > 0) result += num - arr[i];
		}
		System.out.println(result);
		
	}

}
