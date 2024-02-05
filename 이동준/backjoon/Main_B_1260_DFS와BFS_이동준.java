/**Main_B_1260_DFS와BFS_이동준 22476KB 328ms
 * 
 * Vertex 번호가 작은 것부터 탐색. Stack과 Queue를 이용한다.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_B_1260_DFS와BFS_이동준 {
	static BufferedReader br;
	static int N;//number of Vertexs
	static int M;//number of Edges;
	static boolean[][] EDGES; 

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new FileReader("./b/1260.txt"));
		int V = 0;
		{
			String[] tempStringArr = br.readLine().split(" ");
			N = Integer.parseInt(tempStringArr[0]);
			M = Integer.parseInt(tempStringArr[1]);
			V = Integer.parseInt(tempStringArr[2]) - 1; //index로 변환
			EDGES = new boolean[N][N];
			int temp1;
			int temp2;
			for(int i = 0; i < M; i++) {
				tempStringArr = br.readLine().split(" ");
				temp1 = Integer.parseInt(tempStringArr[0]) - 1;//index로 변환
				temp2 = Integer.parseInt(tempStringArr[1]) - 1;
				EDGES[temp1][temp2] = true;
				EDGES[temp2][temp1] = EDGES[temp1][temp2];
			}
		}

		br.close();
		//입력 끝------
		//DFS
		DFS(V, new boolean[N]);

		BFS(V, new boolean[N]);
	}

	public static void DFS(int startingVertex, boolean visited[]) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(startingVertex);
		while(!stack.isEmpty()) {
			int vertex = stack.pop();
			if(visited[vertex]) continue;
			System.out.print((vertex + 1) + " ");
			visited[vertex] = true;
			for(int i =  N - 1; i >= 0; i--) {
				if(!EDGES[vertex][i]) continue;
				if(visited[i]) continue;
				stack.push(i);
			}
		}
		System.out.println();
	}

	public static void BFS(int startingVertex, boolean visited[]) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startingVertex);
		while(!queue.isEmpty()) {
			int vertex = queue.poll();
			if(visited[vertex]) continue;
			System.out.print((vertex + 1) + " ");
			visited[vertex] = true;
			for(int i =  0; i < N; i++) {
				if(!EDGES[vertex][i]) continue;
				if(visited[i]) continue;
				queue.add(i);
			}
		}
	}

}
