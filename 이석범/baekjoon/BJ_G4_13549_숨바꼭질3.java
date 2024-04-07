package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//20140kb 288ms
public class BJ_G4_13549_숨바꼭질3 {
	//최댓값
	static final int MAX = 100_001;

	static int start, end;

	static boolean[] visited;

	static class Node {
		int idx;
		int time;

		Node(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}


	}

	static void Dijstra() {
		//시간이 적고 같으면 idx가 낮은 순으로 해야
		//4 6일때 1이 나옴 4 -> 3 -> 6
		//idx 적용안하면 4 -> 5 -> 6이 됨
		PriorityQueue<Node> pq = new PriorityQueue<>(
				(o1, o2) -> o1.time-o2.time == 0 ? o1.idx - o2.idx: o1.time-o2.time
				);
		pq.offer(new Node(start, 0));
		visited[start] = true;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			//System.out.println(node.idx+" "+node.time);
			int idx = node.idx;
			int time = node.time;
			
			if(idx==end) {
				System.out.println(time);
				return;
			}

			if(idx*2 <=MAX && !visited[idx*2]) {
				pq.offer(new Node(idx*2, time));
				visited[idx*2] = true;
			}

			if(idx+1 <= MAX &&!visited[idx+1]) {
				pq.offer(new Node(idx+1, time+1));
				visited[idx+1] = true;
				
			}

			if(idx-1>=0 && !visited[idx-1]) {
				pq.offer(new Node(idx-1, time+1));
				visited[idx-1] = true;
			}


		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		visited = new boolean[MAX+1];

		Dijstra();


	}
}
