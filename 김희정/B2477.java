import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[BOJ] 2477 참외밭
- 큰 사각형에서 작은 사각형 빼기
- 작은 사각형은 방향 패턴이 반복되는 것으로 구함.
*/
public class B2477 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine()); //참외 갯수
		int[][] info = new int[6][2];  //0 : 방향, 1 : 길이
		int maxRow = 0;
		int maxCol = 0;
		for(int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			info[i][0] = d;
			info[i][1] = length;
			if(d == 3 || d == 4) maxRow = Math.max(maxRow, length);
			else maxCol = Math.max(maxCol, length);
		}
		int answer = 0;
		for(int i = 0; i < 6; i++) {
			int currD = info[i][0];
			int currL = info[i][1];
			if(currD == info[(i+2)%6][0] && info[(i+1)%6][0] == info[(i+3)%6][0]) {  // 3 1 3 1 4 2 처럼 2-2씩 반복될 때 작은 사각형 ( 인덱스가 넘어가는 것을 막기 위해 % 연산)
				answer = maxRow * maxCol - info[(i+1)%6][1] * info[(i+2)%6][1];
			}
		}
		System.out.println(answer * k);
	}

}
