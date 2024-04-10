import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N의 크기만큼 1면, 2면, 3면이 보이는 개수 구하기
 * 3면이 보이는 개수는 상단 모서리 4개 고정
 * 1면에는 주사위의 최소값
 * 2면에는 최소값과 바로 다음 값
 * 3면에는 최소값과 다음값, 다음값
 * 0 - 5, 1 - 4, 2 - 3이 서로 마주보는 index(면)이기 때문에
 * 각 마주보는 면에서 작은값을 최소값, 다음값으로 정하는 것
 * 
 * 3면은 4로 고정
 * N=5
 * 2면 -> 4 * 4 + 4 * 3
 * 1면 -> 4 * 12 + 3 * 3
 * N=4
 * 2면 -> 4 * 3 + 4 * 2
 * 1면 -> 4 * 6 + 2 * 2
 * N=3
 * 2면 -> 4 * 2 + 4 * 1
 * 1면 -> 4 * 2 + 1 * 1
 * 
 * -> 2면 4 * (N-1) + 4 * (N-1)
 * -> 1면 4 * (N-2) * (N-1) + (N-2) * (N-2)
 * 18404KB | 224ms
 */

public class 주사위 {
	static long N;
	static long[] dice, minSelect;
	static long res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Long.parseLong(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		dice = new long[6];
		minSelect = new long[3];
		for(int i=0; i<6; i++) {
			dice[i] = Long.parseLong(st.nextToken());
		}
		
		minSelect[0] = Math.min(dice[0], dice[5]);
		minSelect[1] = Math.min(dice[1], dice[4]);
		minSelect[2] = Math.min(dice[2], dice[3]);
		
		Arrays.sort(minSelect);
		
		// 1면에 선택한 주사위 부분을 2면 정할 때 포함시켜주고
		// 그거를 다시 3면을 정할 때 포함시켜 주기
		// 이렇게 해야 6면중 가장 작은 3면이 선택
		minSelect[1] += minSelect[0];
		minSelect[2] += minSelect[1];
		
		// N이 1일때는 아래의 점화식 사용 불가
		if(N==1) {
			for(int i=0; i<6; i++) {
				res += dice[i];
			}
			// N이 1일때는 주사위 5면만 보이기 때문에 가장 큰 부분 제외하고 보여줌
			System.out.println(res - Arrays.stream(dice).max().getAsLong());
			return;
		}
		
		res += minSelect[0] * ((N-1)*(N-2) * 4 + (N-2) * (N-2));
		res += minSelect[1] * ((N-1) * 4 + (N-2) * 4);
		res += minSelect[2] * 4;
		
		System.out.println(res);
	}

}
