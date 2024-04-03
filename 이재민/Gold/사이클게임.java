import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 노드들을 이었을 때 사이클이 발생하는지 판단하는 문제
 * union-find를 이용하여 사이클 발생유무 판단
 * 162984KB | 484ms
 */
public class 사이클게임 {
	static int res;
	static int N, M;
	static int[] p;
	
	static void make() {
		p = new int[N];
		for(int i=0; i<N; i++) {
			p[i] = i;
		}
	}
	
	static int find(int x) {
		if(p[x] == x) return x;
		return p[x] = find(p[x]);
	}
	
	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		// 사이클 발생
		if(x == y) return true;
		
		p[x] = y;
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		boolean check = false;
		int res = 0;
		make();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			check = union(x, y);
			res++;
			if(check) break;
		}
		
		System.out.println(check?res:0);
	}

}
