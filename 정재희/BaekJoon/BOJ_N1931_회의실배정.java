package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  회의가 끝나는 시간으로 정렬 후 개수 카운트
 * 	42,672KB | 428ms
 */
public class BOJ_N1931_회의실배정 {
	static class Node implements Comparable<Node>{
		int start;
		int end;
		
		public Node(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Node o) {
			return this.end == o.end? Integer.compare(this.start, o.start) : Integer.compare(this.end, o.end);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Node[] times = new Node[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			times[i]  = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(times);
		
		int cnt = 1;
		int presentEnd = times[0].end;
		for (int i = 1; i < N; i++) {
			if(presentEnd > times[i].start) continue;
			cnt++;
			presentEnd = times[i].end;
		}
		
		System.out.println(cnt);
	}

}
