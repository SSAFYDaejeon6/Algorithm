import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		int[] len = new int[6];
		List<Integer> small = new ArrayList<>();
		int sum = 0;
		int max_x = 0, max_y = 0;
		
		for(int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			len[i] = Integer.parseInt(st.nextToken());
			
			// 가로 세로에서 제일 큰 크기 구하기
			if(d <= 2) {
				max_x = Math.max(max_x, len[i]);
			}
			else {
				max_y = Math.max(max_y, len[i]);
			}
			
		}
		
		for(int i=0; i<6; i++) {
			// 그림 구조상 현재 인덱스의 len[i-1] + len[i+1]을 했을때 나오는 값이
			// 가로 또는 세로의 최대값이랑 일치한다면 작은 사각형의 가로 세로 길이중 하나임
			
			// 50 160 30 60 20 100
			// 60의 왼쪽(30) 오른쪽(20) 더하면 50이고 세로의 최대값이랑 같음
			// 20의 왼쪽(60) 오른쪽(100) 더하면 160이고 가로의 최대값이랑 같음
			// 20과 60이 작은 사각형의 가로 세로 길이가 되는 것
			int l = 0;
			
			
			// 인덱스 범위 초과 방지
			l+= len[i==0 ? 5 : i-1] + len[(i+1)%6];

			if(l == max_x || l == max_y) {
				small.add(len[i]);
			}
			
		}
		
		sum += max_x * max_y;
		
		// ArrayList의 모든 값을 곱한 것
		sum -= small.stream().reduce(1, (x, y) -> x*y);
		System.out.println(sum*n);

		
	}

}
