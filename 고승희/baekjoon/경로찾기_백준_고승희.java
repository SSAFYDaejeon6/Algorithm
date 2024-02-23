package algo0221;
/**13456kb	136ms
 * 주어진 가중치 없는 그래프에 따라 i에서 j로 가는 경로가 존재하는지 - 행렬로 나타내기
 * 
 * 처음엔 dfs를 이용해서 값이 1일 때 dfs 진행하고 -> 사이클이 생기면 순열로, 더이상 진행이 안되면 조합으로 경우의 수를 구해 모두 체크하려 함
 * 
 * 인터넷을 통해 플로이드-와샬 알고리즘이라는 힌트를 얻음
 * <사용하는 경우> 가중치 그래프에서 i에서 j로 가는 최소 비용을 구할 때 사용한다.
 * [시간복잡도]
플로이드 와샬의 시간 복잡도는 O(N^3) 이다.
[공간복잡도]
이차원 배열에 따른 공간복잡도만 고려하면 된다. O(N^2) 이다.
 *  
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class B11403_경로찾기 {
	static int N;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		//1.입력 및 연결리스트 만들기
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//입력 완료

		//찍어보기
//		System.out.println(Arrays.deepToString(map));

		//플로이드 와샬 알고리즘으로 푼다
		for (int k=0; k<N; k++) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][k] == 1 && map[k][j] == 1)
						map[i][j] = 1;
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}


}
