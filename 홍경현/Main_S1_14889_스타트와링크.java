package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 14304kb 196ms
 * 문제 해석
	짝수 N명이 N/2명으로 이루어진 스타트팀 vs 링크팀
	능력치 Sij는 i와 j번 사람이 같은 팀일 때 더해지는 능력치
	팀 능력치 = 모든 쌍의 능력치 Sij의 합
	
	입력
	1. 4≤N≤20
	2. 1≤S≤100
	
	출력
	최솟값
 */

public class Main_S1_14889_스타트와링크 {
	static int N, result = Integer.MAX_VALUE;
	static int S[][], start[], link[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		start = new int[N/2];
		link = new int[N/2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0, 0);
		System.out.println(result);
	}

	//비트마스킹을 이용하여 start팀 조합 선택, flag = 0 인 자릿수는 link 팀
	private static void comb(int idx, int num, int flag) {
		if(idx==N/2) {
			int index = 0;
			for(int i=0; i<N; i++) {
				if((flag & 1<<i) == 0) link[index++] = i;
			}
			sum();
			return;
		}
		for(int i=num; i<N; i++) {
			start[idx] = i;
			comb(idx+1, i+1, flag | (1<<i));
		}
	}
	
	//start와 link팀의 능력의 합을 구하고 최솟값 저장
	private static void sum() {
		int s = 0, l = 0;
		for(int i=0; i<N/2; i++) {
			for(int j=i; j<N/2; j++) {
				s += S[start[i]][start[j]]+S[start[j]][start[i]];
				l += S[link[i]][link[j]]+S[link[j]][link[i]];
			}
		}
		result = Math.min(result, Math.abs(s-l));
	}

}
