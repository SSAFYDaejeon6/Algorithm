import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 * 메모리: 20,344KB   |   시간: 268ms
 *
 */
public class Main_N1260_DFS와BFS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();        // DFS
		Queue<Integer> queue = new ArrayDeque<Integer>();   // BFS
		
		String[] inputs = br.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		int V = Integer.parseInt(inputs[2]);
		
		boolean[] isVisited = new boolean[N+1];  // 방문 노드 체크
		
		List<Integer>[] lists = new ArrayList[N+1];
	
				
		List<Integer> res = new ArrayList<Integer>();
		
		/* 값 입력 */
		for (int i = 0; i < M; i++) {
			inputs = br.readLine().split(" ");
			int index = Integer.parseInt(inputs[0]);
			int value = Integer.parseInt(inputs[1]);
			if(lists[index] == null) lists[index] = new ArrayList<Integer>();
			if(lists[value] == null) lists[value] = new ArrayList<Integer>();			
			lists[index].add(value);
			lists[value].add(index);
		}
		
		/* DFS */
		stack.push(V);
		while(!stack.isEmpty()) {
			int v = stack.pop();
			if(isVisited[v]) continue;
			isVisited[v] = true;
			res.add(v);
			if(lists[v] == null) continue;
			Collections.sort(lists[v], Collections.reverseOrder());
			for (int i = 0; i < lists[v].size(); i++) {
				stack.push(lists[v].get(i));
			}
		}
		
		System.out.println(res.toString().replaceAll("[^0-9\\s]", ""));
		
		/* BFS */
		isVisited = new boolean[N+1];
		queue.offer(V);
		res.clear();
		while(!queue.isEmpty()) {
			int v = queue.poll();
			if(isVisited[v]) continue;
			isVisited[v] = true;				
			res.add(v);
			if(lists[v] == null) continue;
			Collections.sort(lists[v]);
			for (int i = 0; i < lists[v].size(); i++) {
				queue.offer(lists[v].get(i));
			}
		}
		
		System.out.println(res.toString().replaceAll("[^0-9\\s]", ""));
		
	}

}
