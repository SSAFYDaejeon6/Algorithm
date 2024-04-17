package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 11460kb 76ms
 * [문제 해석]
	3x3 격자판
	X 또는 O 말을 번갈아 가며 놓는데
	반드시 첫 번째 사람이 X 두 번째 사람이 O
	한 사람의 말이 가로, 세로, 대각선 방향으로 3칸을 잇는데 성공하면 게임 종료
	게임판이 가득 차도 게임 종료
	게임판의 상태가 주어지면 그 상태가 틱택토 게임에서 발생할 수 있는 최종 상태인지 판별
	
	[입력]
	'X', 'O', '.' 중 하나
	end 입력 시 종료
	
	[출력]
	가능 valid
	불가능 invalid
	
	[문제 해결 프로세스]
	최종 상태가 될 수 없는 경우
	1. cntX - cntO !=0, != 1 일 때
		위 조건을 만족했을 때, cntX+cntO=9 이면 valid
	2. cntX + cntO < 9, 가로, 세로, 대각선 방향으로 3칸을 잇지 못할 때
 */
public class Main_G5_7682_틱택토 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String str = br.readLine();
			if(str.equals("end")) break;
			
			char[][] ttt = new char[3][3];
			int cntX = 0;
			int cntO = 0;
			
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					ttt[i][j] = str.charAt(i*3+j);
					if(ttt[i][j] == 'X') cntX++;
					else if(ttt[i][j] == 'O') cntO++;
				}
			}
			
			if((cntX-cntO)!=0 && (cntX-cntO)!=1) {
				sb.append("invalid").append('\n');
				continue;
			}
			
			int idx = 0;
			boolean check[] = new boolean[3]; // 0: 현재, 1: X, 2: O
			
			//가로  확인
			A: for(int i=0; i<3; i++) {
				int value = ttt[i][0];
				check[0] = true;
				
				if(value == '.') continue;
				else if (value == 'X') idx = 1;
				else idx = 2;
				
				if(check[idx]) continue;
				
				for(int j=0; j<3; j++) {
					if(ttt[i][j] != value) {
						check[0] = false;
						continue A;
					}
				}
				
				if(check[0]) {
					check[idx] = true;
				}
			}
			
			//세로  확인
			A: for(int i=0; i<3; i++) {
				int value = ttt[0][i];
				check[0] = true;
				
				if(value == '.') continue;
				else if (value == 'X') idx = 1;
				else idx = 2;
				
				if(check[idx]) continue;
				
				for(int j=0; j<3; j++) {
					if(ttt[j][i] != value) {
						check[0] = false;
						continue A;
					}
				}
				
				if(check[0]) {
					check[idx] = true;
				}
			}
			
			if(ttt[1][1] != '.') {
				if(ttt[1][1] == 'X') idx = 1;
				else idx = 2;
				
				//대각선 확인
				if(ttt[0][0] == ttt[1][1] && ttt[1][1] == ttt[2][2]) {
					check[idx] = true;
				}
				
				if(ttt[0][2] == ttt[1][1] && ttt[1][1] == ttt[2][0]) {
					check[idx] = true;
				}
			}
			
			String res = "";
			if((check[1] && check[2]) || //X, O 둘 다 승리한 경우
					(!check[1] && !check[2] && cntX+cntO < 9) || //둘 다 승리가 아닌데 '.'이 있는 경우
					(check[2] && cntX > cntO) || //O가 승리했는데 X가 말을 둔 경우
					(check[1] && cntX == cntO)) //X가 승리했는데 O가 말을 둔 경우
				res = "invalid"; 
			else res = "valid";
			
			sb.append(res).append('\n');
		}
		
		System.out.println(sb.toString());
		
	}

}
