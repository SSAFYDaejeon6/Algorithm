package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 250656kb	1192ms
 * [문제 해석]
 * N종류의 물약 도무 구매 (1번~N번 물약)
 * 특정 물약 구매 -> 다른 물약들 할인
 * 
 * i번째 물약의 가격은 동전 Ci개
 * i번째 물약을 구매하면 Pi종류의 다른 물약의 가격이 내려감
 * 할인은 중첩됨, 최소 가격 동전 1개
 * 
 * [입력]
 * 1. N
 * 2. 물약의 가격 ci가 공백을 두고 주어짐
 * 3. 물약 할인 정보 N개
 * 할인되는 물약 개수 p
 * 물약 번호 a, 할인 가격 d
 * 
 * [출력]
 * 물약을 가장 싸게 샀을 때 동전이 몇 개 필요한지
 * 
 * [문제 해결 프로세스]
 * 순열
 */
public class Main_s1_24954_물약구매 {

	static int N, res = Integer.MAX_VALUE;
	static int[] arr;
	static boolean[] visited;
	static List<Node>[] list;
	
	static class Node {
		int a, d;

		public Node(int a, int d) {
			super();
			this.a = a;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [a=" + a + ", d=" + d + "]";
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		visited = new boolean[N+1];
		list = new List[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=N; i++) {
			int n = Integer.parseInt(br.readLine());
			for(int j=0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list[i].add(new Node(a, d));
			}
		}
		
		permu(0, new int[N]);
		
		System.out.println(res);
	}

	private static void permu(int cnt,  int[] order) {
		if(cnt == N) {
			potion(order);
		}
		
		for(int i=1; i<=N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			order[cnt] = i;
			permu(cnt+1, order);
			visited[i] = false;
		}
		
	}

	private static void potion(int[] order) {
		int sum = 0;
		int temp[] = new int[N+1];
		System.arraycopy(arr, 0, temp, 0, N+1);
		for(int i=0; i<N; i++) {
			int n = order[i];
			for(Node node : list[n]) {
				temp[node.a] = temp[node.a] - node.d >= 1 ? temp[node.a] - node.d : 1;
			}
			sum += temp[n];
		}
		
		res = Math.min(sum, res);
	}

}
