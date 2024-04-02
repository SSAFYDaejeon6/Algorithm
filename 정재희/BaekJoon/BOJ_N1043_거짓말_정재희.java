import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 	11,652KB | 80ms
 * 
 *  1. 이미 진실을 알고 있는 사람들 체크
 *  2. 같은 파티에 참석하는 사람들 인접행렬 표기
 *  3. 큐를 이용해 진실을 알게되는 사람들 모두 찾기
 *  4. 진실을 알고 잇는 사람들이 참석하는 파티 수 세기
 */
public class BOJ_N1043_거짓말_정재희 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] persons = new boolean[N+1][N+1];
		boolean[] know = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<>();
		
		/* 입력 */
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		for (int i = 0; i < t; i++) {
			int p = Integer.parseInt(st.nextToken());
			know[p] = true;
			queue.offer(p);
		}
		
		int[][] party = new int[M][];
		boolean[][] attend = new boolean[N+1][M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			party[i] = new int[cnt];
			for (int j = 0; j < cnt; j++) {
				party[i][j] = Integer.parseInt(st.nextToken());
				attend[party[i][j]][i] = true;
			}
		}
		
		/* Make linked adj */
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < party[i].length; j++) {
				for (int j2 = j+1; j2 < party[i].length; j2++) {
					persons[party[i][j]][party[i][j2]] = true;
					persons[party[i][j2]][party[i][j]] = true;
				}
			}
		}
		
		boolean[] cntParty = new boolean[M];
		int cnt = 0;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 0; i < M; i++) {
				if(!attend[cur][i]) continue;
				if(!cntParty[i]) cnt++;
				cntParty[i] = true;
			}
			for (int i = 1; i < N+1; i++) {
				if(!persons[cur][i] || know[i]) continue;
				know[i] = true;
				queue.offer(i);
								
			}
		}
		System.out.println(M-cnt);

	}

}
