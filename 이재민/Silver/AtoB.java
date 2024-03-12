import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 각각의 조건에 맞게 Queue에 넣어서 답을 구함
 * 큐(BFS)를 이용하는 이유는 DFS는 하나의 조건으로 끝까지 들어가고
 * BFS는 두개의 조건을 FIFO구조를 가진 큐에 넣기 때문에 번갈아가면서 확인 가능
 * 근데 A -> B를 만드는게 아닌 B에서 시작해 1이면 없애고 아니면 2로 나누는
 * 다른 사람 풀이가 더 좋은듯?
 * 13936KB | 104ms
 */

public class AtoB {
	static long A, B;
	
	static int bfs() {
		Queue<long[]> q = new ArrayDeque<>();
		q.add(new long[] {A, 1});
		
		while(!q.isEmpty()) {
			long a = q.peek()[0];
			long d = q.poll()[1];
			
			if(2*a == B || a*10+1==B) {
				return (int)d+1;
			}
			if(2*a < B)
				q.add(new long[] {2*a, d+1});
			if(a*10+1 < B)
				q.add(new long[] {a*10+1, d+1});
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs());
	}

}