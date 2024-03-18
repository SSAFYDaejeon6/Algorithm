package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *NxMxB
 *땅의 높이는 256
 *
 *블록 제거 후 인벤토리 2초
 *블록 하나 꺼내어 블록위 1초
 *
 *최소시간과 땅의 높이
 *34460kb 692ms
 */
public class BJ_S2_18111_마인크래프트 {
	
	
	static final int MAX = 256;

	static int R, C, inventory;

	static int[][] graph;

	static int[][] time = new int[MAX+1][2];

	static int minTime = Integer.MAX_VALUE;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		inventory = Integer.parseInt(st.nextToken());

		graph = new int[R][C];
		
		//그래프에서 최대값까지 추가 하는것이 시간 최소가 됨
		int maxNum = Integer.MIN_VALUE;

		for(int r=0; r<R;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C;c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
				maxNum = Math.max(maxNum, graph[r][c]);
			}
		}

		//모든 땅이 i개로 되는 경우
		A: for(int i=0; i<=maxNum;i++) {
			for(int r=0; r<R;r++) {
				for(int c=0; c<C;c++) {
					//백트래킹 가지수 줄이기 위해 이전의 최소시간보다 크면 바로 다음 인덱스
					if(time[i][0] > minTime) continue A;

					//같으면 아무것도 안함
					if(graph[r][c] == i) continue;
					else if(graph[r][c] > i) {
						time[i][0] += 2*(graph[r][c]-i);
						time[i][1] += (graph[r][c]-i);
					}

					//i보다 작으면 1초를 추가 인벤에서 1빼고
					//0은 시간을 나타냄 1은 블록 수
					else {
						time[i][0] += (i-graph[r][c]);
						time[i][1] -= (i-graph[r][c]);
					}

				}
			}

			//시간이 적거나 같고 인벤토리의 블록수가 음수가 아니면 갱신
			if(time[i][0] <= minTime & (inventory + time[i][1]) >= 0 ) {
				minTime = time[i][0];
				result = i;
			}
		}



		System.out.println(minTime+" "+result);


	}
}
