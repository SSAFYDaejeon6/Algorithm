import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 이전 코드에서 0, 0에 n이 아직 나누어 지지 않았을 때 (처음 시작했을 때)
 * 무조건 n*n의 for문이 돌고 이후에도 계속 2중 for문이 돌아감
 * 문제 조건에 따라 시간 복잡도를 초과하지는 않고 실제로 돌아가는 시간도 크게 차이나지는 않지만
 * 좀 더 효율적인 계산을 위해 DP 사용
 * 
 * 구간합 구하기때 사용했던 누적합을 이용해 미리 구해놓고
 * 분할정복을 통해 매개변수로 들어온 r, c, n을 이용해 원하는 구간의 합을 구해 줌
 * 처음에 미리 구해놨기 때문에 check하는 부분에서는 O(1)의 시간복잡도가 들어감
 * 13248KB | 116ms
 */
public class 색종이만들기_DP {
	static int white, blue;
	static int[][] paper;

	static int check(int x, int y, int n) {
		// 0,0이 아닌 1,1부터 시작했지만 n은 입력받은 그대로이기 때문에 -1
		// 1, 1, 8을 기준으로 -1을 안해주면 예외 발생
		int r = x+n-1;
		int c = y+n-1;
		int sum = paper[r][c] - paper[x-1][c] - paper[r][y-1] + paper[x-1][y-1];
		return sum;
	}
	
	static void color(int r, int c, int n) {
		
		int sum = check(r, c, n);
		if(sum == n*n) {
			blue++;
			return;
		}
		else if(sum == 0) {
			white++;
			return;
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
		
		paper = new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				paper[i][j] = paper[i-1][j] + paper[i][j-1] + Integer.parseInt(st.nextToken()) - paper[i-1][j-1];
			
			}
		}
		
		
		
		color(1, 1, n);
		
		System.out.println(white);
		System.out.println(blue);
		
	}

}
