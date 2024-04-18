package algo0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 순서를 세워야하는 위상정렬 문제
 * 문제의 조건에서 세울수 없는 조건은 위상정렬 시 사이클 발생할 때임
 * 위상정렬은 사이클이 발생하는 조건에서는 사용할 수 없음
 * 12968KB | 124ms
 */

public class 음악프로그램 {

	static int N, M;
	static List<Integer>[] list;
	static int[] singer;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		singer = new int[N+1];
		list = new List[N+1];
		q = new ArrayDeque<>();
		

		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int seq = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = 0;
			for(int j=0; j<seq-1; j++) {
				b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				a = b;
				// 현재 가수 이전에 공연하는 가수가 몇명인지
				// 진입차수
				singer[b]++;
			}
		}
		
		
		while(q.size()<N) {
			int x = 0;
			for(int i=1; i<=N; i++) {
				if(singer[i] == 0) {
					x = i;
					singer[i]--;
					q.add(i);
					break;
				}
			}
			// 위상정렬은 진입차수가 0인 노드를 가져와서 제거하는 것
			//  진입차수가 0인 노드가 없어서
			// 위의 if문이 실행이 되지 않으면 사이클 발생
			if(x==0) {
				System.out.println(0);
				return;
			}
			for(int i=0; i<list[x].size(); i++) {
				int k = list[x].get(i);
				// 현재 노드에서 다음 순서 노드의 진입차수를 깎아줌
				// 현재 노드는 없어진것으로 했기 때문임
				singer[k]--;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			sb.append(q.poll()).append('\n');
		}
		System.out.println(sb);
		
	}

}
