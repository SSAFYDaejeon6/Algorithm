package alg0206;
/*11620kb	80ms
 * dfs 
 * 그래프 탐색 문제이다. 
 * 그래프 탐색 같은 문제의 경우 그래프 크기가 정말 크지 않다면 BFS가 효율적이다. 
 * 해당 문제는 전체 그래프 탐색이 아니라 특정 지점으로 도달하는 문제이기 때문에 DFS 탐색을 사용하는 게 좋다.
 * 라고 블로그에서 힌트를 얻음
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B2644_촌수계산_고승희 {
	static int N, start, end, num;
	static int parent,child;
	static int[][] map;
	static boolean[] checked;
	static int res;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //숫자 몇번까지
		st = new StringTokenizer(br.readLine()); 
		start = Integer.parseInt(st.nextToken()); //촌수 따지기 시작점
		end = Integer.parseInt(st.nextToken()); //도착점
		num = Integer.parseInt(br.readLine()); //몇번 이동했는지
		res = -1; //답을 담을 변수(전역)
		map = new int[N+1][N+1]; //촌수 정보를 담을 int 배열 -> 부모-자식 관계:1, 아님:0
		checked = new boolean[N+1]; //체크했는지 여부를 담을 boolean 배열
		
		for (int i=0; i<num; i++) {
			st = new StringTokenizer(br.readLine());
			parent = Integer.parseInt(st.nextToken());
			child = Integer.parseInt(st.nextToken());
			
			map[parent][child] = 1; //촌수 정보 배열에 체크
			map[child][parent] = 1; //자식->부모, 부모->자식 양방향으로 이동 가능하므로 자식에서 자식-부모도 체크
		}
		dfs(start, 0);
		
		System.out.println(res);
	}
	//부모가 반드시 1개만 있다고 했으므로 시작점에서 도착점에 도달하는 방법은 딱 하나인 것 같다
	//dfs를 이용해서 도착지에 오면 return하도록 함
	public static void dfs(int start, int cnt) { 
		if (start == end) { //도착지점이라면 res 변수에 이동횟수 저장하고 리턴
			res = cnt;
			return;
		}
		checked[start] = true;
		for (int i=0; i<=N; i++) { //배열에 부모자식 관계가 있는지 탐색
			if (map[start][i] == 1) { //부모자식 관계인 경우
				if (checked[i] == true) continue; //이미 체크되었다면 패스
				dfs(i, cnt+1); //또다시 dfs에 넣음
			}
		}
	}
 
}
