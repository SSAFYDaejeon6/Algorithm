import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/** 12212KB 96ms
 * N <= 1000
 * 
 * M <=100
 * 완탐
 * 	1000! ->말도안됨
 * 그래프로 생각해보자
 * 		일단 Cycle이 있으면 순서를 정하는 것이 불가능 함
 * 	그 후, 각 PD들의 첫번째인 가수들 중에 Parent가 없는 Node로부터 시작해서 모든 Node를 한 번씩만 방문하는 경로를 찾으면 됨.
 * Idea
 * 	directed graph이고
 * 
 * 	1: 그래프의 Cycle을 탐지할 수 있어야 함
 *	....위상 정렬을 쓰면 될 것 같다.
 *	처음에 parent가 없는 Node들 나열해서 공연 시키고
 *	그다음에 먼저 공연 시키고,.... 반복하면 됨
 *
 *프로세스
 *		N개의 Node
 *			int id
 *			List<Node> children
 * 		int[] parents
 * 			index: Node id 값: 부모 수
 * 		parent가 0개인 Node들 골라서 Q에 넣고 빼고 children들 parent 수 1 씩 감소시킴
 * 		이걸 계속 반복
 */

public class Main_B_2623_음악프로그램 {
	static int N, M;//가수 수, PD 수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Node[] Singers = new Node[N];
		for(int i = 0; i < N; i++) {
			Singers[i] = new Node(i);
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int precedes = -1;
			for(int pdManages = Integer.parseInt(st.nextToken()); pdManages > 0; pdManages--) {
				int current = Integer.parseInt(st.nextToken()) - 1;//index 기준으로 생각함
				if(precedes != -1) {
					Singers[precedes].childs.add(Singers[current]);
					Singers[current].numParents++;
				}
				precedes = current;
			}
		}
		//입력 끝
		Deque<Node> q = new ArrayDeque<>();
		for(Node aSinger : Singers) {
			if(aSinger.numParents != 0) continue;
			q.offer(aSinger);
		}
		int outputCount = 0;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			sb.append(cur.id + 1).append('\n');
			outputCount++;
			for(Node c : cur.childs) {
				c.numParents--;
				if(c.numParents == 0) q.offer(c);
			}
		}
		if(outputCount != N) {
			System.out.println(0);
		}else {
			System.out.print(sb.toString());
		}
	}
	
	private static class Node{
		int id, numParents;
		List<Node> childs;
		Node(int singerId){
			id = singerId;
			childs = new ArrayList<Node>();
		}
	}
}