package algo0425;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 상하좌우를 이동하는데 1이 올라가는 것이 아닌
 * 배열의 각 지점마다 가중치가 있기 때문에
 * 다익스트라 사용
 * 18460KB | 188ms
 */
public class 특정한최단경로 {
	static final int MAX_VALUE = 200000000;
	
	static int N, E, nx, ny;
	static List<Loc>[] list;
	static int res1, res2;

	static class Loc implements Comparable<Loc>{
		int cur, w;
		public Loc(int cur, int w) {
			this.cur = cur;
			this.w = w;
		}
		@Override
		public int compareTo(Loc o) {
			return this.w - o.w;
		}
		
		
	}
	static int dijkstra(int s, int e) {
		PriorityQueue<Loc> q = new PriorityQueue<>();
		int[] d = new int[N+1];
		Arrays.fill(d, MAX_VALUE);
		
		q.add(new Loc(s, 0));
		d[s] = 0;
		
		while(!q.isEmpty()) {
			Loc l = q.poll();
			
			if(d[l.cur] < l.w) continue;
			
			for(int i=0; i<list[l.cur].size(); i++) {
				Loc nl = list[l.cur].get(i);
				
				if(d[nl.cur] > d[l.cur] + nl.w) {
					d[nl.cur] = d[l.cur] + nl.w;
					q.add(new Loc(nl.cur, d[nl.cur]));
				}
			}
		}
		return d[e];
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new List[N+1];
		
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[x].add(new Loc(y, w));
			list[y].add(new Loc(x, w));
		}
		st = new StringTokenizer(br.readLine());
		nx = Integer.parseInt(st.nextToken());
		ny = Integer.parseInt(st.nextToken());
		
		res1 += dijkstra(1, nx);
		res1 += dijkstra(nx, ny);
		res1 += dijkstra(ny, N);
		
		res2 += dijkstra(1, ny);
		res2 += dijkstra(ny, nx);
		res2 += dijkstra(nx, N);
		
		if(res1 >= MAX_VALUE && res2 >= MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(Math.min(res1, res2));
		}
		
	}
}