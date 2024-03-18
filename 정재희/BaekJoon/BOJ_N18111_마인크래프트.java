import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 35,160KB | 592ms
 */
public class BOJ_N18111_마인크래프트 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		int minTime = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;
		
		final int MAXVAL = (int) (64 * Math.pow(10, 6));
		
		int maxH;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
				
		A: for (int h = 0; h <= 256; h++) {  // 벽돌의 높이 설정
			int times = 0;
			int inben = B;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int diff = map[i][j] - h;  // 목표 벽의 높이와 차이 계산
					if(diff < 0) // 목표가 높아 -> 블록 위에 쌓아
					{
						times += Math.abs(diff)*1;   // 시간 계산
						inben -= Math.abs(diff);     // B 계산
					}
					else if(diff > 0)   // 블록 제거
					{
						times += diff*2;
						inben += diff;
					}
					
					if(minTime < times) continue A;  // 최소시간보다 커지면 바로 제거
				}
			}
			if(inben < 0 || inben > MAXVAL) continue;  // 이렇게 쌓을 수 없다
			
			maxHeight = minTime == times? h:maxHeight;
			if(minTime > times) {  // 갱신
				minTime = Math.min(minTime, times);
				maxHeight = h;
			}
		}
		
		System.out.println(minTime +" " + maxHeight);
	}

}
