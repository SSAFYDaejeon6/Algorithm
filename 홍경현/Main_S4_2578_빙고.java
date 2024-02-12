package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//11556kb	76ms
public class Main_S4_2578_빙고 {
	static int bingo[][] = new int[5][5];
	static int count, result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				bingo[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				++result;
				int n = Integer.parseInt(st.nextToken());
				search(n);
				if(count>=3) {
					System.out.println(result);
					return;
				}
			}
		}
	}
	
	//5이상의 수가 불리면 해당 칸의 가로, 세로, 대각선 비교 후 count
	private static void search(int n) {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(bingo[i][j] == n) {
					bingo[i][j] = 0;
					if(result<5) break;
					if(checkW(i)) count++;
					if(checkH(j)) count++;
					if(i==j) {
						if(check(1)) count++;
					}
					if(i+j==4) {
						if(check(2)) count++;
					}
					break;
				}
			}
		}
	}
	
	//가로 비교
	private static boolean checkW(int n) {
		for(int i=0; i<5; i++) {
			if(bingo[n][i] != 0) return false;
		}
		return true;
	}
	
	//세로 비교
	private static boolean checkH(int n) {
		for(int i=0; i<5; i++) {
			if(bingo[i][n] != 0) return false;
		}
		return true;
	}
	
	//대각선 비교
	private static boolean check(int idx) {
		if(idx==1) {
			for(int i=0; i<5; i++) {
				if(bingo[i][i] != 0) return false;
			}
		}
		else {
			for(int i=0; i<5; i++) {
				if(bingo[i][4-i] != 0) return false;
			}
		}
		return true;
	}
}
