package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author SSAFY
 *	14156kb 168ms
 * 위상 정렬로 풀면됨
 */
public class BJ_G3_2623_음악프로그램 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//나보다 뒤에 존재해야하는 수
		int[] degree = new int[N+1];
		
		//나보다 앞에 있는 수들
		List<Integer>[] list = new List[N+1];
		for(int i=1; i<N+1;i++) {
			list[i] = new ArrayList<>();
		}
 		
		
		int[] tmpInput = new int[101];
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<num;j++) {
				tmpInput[j] = Integer.parseInt(st.nextToken());
				int idx = tmpInput[j];
//				System.out.println(idx);
				for(int k=j-1; k>=0;k--) {
					int minIdx = tmpInput[k];
					
					list[idx].add(minIdx);
					degree[minIdx]++;
				}
				
			}
			
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i=1; i<N+1;i++) {
			if(degree[i]==0) queue.offer(i);
		}
		int cnt = 0;
		int[] output = new int[N];
		//출력시 거꾸로 출력
		while(!queue.isEmpty()) {
			int num = queue.poll();
			output[cnt++] = num;
			
			for(int i=0; i<list[num].size();i++) {
				int tmp = list[num].get(i);
				degree[tmp]--;
				if(degree[tmp]==0) queue.offer(tmp);
			}
			
		}
		if(cnt==N) {
			for(int i=N-1; i>=0;i--) {
				System.out.println(output[i]);
			}
		}
		else {
			System.out.println(0);
		}
		
	}
}
