import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 148,740KB | 476ms
 * 
 * 1. Union-find 이용
 * 2. union이 안되는 경우 -> 사이클 발생
 */
public class BOJ_N20040_사이클게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] parents  = new int[n];
		make(parents, n);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!union(parents, a, b)) {
				System.out.println(i+1);
				return;
			}
		}
		System.out.println(0);
	}

	private static void make(int[] parents, int n) {
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}
	private static int find(int[] parents, int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents, parents[a]);
	}
	private static boolean union(int[] parents, int a, int b) {
		int parentA = find(parents, a);
		int parentB = find(parents, b);
		
		if(parentA == parentB) return false;
		parents[parentB] = parentA;
		return true;
	}

}
