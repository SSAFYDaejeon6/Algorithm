import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 재귀를 통한 분할정복으로 색종이의 색깔을 판별해줌
 * 색종이의 색깔을 판별하는 함수는 2중 for문을 돌려 색깔이 다르면 false를 리턴
 * 정확한 시간복잡도를 판별할 수 없지만
 * 일반적으로 분할정복은 O(logN)의 시간복잡도를 가지고, 색종이 판별하는 for문은 O(n^2)의 시간복잡도를 가짐
 * 13220KB | 120ms
 */

public class 색종이만들기 {
	static int white, blue;
	static int[][] paper;

	static boolean check(int x, int y, int n) {
		
		int first = paper[x][y];
		
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(paper[i][j] != first)
					return false;
			}
		}
		
		return true;
	}
	
	static void color(int r, int c, int n) {
		
		if(check(r, c, n)) {
			if(paper[r][c] == 1) blue++;
			else white++;
			return ;
		}
		
		// 왼쪽 위
		color(r, c, n/2);
		
		// 오른쪽 위
		color(r, c+n/2, n/2);
		
		// 왼쪽 아래
		color(r+n/2, c, n/2);
		
		// 오른쪽 아래
		color(r+n/2, c+n/2, n/2);
		
		
	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		paper = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		color(0, 0, n);
		
		System.out.println(white);
		System.out.println(blue);
		
	}

}
