
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// [BOJ] 20040 사이클 게임
// 137296KB	|	616ms
// 풀이 : 매 차수마다 사이클 판단 -> 사이클 판단은 union-find로 
public class B20040 {
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parent = new int[N];
		for(int i = 0; i < N; i++) {
			parent[i] = i;	//부모를 자기 자신으로 초기화 
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(!union(a,b)) {
				System.out.println(i);
				return;
			}
			
		}
		
		System.out.println(0);
	}
	
	static boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB) return false;//부모가 같으면 사이클 발생
		
		parent[parentB] = parentA;
		return true;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]); // 해당 노드가 루트가 아니라면 루트 찾을 때까지 재귀 호출
	}
}
