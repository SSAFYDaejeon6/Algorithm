import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class B10157_자리배치 {
	static int[] dir_x = {0, 1, 0, -1};
	static int[] dir_y = {1, 0, -1, 0};
	static BufferedReader br;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int C  = Integer.parseInt(st.nextToken());
    	int R = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(br.readLine());
		int new_x = 0;
		int new_y = 0;
		int cnt = 1;
		int d = 0;
		

		int[][] map = new int[C][R];
		while (true) {
			if(K > C * R) { // 배열 넘어서는 값이 입력되는 경우 -> 0 출력
	    		System.out.println(0);
	    		break;
	    	}
			
			map[new_x][new_y] = cnt;
			
			if (cnt == K) {
				System.out.println((new_x+1) + " " + (new_y+1));
				break; // 0,0 배열에서 시작하였으므로 각각 1을 더함
			}
			
			// 방향 변경(배열을 벗어나거나 다음 위치에 이미 값이 들어있을 때)
			if ((new_x+dir_x[d]) >= C || (new_x+dir_x[d]) < 0 || (new_y+dir_y[d]) >= R || (new_y+dir_y[d]) < 0 || map[new_x+dir_x[d]][new_y+dir_y[d]] != 0) 
				d=(d+1)%4;
			new_x = new_x + dir_x[d];
			new_y = new_y + dir_y[d];
			cnt++;

			if (cnt > C * R) // 최대숫자까지 도달하면 break
				break;
		}
	
	}

}
