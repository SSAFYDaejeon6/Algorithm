package algo0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 틱택토 {
	
	static String str;
	static int xCnt;
	static int oCnt;
	static char[][] ttt;
	
	static int oWin() {
		int cnt = 0;
		int l = 0, r = 0;
		// 가로, 좌상우하, 우상좌하
		for(int i=0; i<3; i++) {
			int o = 0;
			for(int j=0; j<3; j++) {
				if(ttt[i][j] == 'O') o++;
				if(i==j && ttt[i][j]=='O') l++;
				if(i+j == 2 && ttt[i][j] == 'O') r++;
			}
			if(o==3) cnt++;
		}
		
		// 세로
		for(int i=0; i<3; i++) {
			int o = 0;
			for(int j=0; j<3; j++) {
				if(ttt[j][i] == 'O') o++;
			}
			if(o==3) cnt++;
		}
		if(l==3) cnt++;
		if(r==3) cnt++;
		return cnt;
	}
	
	static int xWin() {
		int cnt = 0;
		int l = 0, r = 0;
		// 가로, 좌상우하, 우상좌하
		for(int i=0; i<3; i++) {
			int o = 0;
			for(int j=0; j<3; j++) {
				if(ttt[i][j] == 'X') o++;
				if(i==j && ttt[i][j]=='X') l++;
				if(i+j == 2 && ttt[i][j] == 'X') r++;
			}
			if(o==3) cnt++;
		}
		
		// 세로
		for(int i=0; i<3; i++) {
			int o = 0;
			for(int j=0; j<3; j++) {
				if(ttt[j][i] == 'X') o++;
			}
			if(o==3) cnt++;
		}
		if(l==3) cnt++;
		if(r==3) cnt++;
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		ttt = new char[3][3];
		
		while(!(str = br.readLine()).equals("end")) {
			xCnt = 0;
			oCnt = 0;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					ttt[i][j] = str.charAt(i*3 + j);
					if(ttt[i][j] == 'O') oCnt++;
					else if(ttt[i][j] == 'X') xCnt++;
				}
			}
			
			// O가 X보다 많음
			if(oCnt > xCnt) {
				sb.append("invalid\n");
				continue;
			}
			
			// X의 개수와 O의 개수가 2개 이상 차이가 나는 경우
			if(Math.abs(xCnt - oCnt) >= 2) {
				sb.append("invalid\n");
				continue;
			}
			
			// O와 X가 각각 1개 이상의 승리
			int O = oWin();
			int X = xWin();
			if(O>=1 && X>=1) {
				sb.append("invalid\n");
				continue;
			}
			
			// X가 이겼는데 O의 개수가 X랑 같은경우
			if(X==1 && oCnt == xCnt) {
				sb.append("invalid\n");
				continue;
			}
			
			// 게임이 끝나지 않음
			if(O==0 && X==0 && xCnt+oCnt != 9) {
				sb.append("invalid\n");
				continue;
			}
			
			// O가 이겼는데 O와 X의 개수가 다름
			if(O==1 && oCnt != xCnt) {
				sb.append("invalid\n");
				continue;
			}
			
			sb.append("valid\n");
		}
		System.out.println(sb);
	}
}
