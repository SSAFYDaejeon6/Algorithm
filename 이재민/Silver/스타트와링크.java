import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * bool 배열을 사용하여 조합으로 뽑고 스타트와 링크 판단
 * 2 4 6을 뽑으면 1 3 5가 한번 더 계산 (이미 1 3 5를 뽑을때 계산 되었음)
 * 맨 앞이 1일때 뽑는 스타트가 모든 경우의 수를 계산한 것
 * bool 배열에 0번 인덱스를 true로 고정시키고 나머지를 뽑기
 * 13880KB | 224ms
 */
public class 스타트와링크 {

	static int res, n;
	static int[][] arr;
	static boolean[] visited;
	static void combi(int cnt, int idx) {
		if(cnt == n/2) {
			int start = 0;
			int link = 0;
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					// 둘다 true면 스타트
					if(visited[i] && visited[j]) 
						start += arr[i][j];
					// 둘다 false면 링크
					else if(!visited[i] && !visited[j])
						link += arr[i][j];
				}
			}
			
			res = Math.min(res, Math.abs(start-link));
			
			return ;
		}
		
		for(int i=idx; i<n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			combi(cnt+1, i+1);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		arr = new int[n][n];
		res = Integer.MAX_VALUE;
		visited = new boolean[n+1];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				arr[i][j] = arr[i][j] + arr[j][i];
			}
		}
		visited[0] = true;
		combi(1, 1);
		
		System.out.println(res);
		
		
	}

}