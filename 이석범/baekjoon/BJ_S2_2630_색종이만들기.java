package algo0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//14388kb, 132ms
public class BJ_S2_2630_색종이만들기 {
	static int n;
	static int[][] graph;
	static int[] dx;
	static int[] dy;
	static int white, blue;
	
	static void recur(int curX, int curY, int n) {
		
		//0,0 n/2,0  0,n/2 n/2,n/2 순으로 반복
		for(int i=0; i<4;i++) {
			dx = new int[] {0, n/2, 0, n/2};
			dy = new int[] {0, 0, n/2, n/2};
			int whiteCnt = 0;
			int blueCnt = 0;
			//현재 네모에 색상이 모두 같으면 +1추가 아니면 재귀로 나눔
			for(int x=0;x<n/2;x++) {
				for(int y=0;y<n/2;y++) {
					int nx = curX+x+dx[i];
					int ny = curY+y+dy[i];
					if(graph[nx][ny]==0) whiteCnt++;
					else blueCnt++;
				}
			}
			if(whiteCnt !=0 && blueCnt != 0) recur(curX+dx[i], curY+dy[i], n/2);
			else {
				if(whiteCnt==0) blue++;
				else white++;
			}
			
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		graph = new int[n][n];
		for(int x=0; x<n;x++) {
			st = new StringTokenizer(br.readLine());
			for(int y=0; y<n;y++) {
				graph[x][y] = Integer.parseInt(st.nextToken());				
			}
		}
		
		//모든 그래프가 0이거나 1이면 색상에 1을 출력
		//아닐 경우 재귀 
		int whiteCnt = 0;
		int blueCnt = 0;
		for(int x=0; x<n;x++) {
			for(int y=0;y<n;y++) {
				if(graph[x][y] == 0) whiteCnt++;
				else blueCnt++;
			}
		}
		if(whiteCnt !=0 && blueCnt != 0) recur(0, 0, n);
		else {
			if(whiteCnt==0) blue++;
			else white++;
		}
		System.out.println(white);
		System.out.println(blue);
		
		
		
	}
}
