package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 12860kb 92ms
 * [문제 해석]
	식재료 N개 중 몇 개를 선택해서 영양분 일정 이상 만들기
	조건을 만족시키면서 비용이 최소가 되는 선택
	
	[입력]
	1. 3<=N<=15 : 식재료 개수
	2. 단백질 지방 탄수화물 비타민의 최소 영양성분 0<=mp mf ms mv<=500
	3. (N) 단백질, 지방, 탄수화물, 비타민, 가격 p f s v c
	
	[출력]
	최소 비용 (없으면 -1)
	최소 비용 식재료 번호 오름차순
	
	[문제 해결 프로세스]
	1. 부분 집합 (2^15 = 32,768)
		- return 조건
			1) 현재 비용이 최소 비용보다 클 때
			2) 최소 영양성분을 만족할 때
				-> 최소 비용보다 작으면 갱신

 */
public class Main_G4_19942_다이어트 {
	static int N;
	static int res = Integer.MAX_VALUE, len;
	static Node[] node;
	static int mp, mf, ms, mv;
	static int[] arr;
	
	static class Node{
		int p, f, s, v, c;

		public Node(int p, int f, int s, int v, int c) {
			super();
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [p=" + p + ", f=" + f + ", s=" + s + ", v=" + v + ", c=" + c + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		node = new Node[N+1];
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			node[i] = new Node(p, f, s, v, c);
		}
		
		part(0, 1, 0, new int[N]);
		
		if(len==0) System.out.println(-1);
		else {
			System.out.println(res);
			for(int i=0; i<len; i++) {
				System.out.print(arr[i]+" ");
			}
		}
	}

	//부분집합
	private static void part(int cnt, int idx, int cost, int[] pick) {
		if(cost >= res) return; //현재 비용이 최소 비용보다 크면 return
		
		//조건 만족하는지 확인
		boolean b = check(pick, cnt);
		if(b) {
			//조건 만족하면 최소 비용 갱신
			res = cost;
			System.arraycopy(pick, 0, arr, 0, N);
			len = cnt;
			return;
		}
		
		//N보다 크면 return
		if(cnt>=N) {
			return;
		}
		
		for(int i=idx; i<=N; i++ ) {
			pick[cnt] = i;
			part(cnt+1, i+1, cost+node[i].c, pick);
		}
	}

	private static boolean check(int[] pick, int cnt) {
		int sumP = 0, sumF = 0, sumS = 0, sumV = 0;
		for(int i=0; i<cnt; i++) {
			sumP += node[pick[i]].p;
			sumF += node[pick[i]].f;
			sumS += node[pick[i]].s;
			sumV += node[pick[i]].v;
		}
		if(sumP>=mp && sumF>=mf && sumS>=ms && sumV>=mv) return true;
		return false;
	}

}
