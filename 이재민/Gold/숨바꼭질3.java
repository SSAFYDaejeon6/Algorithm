import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs 탐색
 * 값이 저장되는 배열과 방문 배열을 만들어서 체크
 * 방문체크가 되어 있어도 최소값을 갱신할 수 있으면 갱신
 * 값이 올라갔다 되돌아오는 부분이 더 최소값일 수 있고, 하나하나 배열의 크기를 체크해주기 어려움
 * 100001로 고정시켜놓고 돌리기 
 */

public class 숨바꼭질3 {
	static int N, K;
	static Queue<Integer> q;
	static int[] arr;
	static boolean[] visited;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[100001];
		visited = new boolean[100001];
		q = new ArrayDeque<>();
		q.add(N);
		if(N == K) {
			System.out.println(0);
			return ;
		}
		while (!q.isEmpty()) {
			int x = q.poll();
			
			if(x*2 <= 100000) {
				if((!visited[x*2] && arr[x*2] == 0) || arr[x*2] > arr[x]) {
					arr[x*2] = arr[x];
					q.add(x*2);
					visited[x*2] = true;
				}
			}
			if(x-1 >= 0) {
				if((!visited[x-1] && arr[x-1] == 0) || arr[x-1] > arr[x]+1) {
					arr[x-1] = arr[x]+1;
					q.add(x-1);
					visited[x-1] = true;
				}
			}
			if(x+1 <= 100000) {
				if((!visited[x+1] && arr[x+1] == 0) || arr[x+1] > arr[x]+1) {
					arr[x+1] = arr[x]+1;
					q.add(x+1);
					visited[x+1] = true;
				}
			}
			
		}
		System.out.println(arr[K]);
	}

}
