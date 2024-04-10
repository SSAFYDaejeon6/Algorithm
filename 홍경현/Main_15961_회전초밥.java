package Algorithm.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 201488kb 580ms
 * [문제 해석]
	초밥의 종류 -> 번호로 표현
	같은 종류의 초밥 둘 이상 있을 수 있음
	
	1. 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
	2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰 발행
		1번 행사에 참가하면 해당 초밥 무료 제공
		만약 현재 벨트 위에 없으면 새로 만들어 제공
		
	위 할인 행사에 참여하여 가능한 다양한 종류의 초밥
	
	손님이 먹을 수 있는 초밥 가짓수의 최댓값
	
	[입력]
	1. N, d, k, c
		접시 수 2<=N<=3,000,000
		초밥의 가짓수 2<=d<=3,000
		연속해서 먹는 접시 수 2<=k<=3000 (k<=N)
		쿠폰 번호 1<=c<=d
	2. 회전 방향을 따라갈 때 초밥의 종류를 나타내는 1이상 d이하의 정수
	
	[출력]
	초밥 가짓수의 최댓값
 */
public class Main_15961_회전초밥 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //접시 수
		int d = Integer.parseInt(st.nextToken()); //초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); //연속 접시 수
		int c = Integer.parseInt(st.nextToken()); //쿠폰 번호
		
		int sushi[] = new int[d+1]; //현재 먹은 스시의 개수를 담는 배열
		sushi[c]++; //무조건 먹는 쿠폰 회전 추가하고 시작함
		int maxCnt = 0; //최대 수
		int curCnt = 1; //현재 수
		
		Queue<Integer> start = new ArrayDeque<>(); //원형이라 초반 4개를 따로 저장해둠
		Queue<Integer> queue = new ArrayDeque<>(); //현재 k개의 초밥
		
		for(int i=0; i<k; i++) {
			int n = Integer.parseInt(br.readLine());
			queue.add(n);
			start.add(n);
			if(sushi[n]==0) curCnt++; //먹지 않은 초밥이면 +1
			sushi[n]++;
		}
		
		maxCnt = curCnt;
		
		//슬라이딩 윈도우
		for(int i=k; i<N; i++) {
			int s = queue.poll();
			if(sushi[s]==1) curCnt--;
			sushi[s]--;
			int n = Integer.parseInt(br.readLine());
			queue.add(n);
			if(sushi[n]==0) curCnt++;
			sushi[n]++;
			
			maxCnt = Math.max(maxCnt, curCnt);
		}
		
		while(!start.isEmpty()) {
			int s = queue.poll();
			if(sushi[s]==1) curCnt--;
			sushi[s]--;
			int n = start.poll();
			queue.add(n);
			if(sushi[n]==0) curCnt++;
			sushi[n]++;
			
			maxCnt = Math.max(maxCnt, curCnt);
		}
		
		System.out.println(maxCnt);
	}

}
