/**Main_B_1359_숨바꼭질3_이동준 14660KB 128ms
 * 
 * 
 * Facts
 * 	+1 연산 또는 -1 연산은 1초의 시간이 든다.
 * 	*2 연산은 0초의 시간이 든다.
 * 	N, K < 10만
 *  
 * IDEA
 * 	2^16 <10만 < 2^17 미만
 * 	따라서 최대 매 + 또는 -연산당 *2^(0~16)의 17가지 form이 존재한다.
 * 	 
 * 	*2 연산은 항상 짝수만 만든다.
 * 	따라서 Target이 홀수인 경우 +1 또는 -1을 마지막에 해야만 한다
 * 	목표 숫자가 자신보다 작다면, -1을 반복하는게 가능한 유일한 연산이다.
 * 	따라서 Backtracking
 * 0.현재 연산이 알려진 최소 연산 이상이면 중단
 * 1. 목표숫자가 자신보다 작거나 같다면
 * 		자신 - 목표숫자 랑 비교해서 최소연산 수 업데이트
 * 2. 목표숫자가 자신보다 큰 경우
 * 		2^1~16승 해서 재귀
 * 		+1 해서 사용한 연산 수 +1 하고 재귀
 * 그냥 BFS 허쉴?
 * 
 */


import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main_B_13549_숨바꼭질3_이동준 {
	final static int PN = 13549;
	static int N;
	static int Target;
	static int MINTIME;
	static boolean[] Visited;
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		Visited = new boolean[100001];
		MINTIME = Integer.MAX_VALUE;
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		Target = Integer.parseInt(input[1]);
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(N);
		Visited[N] = true;
		int time = 0;
		int curNums = 1;
		int nextNums = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == Target) {
				System.out.println(time);
				System.exit(0);
			}
			curNums--;
			int next = cur * 2;
			if(cur != 0 && next <= 100000 && !Visited[next]) {
				q.push(next);
				Visited[next] = true;
				curNums++;
			}
			for(int i = -1; i < 2; i += 2) {
				next = cur + i;
				if(next >= 0 && next <= 100000 && !Visited[next]) {
					q.offer(next);
					Visited[next] = true;
					nextNums++;					
				}
			}
			if(curNums == 0) {
				time++;
				curNums = nextNums;
				nextNums = 0;
			}
		}
	}
}
