package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author SSAFY
 *
XXX
OO.
XXX
x먼저 o다음
최종상태인지 확인
1. 잇는데 성공
	x가 이기는 경우: x가 1개 많아야함
	o가 이기는 경우: o랑 x가 같아야함
2. 게임이 가득 찬 경우
	x가 5개, o가 4개
XOX
OXO
XOX
012
345
678

OOOXXX...
end
invalid

11304kb 72ms
 */
public class BJ_G5_7682_틱택토 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			String tmp = br.readLine();
			if(tmp.equals("end")) break;

			int result = check(tmp);
			
			//-1은 둘다 1개이상 이을 경우
			if(result==-1) {
				sb.append("invalid").append("\n");
				continue;
			}

			int one = 0;
			int two = 0;
			
			for(int i=0; i<9;i++) {
				if(tmp.charAt(i)=='X') one++;
				else if(tmp.charAt(i)=='O') two++;
			}
			
			//비김
			if(result==0) {
				if(one==5&&two==4) sb.append("valid").append("\n");
				else sb.append("invalid").append("\n");
			}
			//X가 이김
			else if(result==1) {
				if(one == two +1)  sb.append("valid").append("\n");
				else sb.append("invalid").append("\n");
			}
			//O가 이김
			else {
				if(one == two)  sb.append("valid").append("\n");
				else sb.append("invalid").append("\n");
			}


		}
		System.out.println(sb);

	}
	static char one, two, three;
	static int check(String tmp) {
		
		int oCnt = 0;
		int xCnt = 0;
		

		for(int r=0; r<3;r++) {
			one = tmp.charAt(r*3 + 0);
			two = tmp.charAt(r*3 + 1);
			three = tmp.charAt(r*3 + 2);

			if(one==two&&two==three) {
				if(one!='.') { 
					if(one == 'X') xCnt++;
					else oCnt++;
				}
			}

		}

		for(int r=0; r<3;r++) {
			one = tmp.charAt(r + 0);
			two = tmp.charAt(r + 3);
			three = tmp.charAt(r + 6);

			if(one==two&&two==three) {
				if(one!='.') { 
					if(one == 'X') xCnt++;
					else oCnt++;
				}
			}

		}
		one = tmp.charAt(0);
		two = tmp.charAt(4);
		three = tmp.charAt(8);

		if(one==two&&two==three) {
			if(one==two&&two==three) {
				if(one!='.') { 
					if(one == 'X') xCnt++;
					else oCnt++;
				}
			}
		}

		one = tmp.charAt(2);
		two = tmp.charAt(4);
		three = tmp.charAt(6);

		if(one==two&&two==three) {
			if(one==two&&two==three) {
				if(one!='.') { 
					if(one == 'X') xCnt++;
					else oCnt++;
				}
			}
		}

		if(xCnt!=0 && oCnt!=0) return -1;
		else if(xCnt==0&&oCnt==0) return 0;
		else {
			if(xCnt!=0) return 1;
			else return 2;
		}

	}
}
