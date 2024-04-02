import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//4 5
//1 1
//1 1
//1 2
//1 3
//1 4
//2 4 1

/*
 *  이야기의 진실을 아는 사람과 어떠한 파티라도 같이한다면
 *  그 파티에 속한 인원들에게는 거짓말을 할 수 없음
 *  위 예제에서 4번째 파티에서 과장되게 말하면 마지막 파티에서는 1이 있어서 거짓말이 들통이 날 수 있음
 *  거짓말을 하는 사람과 만나는 사람이나, 또 그 사람을 만나는 사람을 전부 하나의 집합으로 묶어줌
 *  하나의 집합에 있으면 과장된 이야기를 할 수 없는 사람들이므로 union-find를 사용할 수 있음
 *	11716KB | 88ms
 */

public class 거짓말 {
	
	static int N, M, res;
	static boolean[] truth;
	static List<Integer>[] edges;
	static int[] p;
	
	static void make() {
		p = new int[N+1];
		for(int i=1; i<=N; i++) {
			p[i] = i;
		}
	}
	
	static int find(int x) {
		if(p[x] == x) return x;
		return p[x] = find(p[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return ;
		if(truth[x]) p[y] = x;
		else if(truth[y]) p[x] = y;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = M;
		edges = new List[M];
		truth = new boolean[N+1];
		
		for(int i=0; i<M; i++) {
			edges[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		int tSize = Integer.parseInt(st.nextToken());
		for(int i=0; i<tSize; i++) {
			truth[Integer.parseInt(st.nextToken())] = true;
		}
		
		make();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int eSize = Integer.parseInt(st.nextToken());
			for(int j=0; j<eSize; j++) {
				edges[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int p=0; p<N; p++) {
			for(int i=0; i<M; i++) {
				for(int j=0; j<edges[i].size()-1; j++) {
					for(int k=j+1; k<edges[i].size(); k++)
						union(edges[i].get(j), edges[i].get(k));
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<edges[i].size(); j++) {
				int x = find(edges[i].get(j));
				if(truth[x]) {
					res--;
					break;
				}
			}
		}
		
		System.out.println(res);
		
	}

}
