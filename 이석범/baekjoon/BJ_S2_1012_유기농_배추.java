import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_1012_유기농_배추 {
	
	
	static int[][] graph;
	static int count;
	
	static int n, m;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	//BFS로 주변에 있는 배추들 다 0으로 만들기
	static void BFS(int x, int y) {
		if(graph[x][y] == 1) graph[x][y] = 0;
		else return;
		for(int i=0; i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx>=0 && nx < n && ny >=0 && ny < m) {
				BFS(nx, ny);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력값
		BufferedReader br = new BufferedReader(new  InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int i=0; i< testCase;i++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			
			graph = new int[n][m];
			for(int j=0; j< k;j++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				graph[x][y] = 1;
			}
			
			//이중 반복문을 돌면서 배추가 있는 곳에서 BFS탐색
			for(int x=0; x<n;x++) {
				for(int y=0; y<m;y++) {
					if(graph[x][y] == 0) continue;
					else {
						//배추가 있으면 count++
						count++;
						BFS(x, y);
					}
				}
			}
			
			System.out.println(count);
			count = 0;
		}
		

		
	}
}
