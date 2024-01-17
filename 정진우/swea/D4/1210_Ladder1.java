import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {		
		for(int tc=1; tc<=10; tc++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int T = Integer.parseInt(st.nextToken());
		
			int N = 100;
			int[][] arr = new int[N][N];

			for(int i=0; i<N; i++) {
				s = br.readLine();
				st = new StringTokenizer(s);
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answerX=0;
			for(int i=0; i<N; i++) {
				if(arr[99][i] == 2) {
					answerX = search(arr, 99, i);
				}
			}
			
			System.out.println("#"+tc + " " +answerX);
		}
	}
	
	public static int search(int[][] arr, int y, int x) {
		char state = 'N';
		
		int yy = y;
		int xx = x;
		while(yy != 0) { // y 값이 0이될때까지 반복
			
			
			if(xx != 0 && arr[yy][xx-1] == 1 && state != 'R') {
				state = 'L';
				xx = xx-1;
				// go left
			} 
			else if(xx != 99 && arr[yy][xx+1] == 1 && state != 'L') {
				state = 'R';
				xx = xx+1;
				// go right
			}
			else {
				state = 'N';
				yy = yy-1;
				//go up
			}
//			System.out.println(xx + " " + yy + " " + state);	
			
			
		}
		return xx;
		
	}
}
